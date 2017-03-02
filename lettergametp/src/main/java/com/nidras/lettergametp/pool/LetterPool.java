package com.nidras.lettergametp.pool;

public interface LetterPool<T> {
	
	public int getNumberOfElements();
	
	public void addElement(T element);
	
	public T getElement(int i);
	
	public void removeElement(T element);
	
	public boolean makeWord(String word);
	
	public boolean containsWord(String word);
	
	public String concatPool();

}
