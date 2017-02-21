package com.nidras.lettergametp;

import java.util.ArrayList;

public class Plays {
	
	private Dictionary dictionary;
	private LetterPool pPool;
	private LetterPool cPool;
	private LetterPool iPool;
	private GUI gui;
	private Player player;
	private ArrayList<String> usedAnagrams;
	
	public Plays(Dictionary dictionary, LetterPool pPool, LetterPool cPool, LetterPool iPool, GUI gui, Player player){
		
		this.dictionary = dictionary;
		this.pPool = pPool;
		this.cPool = cPool;
		this.iPool = iPool;
		this.gui = gui;
		this.player = player;
		this.usedAnagrams = new ArrayList<String>();
		
	}
	
	public boolean createWordFromCommonPoolPlayer(String word){
		
		if(cPool.makeWord(word)){
			
			if(dictionary.isInDictionary(word)){
				
				if(!pPool.containsWord(word)){
			
					pPool.addElement(word);
        			for(int i = 0 ; i < word.length() ; i ++) cPool.removeElement(word.charAt(i));
        			cPool.addElement(player.drawLetter());
        			gui.update();
        			
    			
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Il manque des lettres !");
		
		return false;
		
	}	
	
	public boolean createWordFromStealingPlayer(String word){
		
		if(word.contains(" ") && (word.length() - word.substring(0, word.indexOf(' ')).length()) > 1){
		
			String beg = word.substring(0, word.indexOf(' '));
			String end = word.substring(word.indexOf(' ') + 1);
			
			if(dictionary.isInDictionary(end)){
				
				if(!pPool.containsWord(end)){
				
					if(iPool.containsWord(beg)){
						
						if(beg.length() < end.length()){
						
							if(cPool.makeWord(end.replace(beg, ""))){
								
								iPool.removeElement(beg);
								
								pPool.addElement(end);
			        			for(int k = 0 ; k < end.replace(beg, "").length() ; k ++) cPool.removeElement(end.replace(beg, "").charAt(k));
			        			cPool.addElement(player.drawLetter());
			        			gui.update();
			        			
			        			return true;
								
							}
							else gui.setLogsLabel("Il n'y a pas assez de lettres pour voler ce mot !");
						
						}
						else gui.setLogsLabel("Il faut rajouter des lettres pour voler ce mot !");
						
					}
					else gui.setLogsLabel("Impossible de voler ce mot !");
				
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession !");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
		
		}
		else gui.setLogsLabel("Mauvaise syntaxe !");
		
		return false;
		
	}
	
	public boolean createWordFromAssemblingPlayer(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[3];
		
		words[0] = words[1] = words[2] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 2 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[2])){
				
				if(pPool.containsWord(words[0]) && pPool.containsWord(words[1])){
					
					if(words[0].length() + words[1].length() == words[2].length()){
						
						if(isThereSameAmountOfLetters(words[0] + words[1], words[2])){
							
							pPool.addElement(words[2]);
							pPool.removeElement(words[0]);
							pPool.removeElement(words[1]);
							
							cPool.addElement(player.drawLetter());
		        			gui.update();
		        			
		        			return true;
							
						}
						else gui.setLogsLabel("Il n'y a pas le même nombre de lettres utilisées");
						
					}
					else gui.setLogsLabel("L'intégralité des deux mots doit être utilisée !");
					
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession !");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Mauvaise syntaxe !");
		
		return false;
		
	}
	
	public boolean isThereSameAmountOfLetters(String a, String b){
		
		int[] counter = new int[26];
		
		for(int count = 0 ; count < 26 ; count ++) counter[count] = 0;
		
		for(int i = 0 ; i < a.length() ; i ++){
			
			counter[((int) a.charAt(i)-97)] ++;
			
		}
		
		for(int j = 0 ; j < b.length() ; j ++){
			
			counter[((int) b.charAt(j)-97)] --;
			
		}
		
		for(int check = 0 ; check < 26 ; check ++){
			
			if(counter[check] != 0) return false;
			
		}
		
		return true;
		
	}
	
	public boolean createWordFromAddingLettersPlayer(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[2];
		
		words[0] = words[1] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[1])){
				
				if(!pPool.containsWord(words[1])){

					String temp = words[1];
					for(int j = 0 ; j < words[0].length() ; j ++){
						
						temp = temp.replace(words[0].substring(j, j+1), "");
						
					}
					
					if(cPool.makeWord(temp)){
						
						pPool.addElement(words[1]);
						pPool.removeElement(words[0]);
	        			for(int j = 0 ; j < temp.length() ; j ++) cPool.removeElement(temp.charAt(j));
	        			cPool.addElement(player.drawLetter());
	        			gui.update();
	        			
	        			return true;
						
					}
					else gui.setLogsLabel("Il n'y a pas assez de lettres !");
					
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession !");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Mauvaise syntaxe !");
		
		return false;
		
	}
	
	public boolean createWordFromAnagramPlayer(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[2];
		
		words[0] = words[1] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[1])){
				
				if(!pPool.containsWord(words[1])){
					
					if(dictionary.anagramMaker(words[0]).contains(words[1])){
					
						if(!usedAnagrams.contains(words[1])){
							
							pPool.addElement(words[1]);
							pPool.removeElement(words[0]);
							usedAnagrams.add(words[0]);
							cPool.addElement(player.drawLetter());
		        			gui.update();
		        			
		        			return true;
							
						}
						else gui.setLogsLabel("L'anagramme a déjà été joué !");
						
					}
					else gui.setLogsLabel("Impossible de créer l'anagramme !");	
					
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession !");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Mauvaise syntaxe !");
		
		return false;
		
	}	

}
