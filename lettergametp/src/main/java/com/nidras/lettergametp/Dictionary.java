package com.nidras.lettergametp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Dictionary {
	
	private ArrayList<String> words;
	
	public Dictionary(){
		
		this.words = new ArrayList<String>();
		loadWords();
		removeOccurrence();
		
		ArrayList<String> test = wordMaker("cannelle");
		
	}
	
	public void loadWords(){
		
		BufferedReader reader;
		String line;
		
		try {
			
			reader = new BufferedReader(new FileReader("src\\main\\dico.txt"));
			while((line = reader.readLine()) != null){
				
				line = line.replace('à', 'a');
				line = line.replace('ä', 'a');
				line = line.replace('â', 'a');
				line = line.replace('ã', 'a');
				
				line = line.replace('é', 'e');
				line = line.replace('è', 'e');
				line = line.replace('ë', 'e');
				line = line.replace('ê', 'e');
				
				line = line.replace('ï', 'i');
				line = line.replace('î', 'i');
				line = line.replace('ì', 'i');
				
				line = line.replace('ö', 'o');
				line = line.replace('ô', 'o');
				line = line.replace('ò', 'o');
				line = line.replace('õ', 'o');
				
				line = line.replace('ü', 'u');
				line = line.replace('û', 'u');
				line = line.replace('ù', 'u');
				
				line = line.replace('ç', 'c');
				line = line.replace("-", "");
				line = line.replace(" ", "");
				
				words.add(line);
				
			}
	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void removeOccurrence(){
		
		Set<String> temp = new LinkedHashSet<String>();
		temp.addAll(words);
		words.clear();
		words.addAll(temp);
		
	}
	
	public ArrayList<String> getWords(){
		
		return words;
		
	}
	
	public boolean isInDictionary(String word){
		
		if(words.contains(word)) return true;
		
		return false;
		
	}
	
	public ArrayList<String> anagramMaker(String word){
		
		ArrayList<String> temp = new ArrayList<String>() ;
		
		for(String i : words){
			
			if(i.length() == word.length()){
				
				if(!i.equals(word)){
					
					boolean ok = true;
					
					for(int j = 0 ; j < word.length() ; j ++){
						
						if(!i.contains(String.valueOf(word.charAt(j)))) ok = false;
						
					}
					
					if(ok && isThereSameAmountOfLetters(i, word)){
						
						temp.add(i);
						System.out.println(i);
						
					}
					
				}
				
			}
			
		}
		
		return temp;
		
	}
	
	public ArrayList<String> wordMaker(String word){
		
		ArrayList<String> temp = new ArrayList<String>() ;
		
		for(String i : words){
			
			if(i.length() <= word.length()){
					
				boolean ok = true;
					
				for(int j = 0 ; j < i.length() ; j ++){
						
					if(!word.contains(String.valueOf(i.charAt(j)))) ok = false;
						
				}
					
				if(ok && isThereSameOrMoreAmountOfLetters(word, i)){
						
					temp.add(i);
						
				}
				
			}
			
		}
		
		return temp;
		
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
	
	public boolean isThereSameOrMoreAmountOfLetters(String a, String b){
		
		int[] counter = new int[26];
		
		for(int count = 0 ; count < 26 ; count ++) counter[count] = 0;
		
		for(int i = 0 ; i < a.length() ; i ++) counter[((int) a.charAt(i)-97)] ++;
		
		for(int j = 0 ; j < b.length() ; j ++) counter[((int) b.charAt(j)-97)] --;
		
		for(int check = 0 ; check < 26 ; check ++){
			
			if(counter[check] < 0) return false;
			
		}
		
		return true;
		
	}
	
	public int countVowel(String word){
		
		int count = 0;
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
				|| word.charAt(i) == 'u' || word.charAt(i) == 'y') count ++; 
			
		}
		
		return count;
		
	}
	
}
