package com.nidras.lettergametp.players;

import java.util.ArrayList;

import com.nidras.lettergametp.Dictionary;
import com.nidras.lettergametp.GUI;
import com.nidras.lettergametp.pool.LetterPool;

public class Plays {
	
	private Dictionary dictionary;
	private LetterPool<String> pPool;
	private LetterPool<Character> cPool;
	private LetterPool<String> iPool;
	private GUI gui;
	private Player player;
	private ArrayList<String> usedAnagrams;
	
	public Plays(Dictionary dictionary, LetterPool<String> pPool, LetterPool<Character> cPool, LetterPool<String> iPool, GUI gui, Player player){
		
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
				
				if(!pPool.containsWord(words[0])){
				
					if(iPool.containsWord(words[0])){
						
						if(words[0].length() < words[1].length()){
						
							if(cPool.makeWord(words[1].replace(words[0], "")) && dictionary.isThereSameOrMoreAmountOfLetters(cPool.concatPool(), words[1].replace(words[0], ""))){
								
								iPool.removeElement(words[0]);
								
								pPool.addElement(words[1]);
			        			for(int k = 0 ; k < words[1].replace(words[0], "").length() ; k ++) cPool.removeElement(words[1].replace(words[0], "").charAt(k));
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
				
				if(words[0].length() < words[1].length()){
				
					if(!pPool.containsWord(words[1])){
	
						String temp = words[1].replace(words[0], "");
						
						if(cPool.makeWord(temp) && dictionary.isThereSameOrMoreAmountOfLetters(cPool.concatPool(), temp)){
							
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
				else gui.setLogsLabel("Il faut rajouter des lettres pour ralonger ce mot !");
				
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
	
	public ArrayList<String> getUsedAnagrams(){
		
		return usedAnagrams;
		
	}

}
