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
		
		assertTrue("Recherche de poulet dans le dictionnaire", dico.isInDictionary("poulet"));
		assertFalse("Recherche de azrt dans le dictionnaire", dico.isInDictionary("azrt"));
		
	}
	
	@Test
	public void anagramResearchTest(){
		
		assertFalse("Recherche de l'anagremme de feu (inexistant)", dico.anagramMaker("feu").size() > 0);
		assertTrue("Recherche de l'anagramme de olive (voile)", dico.anagramMaker("olive").contains("voile"));
		
	}
	
	@Test
	public void wordMakerTest(){
		
		assertTrue("Création d'un mot avec efu (feu)", dico.wordMaker("efu").size() > 0);
		
	}
	
	@Test
	public void isThereSameAmountOfLettersTest(){
		
		assertTrue("Vérification nombre de lettres entre azerty et ytreza", dico.isThereSameAmountOfLetters("azerty", "ytreza"));
		assertFalse("Vérification nombre de lettres entre pale et palle (faux)", dico.isThereSameAmountOfLetters("pale", "palle"));
		
	}
	
	@Test
	public void isThereSameOrMoreAmountOfLettersTest(){
		
		assertTrue("Toutes les lettres d'azerty sont elles contenues dans ytreza ? Vrai", dico.isThereSameAmountOfLetters("azerty", "ytreza"));
		assertFalse("Toutes les lettres de pile sont elles contenues dans pales ? Faux", dico.isThereSameAmountOfLetters("pile", "pales"));
		
	}
	
	@Test
	public void countVowelsTest(){
		
		assertEquals(dico.countVowel("azerty"), 3);
		
	}
	
}
