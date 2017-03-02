package com.nidras.lettergametp;

import com.nidras.lettergametp.players.IA;
import com.nidras.lettergametp.players.Player;
import com.nidras.lettergametp.players.Plays;
import com.nidras.lettergametp.pool.CommonPool;
import com.nidras.lettergametp.pool.IAPool;
import com.nidras.lettergametp.pool.LetterPool;
import com.nidras.lettergametp.pool.PlayerPool;

public class Game extends Thread{
	
	private Dictionary dictionary;
	private Player player;
	private IA ia;
	private LetterPool<Character> cPool;
	private LetterPool<String> pPool;
	private LetterPool<String> pPool2;
	private LetterPool<String> iPool;
	private GUI gui;
	private Plays plays;
	private boolean isPlayer1Turn;
	private boolean pvp;
	
	public Game(boolean pvp){
		
		this.dictionary = new Dictionary();
		this.player = new Player();
		this.ia = new IA(this);
		this.cPool = new CommonPool();
		this.pPool = new PlayerPool();
		this.pPool2 = new PlayerPool();
		this.iPool = new IAPool();
		this.gui = new GUI(this);
		this.plays = new Plays(dictionary, pPool, pPool2, cPool, iPool, gui, player);
		this.isPlayer1Turn = false;
		this.pvp = pvp;
		
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
	
	public LetterPool<Character> getCommonPool(){
		
		return cPool;
		
	}
	
	public LetterPool<String> getPlayerPool(){
		
		return pPool;
		
	}
	
	public LetterPool<String> getPlayer2Pool(){
		
		return pPool2;
		
	}

	public LetterPool<String> getIAPool(){
	
		return iPool;
	
	}
	
	public GUI getGUI(){
		
		return gui;
	
	}
	
	public Plays getPlays(){
		
		return plays;
		
	}
	
	public boolean getPVP(){
		
		return pvp;
		
	}
	
	public boolean getIsPlayer1Turn(){
		
		return isPlayer1Turn;
		
	}
	
	public void getStartingPlayerIA(Character player, Character ia){
		
		if((int) player > (int) ia){
			
			cPool.addElement(player);
			cPool.addElement(ia);
			gui.setLogsLabel("Le joueur commence !");
			gui.cPoolSetText(player + " ");
			gui.cPoolAddText(ia + "");
			player1Turn();
			
		}
		else if((int) player == (int) ia){
			
			getStartingPlayerIA(this.player.drawLetter(), this.ia.drawLetter());
			
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
	
	public void getStartingPlayerPVP(Character player, Character player2){
		
		if((int) player > (int) player2){
			
			cPool.addElement(player);
			cPool.addElement(player2);
			gui.setLogsLabel("Le joueur 1 commence !");
			gui.cPoolSetText(player + " ");
			gui.cPoolAddText(player2 + "");
			player1Turn();
			
		}
		else if((int) player == (int) player2){
			
			getStartingPlayerPVP(this.player.drawLetter(), this.player.drawLetter());
			
		}
		else{
			
			cPool.addElement(player);
			cPool.addElement(player2);
			gui.setLogsLabel("Le joueur 2 commence !");
			gui.cPoolSetText(player + " ");
			gui.cPoolAddText(player2 + "");			
			player2Turn();
			
		}
		
	}
	
	public synchronized void player1Turn(){
		
		try {
			
			gui.setLogsLabel("Au joueur 1 de jouer !");
			wait();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		isPlayer1Turn = false;
		
	}
	
	public synchronized void player2Turn(){
		
		try {
			
			gui.setLogsLabel("Au joueur 2 de jouer !");
			wait();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		isPlayer1Turn = true;
		
	}
	
	public void iaTurn(){
		
		gui.setLogsLabel("A l'ordinateur de jouer !");
		ia.play();
		
		isPlayer1Turn = true;
		
	}
	
	public synchronized void wakeUp(boolean reset){
		
		this.notify();
		
		if(reset) resetGame(!pvp);
		
	}
	
	public synchronized void run(){
		
		if(!pvp) getStartingPlayerIA(player.drawLetter(), ia.drawLetter());
		else getStartingPlayerPVP(player.drawLetter(), player.drawLetter());
		while(pPool.getNumberOfElements() < 10 && iPool.getNumberOfElements() < 10){
			
			cPool.addElement(player.drawLetter());
			cPool.addElement(player.drawLetter());
			gui.update();
			
			if(!pvp){
			
				if(isPlayer1Turn) player1Turn();
				else iaTurn();
			
			}
			else{
				
				if(isPlayer1Turn) player1Turn();
				else player2Turn();
				
			}
			
		}
		
		if(pPool.getNumberOfElements() >= 10) gui.setLogsLabel("Le joueur 1 gagne !!!");
		
		else if(pPool2.getNumberOfElements() >= 10) gui.setLogsLabel("Le joueur 2 gagne !!!");
		
		else gui.setLogsLabel("L'ordinateur gagne ... Rekt !");
		
	}
	
	public void resetGame(boolean pvp){
		
		dictionary = new Dictionary();
		player = new Player();
		ia = new IA(this);
		cPool = new CommonPool();
		pPool = new PlayerPool();
		pPool2 = new PlayerPool();
		iPool = new IAPool();
		plays = new Plays(dictionary, pPool, pPool2, cPool, iPool, gui, player);
		isPlayer1Turn = false;
		this.pvp = pvp;
		
	}

}
