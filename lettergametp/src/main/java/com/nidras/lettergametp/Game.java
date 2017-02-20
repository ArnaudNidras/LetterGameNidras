package com.nidras.lettergametp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Game extends Thread{
	
	private Dictionary dictionary;
	private Player player;
	private IA ia;
	private LetterPool cpool;
	private LetterPool ppool;
	private LetterPool ipool;
	private GUI gui;
	private boolean isPlayerTurn;
	
	public Game(){
		
		this.dictionary = new Dictionary();
		this.player = new Player();
		this.ia = new IA(this);
		this.cpool = new CommonPool();
		this.ppool = new PlayerPool();
		this.ipool = new IAPool();
		this.gui = new GUI(this);
		this.isPlayerTurn = false;
		
		run();
		
	}
	
	public Dictionary getDictionary(){
		
		return this.dictionary;
		
	}
	
	public Player getPlayer(){
		
		return this.player;
		
	}
	
	public IA getIA(){
		
		return this.ia;
		
	}
	
	public LetterPool getCommonPool(){
		
		return cpool;
		
	}
	
	public LetterPool getPlayerPool(){
		
		return ppool;
		
	}

	public LetterPool getIAPool(){
	
		return ipool;
	
	}
	
	public GUI getGUI(){
		
		return gui;
	
	}
	
	public void getStartingPlayer(Character player, Character ia){
		
		if((int) player > (int) ia){
			
			cpool.addElement(player);
			cpool.addElement(ia);
			gui.setLogsLabel("Le joueur commence !");
			gui.cpoolSetText(player + " ");
			gui.cpoolAddText(ia + "");
			playerTurn();
			
		}
		else if((int) player == (int) ia){
			
			getStartingPlayer(this.player.drawLetter(), this.ia.drawLetter());
			
		}
		else{
			
			cpool.addElement(player);
			cpool.addElement(ia);
			gui.setLogsLabel("L'ordinateur commence !");
			gui.cpoolSetText(player + " ");
			gui.cpoolAddText(ia + "");			
			iaTurn();
			
		}
		
	}
	
	public synchronized void playerTurn(){
		
		try {
			
			gui.setLogsLabel("A vous de jouer !");
			wait();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		isPlayerTurn = false;
		
	}
	
	public void iaTurn(){
		
		gui.setLogsLabel("A l'ordinateur de jouer !");
		ia.play();
		try {
			
			sleep(500);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		isPlayerTurn = true;
		
	}
	
	public synchronized void wakeUp(){
		
		this.notify();
		
	}
	
	public synchronized void run(){
		
		getStartingPlayer(player.drawLetter(), ia.drawLetter());
		while(ppool.getNumberOfElements() < 10 && ipool.getNumberOfElements() < 10){
			
			cpool.addElement(player.drawLetter());
			cpool.addElement(player.drawLetter());
			gui.update();
			
			if(isPlayerTurn) playerTurn();
			else iaTurn();
			
		}
		
	}

}
