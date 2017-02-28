package com.nidras.lettergametp.players;

import java.util.ArrayList;

import com.nidras.lettergametp.Game;

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
	
		if(poolContent.length() == 0 || playableWords.size() == 0) return;
		
		for(String i : playableWords){
			
			if(game.getDictionary().countVowel(i) > game.getDictionary().countVowel(toPlay) && !game.getIAPool().containsWord(i)) toPlay = i;
			
		}
		if(toPlay.length() >= 3 - game.getPlayerPool().getNumberOfElements() && toPlay.length() > 0){
				
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
				
		}
		
		poolContent = "";
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
		
		playableWords = game.getDictionary().wordMaker(poolContent);
		
		if(playableWords.size() > 0 && hasPlayed) play();


		else {
			for(int j = 0 ; j < game.getPlayerPool().getNumberOfElements() ; j ++) {
				
				poolContent = game.getPlayerPool().getElement(j).toString();
				
				for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
				
				System.out.println("Pool : " + poolContent);
				
				playableWords = game.getDictionary().wordMaker(poolContent);
				
				for(int i = 0 ; i < playableWords.size() ; i ++) {
					System.out.println("Playable : " + playableWords.get(i));
						if(!playableWords.get(i).contains(game.getPlayerPool().getElement(j).toString()) || playableWords.get(i).length() <= game.getPlayerPool().getElement(j).toString().length()) {
						System.out.println("Deleting : " + playableWords.get(i));
						playableWords.remove(i);
						i --;
						
					}
				}
				toPlay = "";
				for(String i : playableWords){
					
					if(game.getDictionary().countVowel(i) > game.getDictionary().countVowel(toPlay) && !game.getIAPool().containsWord(i)) toPlay = i;
					System.out.println("Toplay : " + toPlay);
					
				}
					
				
				if(toPlay.length() > 0) {

					game.getIAPool().addElement(toPlay);
					System.out.println("Played : " + toPlay);
					toPlay = toPlay.replace(game.getPlayerPool().getElement(j).toString(), "");
					System.out.println("Removing : " + toPlay);
					
					for(int i = 0 ; i < toPlay.length() ; i ++) game.getCommonPool().removeElement(toPlay.charAt(i));
					
					game.getPlayerPool().removeElement(game.getPlayerPool().getElement(j).toString());
					j--;
					game.getCommonPool().addElement(game.getPlayer().drawLetter());
					game.getGUI().update();
					hasPlayed = true;
					try {
						
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}				
				
			}	
			
		}
		poolContent = "";
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
		
		playableWords = game.getDictionary().wordMaker(poolContent);
		
		if(playableWords.size() > 0 && hasPlayed) play();
		
	}

}
