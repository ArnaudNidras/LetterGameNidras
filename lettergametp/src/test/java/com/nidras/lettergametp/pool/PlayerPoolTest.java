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
		
		assertTrue("Est-ce que le mot azerty contient un mot du pool ?  Vrai", pPool.makeWord("azer"));
		assertFalse("Est-ce que le mot poulet contient un mot du pool ? Faux", pPool.makeWord("poulet"));
		
	}
	
}
