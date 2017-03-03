package com.nidras.lettergametp.players;

import java.util.ArrayList;

import com.nidras.lettergametp.Dictionary;
import com.nidras.lettergametp.GUI;
import com.nidras.lettergametp.pool.LetterPool;

public class Plays {
	
	private Dictionary dictionary;
	private LetterPool<String> pPool;
	private LetterPool<String> pPool2;
	private LetterPool<Character> cPool;
	private LetterPool<String> iPool;
	private GUI gui;
	private Player player;
	private ArrayList<String> usedAnagrams;
	
	public Plays(Dictionary dictionary, LetterPool<String> pPool, LetterPool<String> pPool2, LetterPool<Character> cPool, LetterPool<String> iPool, GUI gui, Player player){
		
		this.dictionary = dictionary;
		this.pPool = pPool;
		this.pPool2 = pPool2;
		this.cPool = cPool;
		this.iPool = iPool;
		this.gui = gui;
		this.player = player;
		this.usedAnagrams = new ArrayList<String>();
		
	}
	
	public boolean createWordFromCommonPoolPlayerIA(String word){
		
		if(cPool.makeWord(word)){
			
			if(dictionary.isInDictionary(word)){
				
				if(!pPool.containsWord(word)){
			
					pPool.addElement(word);
        			for(int i = 0 ; i < word.length() ; i ++) cPool.removeElement(word.charAt(i));
        			cPool.addElement(player.drawLetter());
        			
        			return true;
        			
				}
				else gui.setLogsLabel("Le mot est déjà en votre possession");
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Il manque des lettres !");
		
		return false;
		
	}
	
	public boolean createWordFromCommonPoolPlayerPVP(String word, boolean isPlayer1Turn){
		
		if(cPool.makeWord(word)){
			
			if(dictionary.isInDictionary(word)){
				
				if(isPlayer1Turn){
				
					if(!pPool.containsWord(word)){
				
						pPool.addElement(word);
	        			for(int i = 0 ; i < word.length() ; i ++) cPool.removeElement(word.charAt(i));
	        			cPool.addElement(player.drawLetter());
	        			
	        			return true;
	    			
					}
					else gui.setLogsLabel("Le mot est déjà en votre possession");
				
				}
				else{
					
					if(!pPool2.containsWord(word)){
						
						pPool2.addElement(word);
	        			for(int i = 0 ; i < word.length() ; i ++) cPool.removeElement(word.charAt(i));
	        			cPool.addElement(player.drawLetter());
	        			
	        			return true;
	    			
					}
					else gui.setLogsLabel("Le mot est déjà en votre possession");
					
				}
				
			}
			else gui.setLogsLabel("Le mot n'existe pas !");
			
		}
		else gui.setLogsLabel("Il manque des lettres !");
		
		return false;
		
	}
	
	public boolean createWordFromStealingPlayerIA(String word){
		
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
				
					if(iPool.containsWord(words[0])){
						
						if(words[0].length() < words[1].length()){
						
							if(cPool.makeWord(words[1].replace(words[0], "")) && dictionary.isThereSameOrMoreAmountOfLetters(cPool.concatPool(), words[1].replace(words[0], ""))){
								
								iPool.removeElement(words[0]);
								
								pPool.addElement(words[1]);
			        			for(int k = 0 ; k < words[1].replace(words[0], "").length() ; k ++) cPool.removeElement(words[1].replace(words[0], "").charAt(k));
			        			cPool.addElement(player.drawLetter());
			        			
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
	
	public boolean createWordFromStealingPlayerPVP(String word, boolean isPlayer1Turn){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[2];
		
		words[0] = words[1] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		LetterPool<String> pool1;
		LetterPool<String> pool2;
		
		if(isPlayer1Turn){
			
			pool1 = pPool;
			pool2 = pPool2;

		}
		else{
			
			pool1 = pPool2;
			pool2 = pPool;
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[1])){
				
				if(!pool1.containsWord(words[1])){
				
					if(pool2.containsWord(words[0])){
						
						if(words[0].length() < words[1].length()){
						
							if(cPool.makeWord(words[1].replace(words[0], "")) && dictionary.isThereSameOrMoreAmountOfLetters(cPool.concatPool(), words[1].replace(words[0], ""))){
								
								pool2.removeElement(words[0]);
								
								pool1.addElement(words[1]);
			        			for(int k = 0 ; k < words[1].replace(words[0], "").length() ; k ++) cPool.removeElement(words[1].replace(words[0], "").charAt(k));
			        			cPool.addElement(player.drawLetter());
			        			
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
	
	public boolean createWordFromAssemblingPlayerIA(String word){
		
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
	
	public boolean createWordFromAssemblingPlayerPVP(String word, boolean isPlayer1Turn){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[3];
		
		words[0] = words[1] = words[2] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 2 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		LetterPool<String> pool;
		
		if(isPlayer1Turn) pool = pPool;
		else pool = pPool2;
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[2])){
				
				if(pool.containsWord(words[0]) && pool.containsWord(words[1])){
					
					if(words[0].length() + words[1].length() == words[2].length()){
						
						if(isThereSameAmountOfLetters(words[0] + words[1], words[2])){
							
							pool.addElement(words[2]);
							pool.removeElement(words[0]);
							pool.removeElement(words[1]);
							
							cPool.addElement(player.drawLetter());
		        			
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
	
	public boolean createWordFromAddingLettersPlayerIA(String word){
		
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
	
	public boolean createWordFromAddingLettersPlayerPVP(String word, boolean isPlayer1Turn){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[2];
		
		words[0] = words[1] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		LetterPool<String> pool;
		
		if(isPlayer1Turn) pool = pPool;
		else pool = pPool2;
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[1])){
				
				if(words[0].length() < words[1].length()){
				
					if(!pool.containsWord(words[1])){
	
						String temp = words[1].replace(words[0], "");
						
						if(cPool.makeWord(temp) && dictionary.isThereSameOrMoreAmountOfLetters(cPool.concatPool(), temp)){
							
							pool.addElement(words[1]);
							pool.removeElement(words[0]);
		        			for(int j = 0 ; j < temp.length() ; j ++) cPool.removeElement(temp.charAt(j));
		        			cPool.addElement(player.drawLetter());
		        			
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
	
	public boolean createWordFromAnagramPlayerIA(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[3];
		
		words[0] = words[1] = words[2] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[2])){
				
				if(!pPool.containsWord(words[2])){
					
					if(dictionary.anagramMaker(words[1]).contains(words[2])){
					
						if(!usedAnagrams.contains(words[2])){
							
							if(iPool.containsWord(words[1]) && words[0].equals("ADV")){
								
								pPool.addElement(words[2]);
								iPool.removeElement(words[1]);
								usedAnagrams.add(words[1]);
								cPool.addElement(player.drawLetter());
			        			
			        			return true;
								
							}
							else if(pPool.containsWord(words[1]) && words[0].equals("MOI")){
								
								pPool.addElement(words[2]);
								pPool.removeElement(words[1]);
								usedAnagrams.add(words[1]);
								cPool.addElement(player.drawLetter());
			        			
			        			return true;
								
							}
							else gui.setLogsLabel("Impossible de trouver le mot à changer !");
							
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

	public boolean createWordFromAnagramPlayerPVP(String word, boolean isPlayer1Turn){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[3];
		
		words[0] = words[1] = words[2] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		LetterPool<String> pool1;
		LetterPool<String> pool2;
		
		if(isPlayer1Turn){
			
			pool1 = pPool;
			pool2 = pPool2;

		}
		else{
			
			pool1 = pPool2;
			pool2 = pPool;
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(dictionary.isInDictionary(words[2])){
				
				if(!pool1.containsWord(words[2])){
					
					if(dictionary.anagramMaker(words[1]).contains(words[2])){
					
						if(!usedAnagrams.contains(words[2])){
							
							if(pool2.containsWord(words[1]) && words[0].equals("ADV")){
								
								pool1.addElement(words[2]);
								pool2.removeElement(words[1]);
								usedAnagrams.add(words[1]);
								cPool.addElement(player.drawLetter());
			        			
			        			return true;
								
							}
							else if(pool1.containsWord(words[1]) && words[0].equals("MOI")){
								
								pool1.addElement(words[2]);
								pool1.removeElement(words[1]);
								usedAnagrams.add(words[1]);
								cPool.addElement(player.drawLetter());
			        			
			        			return true;
								
							}
							else gui.setLogsLabel("Impossible de trouver le mot à changer !");
							
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
