package com.nidras.lettergametp.pool;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nidras.lettergametp.pool.LetterPool;
import com.nidras.lettergametp.pool.PlayerPool;

public class PlayerPoolTest {

	private LetterPool<String> pPool;
	
	@Before
	public void init(){
		
		this.pPool = new PlayerPool();
		
	}
	
	@Test
	public void makeWordTest(){
		
		pPool.addElement("azer");
		
		assertTrue("Est-ce que le mot azerty contient un mot du pool joueur ?  Vrai", pPool.makeWord("azerty"));
		assertFalse("Est-ce que le mot poulet contient un mot du pool joueur ? Faux", pPool.makeWord("poulet"));
		
		pPool.removeElement("azer");
		
	}
	
	@Test
	public void containsWord(){
		
		pPool.addElement("azer");
		
		assertTrue("Le pool du joueur contient-il le mot azer ? Vrai", pPool.containsWord("azer"));
		
		pPool.removeElement("azer");
		
	}
	
	@Test
	public void concatPoolTest(){
		
		pPool.addElement("azer");
		pPool.addElement("ty");
		
		assertEquals("Est-ce que la concaténation des mots (azer,ty) du pool donne azerty ?  Vrai", pPool.concatPool(), "azerty");
		assertNotEquals("Est-ce que la concaténation des mots du pool(azer,ty) donne azairti ?  Faux", pPool.concatPool(), "azairti");
		
	}
	
}
