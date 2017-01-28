package com.nidras.lettergametp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {

	private Dictionary dico;
	
	@Before
	public void init(){
		
		this.dico = new Dictionary();
		
	}
	
	@Test
	public void sourceDictionaryLengthTest(){
		
		BufferedReader reader;
		String line;
		try {
			
			reader = new BufferedReader(new FileReader("src\\main\\dico.txt"));
			line = reader.readLine();
			assertEquals("a", line);
			
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			fail("Pas de fichier");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			fail("Fichier non lu");
			
		}
		
	}
	
	@Test
	public void dictionaryLengthTest(){
		
		assertTrue("Longueur du dictionnaire importé", dico.getWords().size() != 0);
		
	}
	
	@Test
	public void dictionaryContentTest(){
		
		assertFalse("Recherche de azrt dans le dictionnaire", dico.isInDictionary("azrt") == true);
		
	}
	
	@Test
	public void anagramResearch(){
		
		assertTrue("Recherche de l'anagramme de olive (voile)", dico.anagramMaker("olive").size() > 0);
		assertFalse("Recherche de l'anagremme de feu (inexistant)", dico.anagramMaker("feu").size() > 0);
		
	}

}
