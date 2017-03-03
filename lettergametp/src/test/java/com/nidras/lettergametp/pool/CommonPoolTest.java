package com.nidras.lettergametp.pool;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommonPoolTest {

	private LetterPool<Character> cPool;
	
	@Before
	public void init(){
		
		this.cPool = new CommonPool();
		
	}
	
	@Test
	public void makeWordTest(){
		
		String temp = "azer";
		
		for(int i = 0 ; i < temp.length() ; i ++) cPool.addElement(temp.charAt(i));
		
		assertTrue("Est-ce que le pool commun contient les lettres pour le mot aze ?  Vrai", cPool.makeWord("aze"));
		assertFalse("Est-ce que le pool commun contient les lettres pour le mot rekt ? Faux", cPool.makeWord("rekt"));
		
		for(int j = 0 ; j < temp.length() ; j ++) cPool.removeElement(temp.charAt(j));
		
	}
	
	@Test
	public void containsWordTest(){
		
		String temp = "azerty";
		
		for(int i = 0 ; i < temp.length() ; i ++) cPool.addElement(temp.charAt(i));
		
		assertTrue("Est-ce que le pool commun et le mot azerty ont le même nombre de lettres ?  Vrai", cPool.containsWord("azerty"));
		assertFalse("Est-ce que le pool commun et le mot hazairetie ont le même nombre de lettres ? Faux", cPool.containsWord("hazairetie"));
		
		for(int j = 0 ; j < temp.length() ; j ++) cPool.removeElement(temp.charAt(j));
		
	}
	
	@Test
	public void concatPoolTest(){
		
		String temp = "azerty";
		
		for(int i = 0 ; i < temp.length() ; i ++) cPool.addElement(temp.charAt(i));
		
		assertEquals("Est-ce que le contenue du pool commun forme le mot azerty ?  Vrai", cPool.concatPool(), "azerty");
		assertNotEquals("Est-ce que le contenue du pool commun forme le mot hazairetie ? Faux", cPool.concatPool(), "hazairetie");
		
	}
	
}
