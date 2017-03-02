package com.nidras.lettergametp.players;

import java.util.ArrayList;

import com.nidras.lettergametp.Game;

public class IA extends Player{
	
	private Game game;
	private String poolContent;
	private ArrayList<String> playableWords;
	
	public IA(Game game){
		this.game = game;
		
	}
	
	public void play(){
		
		poolContent = fillPoolContentFromCommonPool();
		boolean hasPlayed = false;

		playableWords = game.getDictionary().wordMaker(poolContent);
		
		String toPlay = getWordWithMostVowel();
	
		if(poolContent.length() == 0 || playableWords.size() == 0) return;
		
		
		hasPlayed = playWord(toPlay, toPlay);
		
		
		
		if(!hasPlayed) {
			poolContent = fillPoolContentFromCommonPool();
			hasPlayed = stealWordsFromPlayer();

			poolContent = fillPoolContentFromCommonPool();
			playableWords = game.getDictionary().wordMaker(poolContent);
		}

		if(hasPlayed) play();
		
	}
	
	private boolean stealWordsFromPlayer() {
		String toPlay = "" ;
		boolean hasPlayed =  false;
		for(int i = 0 ; i < game.getPlayerPool().getNumberOfElements() ; i ++) {
			
			playableWords = game.getDictionary().wordMaker(poolContent + game.getPlayerPool().getElement(i).toString());
			ownedByPlayerFilter(i);
			toPlay = getWordWithMostVowel();
			if(playWord(toPlay, toPlay.replace(game.getPlayerPool().getElement(i).toString(), ""))) {
				game.getPlayerPool().removeElement(game.getPlayerPool().getElement(i).toString());
				poolContent = fillPoolContentFromCommonPool();
				i--;
				hasPlayed = true;
			}
			
		}
		return hasPlayed;
		
	}
	
	private void ownedByPlayerFilter(int playerWordIndex) {
		
		for(int i = 0 ; i < playableWords.size() ; i ++) {
			
			if(!playableWords.get(i).contains(game.getPlayerPool().getElement(playerWordIndex).toString())
					|| playableWords.get(i).length() <= game.getPlayerPool().getElement(playerWordIndex).toString().length()) {
				
				playableWords.remove(i);
				i --;
				
			}
		
		}
		
	}

	private boolean playWord(String toPlay, String toRemove) {
		if(toPlay.length() >= 3 - game.getPlayerPool().getNumberOfElements() && toPlay.length() > 0){
			
			game.getIAPool().addElement(toPlay);
			for(int i = 0 ; i < toRemove.length() ; i ++) game.getCommonPool().removeElement(toRemove.charAt(i));
			game.getCommonPool().addElement(game.getPlayer().drawLetter());
			game.getGUI().update();
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

			return true;
		}
		else return false;
		
	}

	private String fillPoolContentFromCommonPool() {
		
		String poolContent = "";
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++) poolContent += game.getCommonPool().getElement(i);
		return poolContent;
		
	}
	
	private String getWordWithMostVowel() {
		String toPlay = "";
		
		for(String i : playableWords){
			
			if(game.getDictionary().countVowel(i) > game.getDictionary().countVowel(toPlay) && !game.getIAPool().containsWord(i)) toPlay = i;
			
		}
		return toPlay;
	}

}
