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
	
	public Game(Dictionary dictionary, Player player, IA ia){
		
		this.dictionary = dictionary;
		this.player = player;
		this.ia = ia;
		this.cpool = new CommonPool();
		this.ppool = new PlayerPool();
		this.ipool = new IAPool();
		this.gui = new GUI(this);
		
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
	
	public void getStartingPlayer(Character player, Character ia){
		
		if((int) player > (int) ia){
			
			cpool.addElement(player);
			cpool.addElement(ia);
			gui.setLogsLabel("Le joueur commence !");
			gui.cpoolSetText(player + "\n");
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
			gui.cpoolSetText(player + "\n");
			gui.cpoolAddText(ia + "");			
			iaTurn();
			
		}
		
	}
	
	public synchronized void playerTurn(){
		
		try {
			System.out.println("Wait");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("lol");
		
	}
	
	public void iaTurn(){
		
		
		
	}
	
	public synchronized void wakeUp(){
		
		this.notify();
		
	}
	
	public synchronized void run(){
		
		getStartingPlayer(player.drawLetter(), ia.drawLetter());
		
	}

}
