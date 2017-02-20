package com.nidras.lettergametp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Game extends Thread{
	
	private Dictionary dictionary;
	private Player player;
	private IA ia;
	private LetterPool cPool;
	private LetterPool pPool;
	private LetterPool iPool;
	private GUI gui;
	private Plays plays;
	private boolean isPlayerTurn;
	
	public Game(){
		
		this.dictionary = new Dictionary();
		this.player = new Player();
		this.ia = new IA(this);
		this.cPool = new CommonPool();
		this.pPool = new PlayerPool();
		this.iPool = new IAPool();
		this.gui = new GUI(this);
		this.plays = new Plays(dictionary, pPool, cPool, iPool, gui, player);
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
		
		return cPool;
		
	}
	
	public LetterPool getPlayerPool(){
		
		return pPool;
		
	}

	public LetterPool getIAPool(){
	
		return iPool;
	
	}
	
	public GUI getGUI(){
		
		return gui;
	
	}
	
	public Plays getPlays(){
		
		return plays;
		
	}
	
	public void getStartingPlayer(Character player, Character ia){
		
		if((int) player > (int) ia){
			
			cPool.addElement(player);
			cPool.addElement(ia);
			gui.setLogsLabel("Le joueur commence !");
			gui.cPoolSetText(player + " ");
			gui.cPoolAddText(ia + "");
			playerTurn();
			
		}
		else if((int) player == (int) ia){
			
			getStartingPlayer(this.player.drawLetter(), this.ia.drawLetter());
			
		}
		else{
			
			cPool.addElement(player);
			cPool.addElement(ia);
			gui.setLogsLabel("L'ordinateur commence !");
			gui.cPoolSetText(player + " ");
			gui.cPoolAddText(ia + "");			
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
			
			sleep(1000);
			
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
		while(pPool.getNumberOfElements() < 10 && iPool.getNumberOfElements() < 10){
			
			cPool.addElement(player.drawLetter());
			cPool.addElement(player.drawLetter());
			gui.update();
			
			if(isPlayerTurn) playerTurn();
			else iaTurn();
			
		}
		
	}

}
