package com.nidras.lettergametp;

import java.util.ArrayList;

public class IA extends Player{
	
	private Game game;
	
	public IA(Game game){
		
		this.game = game;
		
	}
	
	public void play(){
		
		String poolContent = "";
		ArrayList<String> playableWords;
		boolean hasPlayed = false;
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
		
		playableWords = game.getDictionary().wordMaker(poolContent);
		
		String toPlay = "";
		
		for(String i : playableWords){
			
			if(game.getDictionary().countVowel(i) > game.getDictionary().countVowel(toPlay) && !game.getIAPool().containsWord(i)) toPlay = i;
			
			if(toPlay.length() >= 3 - game.getPlayerPool().getNumberOfElements()){
				
				game.getIAPool().addElement(toPlay);
    			for(int j = 0 ; j < toPlay.length() ; j ++) game.getCommonPool().removeElement(toPlay.charAt(j));
    			game.getCommonPool().addElement(game.getPlayer().drawLetter());
    			game.getGUI().update();
    			hasPlayed = true;
    			try {
    				
    				Thread.sleep(1000);
    				
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    			break;
				
			}
			
		}
		
		poolContent = "";
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
		
		playableWords = game.getDictionary().wordMaker(poolContent);
		
		System.out.println("PoolE : " + poolContent);
		
		if(playableWords.size() > 0 && hasPlayed) play();
		
	}

}
