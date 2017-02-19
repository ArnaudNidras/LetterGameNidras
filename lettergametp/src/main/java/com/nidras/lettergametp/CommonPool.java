package com.nidras.lettergametp;

import java.util.ArrayList;

public class CommonPool implements LetterPool<Character>{
	
	ArrayList<Character> pool;
	
	public CommonPool(){
		
		this.pool = new ArrayList<Character>();
		System.out.println("");
		
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
		// TODO Auto-generated method stub
		return false;
	}

}
