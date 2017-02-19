package com.nidras.lettergametp;

import java.util.ArrayList;

public class PlayerPool implements LetterPool<String>{
	
	private ArrayList<String> pool;
	
	public PlayerPool(){
		
		this.pool = new ArrayList<String>();
		System.out.println("");
		
	}

	public int getNumberOfElements() {
		return pool.size();
	}

	public void addElement(String element) {
		// TODO Auto-generated method stub
		pool.add(element);
		
	}

	public void removeElement(String element) {
		// TODO Auto-generated method stub
		pool.remove(pool.indexOf(element));
	}

	public String getElement(int i) {
		// TODO Auto-generated method stub
		return pool.get(i);
	}

	public boolean makeWord(String word) {
		// TODO Auto-generated method stub
		return false;
	}

}
