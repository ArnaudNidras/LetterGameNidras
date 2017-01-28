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
		
	}
	
	public void loadWords(){
		
		BufferedReader reader;
		String line;
		
		try {
			
			reader = new BufferedReader(new FileReader("src\\main\\dico.txt"));
			while((line = reader.readLine()) != null){
				
				line.replace('à', 'a');
				line.replace('ä', 'a');
				line.replace('â', 'a');
				line.replace('ã', 'a');
				
				line.replace('é', 'e');
				line.replace('è', 'e');
				line.replace('ë', 'e');
				line.replace('ê', 'e');
				
				line.replace('ï', 'i');
				line.replace('î', 'i');
				line.replace('ì', 'i');
				
				line.replace('ö', 'o');
				line.replace('ô', 'o');
				line.replace('ò', 'o');
				line.replace('õ', 'o');
				
				line.replace('ü', 'u');
				line.replace('û', 'u');
				line.replace('ù', 'u');
				
				line.replace('ç', 'c');
				line.replace("-", "");
				line.replace(" ", "");
				
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
					
					if(ok){
						
						temp.add(i);
						System.out.println(i);
						
					}
					
				}
				
			}
			
		}
		
		return temp;
		
	}
	
}
