package com.nidras.lettergametp.pool;

import java.util.ArrayList;

public class CommonPool implements LetterPool<Character>{
	
	ArrayList<Character> pool;
	
	public CommonPool(){
		
		this.pool = new ArrayList<Character>();
		
	}

	public int getNumberOfElements() {
		
		return pool.size();
		
	}

	public void addElement(Character element) {
		
		pool.add(element);
		
	}
	
	public Character getElement(int i){
		
		return pool.get(i);
		
	}

	public void removeElement(Character element) {
		
		pool.remove(pool.indexOf(element));
		
	}
	
	public boolean makeWord(String word){
		
		if(word.length() <= pool.size()){
			
			for(int i = 0 ; i < word.length() ; i ++){
				
				if(pool.contains(word.charAt(i)) == false){
					
					return false;
					
				}
				
			}
			
			return true;
			
		}
		
		return false;
		
	}

	public boolean containsWord(String word) {
		
		int[] counter = new int[26];
		
		for(int count = 0 ; count < 26 ; count ++) counter[count] = 0;
		
		for(int i = 0 ; i < pool.size() ; i ++){
			
			counter[((int) pool.get(i)-97)] ++;
			
		}
		
		for(int j = 0 ; j < word.length() ; j ++){
			
			counter[((int) word.charAt(j)-97)] --;
			
		}
		
		for(int check = 0 ; check < 26 ; check ++){
			
			if(counter[check] != 0) return false;
			
		}
		
		return true;
		
	}

	public String concatPool() {
		
		String toReturn = "";
		
		for(Character i : pool){
			
			toReturn += i;
		}
		
		return toReturn;
	}

}
