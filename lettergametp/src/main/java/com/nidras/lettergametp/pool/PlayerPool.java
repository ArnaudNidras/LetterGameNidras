package com.nidras.lettergametp.pool;

import java.util.ArrayList;

public class PlayerPool implements LetterPool<String>{
	
	private ArrayList<String> pool;
	
	public PlayerPool(){
		
		this.pool = new ArrayList<String>();
		
	}

	public int getNumberOfElements() {
		
		return pool.size();
	}

	public void addElement(String element) {
		
		pool.add(element);
		
	}

	public void removeElement(String element) {
		
		pool.remove(pool.indexOf(element));
		
	}

	public String getElement(int i) {
		
		return pool.get(i);
		
	}

	public boolean makeWord(String word) {
			
		for(int i = 0 ; i < pool.size() ; i ++){
				
			if(word.contains(pool.get(i))){
					
				return true;
					
			}
				
		}
			
		return false;
		
	}

	public boolean containsWord(String word) {
		
		if(pool.contains(word)) return true;
		
		return false;
		
	}

}
