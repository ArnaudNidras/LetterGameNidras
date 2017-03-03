package com.nidras.lettergametp.pool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class IAPoolTest {

	private LetterPool<String> iPool;
	
	@Before
	public void init(){
		
		this.iPool = new IAPool();
		
	}
	
	@Test
	public void makeWordTest(){
		
		iPool.addElement("azer");
		
		assertTrue("Est-ce que le mot azerty contient un mot du pool IA ?  Vrai", iPool.makeWord("azerty"));
		assertFalse("Est-ce que le mot poulet contient un mot du pool IA ? Faux", iPool.makeWord("poulet"));
		
		iPool.removeElement("azer");
		
	}
	
	@Test
	public void containsWord(){
		
		iPool.addElement("azer");
		
		assertTrue("Le pool de l'IA contient-il le mot azer ? Vrai", iPool.containsWord("azer"));
		
		iPool.removeElement("azer");
		
	}
	
	@Test
	public void concatPoolTest(){
		
		iPool.addElement("azer");
		iPool.addElement("ty");
		
		assertEquals("Est-ce que la concaténation des mots (azer,ty) du pool donne azerty ?  Vrai", iPool.concatPool(), "azerty");
		assertNotEquals("Est-ce que la concaténation des mots du pool(azer,ty) donne azairti ?  Faux", iPool.concatPool(), "azairti");
		
	}
	
}
