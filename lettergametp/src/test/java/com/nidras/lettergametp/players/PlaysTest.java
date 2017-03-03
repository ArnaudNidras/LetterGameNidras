package com.nidras.lettergametp.players;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nidras.lettergametp.Dictionary;
import com.nidras.lettergametp.pool.CommonPool;
import com.nidras.lettergametp.pool.IAPool;
import com.nidras.lettergametp.pool.LetterPool;
import com.nidras.lettergametp.pool.PlayerPool;

public class PlaysTest {
	
	private Plays plays;
	
	@Before
	public void init(){
		
		
	}

	
	@Test
	public void createWordFromCommonPoolPlayerIATest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		
		String init = "bateau";
		
		for(int i = 0 ; i < init.length() ; i ++) cPool.addElement(init.charAt(i));
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				null, null, new Player());
		
		assertTrue("Création du mot Bateau contre l'IA", plays.createWordFromCommonPoolPlayerIA("bateau"));
		
	}
	
	@Test
	public void createWordFromCommonPoolPlayerPVPIATest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		
		String init = "bateau";
		
		for(int i = 0 ; i < init.length() ; i ++) cPool.addElement(init.charAt(i));
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				null, null, new Player());
		
		assertTrue("Création du mot Bateau contre l'ordinateur", plays.createWordFromCommonPoolPlayerPVP("bateau", true));
		
	}
	
	@Test
	public void createWordFromStealingPlayerIATest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		LetterPool<String> iPool = new IAPool();
		
		String init = "s";
		
		for(int i = 0 ; i < init.length() ; i ++) cPool.addElement(init.charAt(i));
		iPool.addElement("lapin");
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				iPool, null, new Player());
		
		assertTrue("Vol du mot lapin contre l'IA en ajoutant un s", plays.createWordFromStealingPlayerIA("lapin lapins"));
		
	}
	
	@Test
	public void createWordFromStealingPlayerPVPTest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		LetterPool<String> pPool2 = new PlayerPool();
		
		String init = "s";
		
		for(int i = 0 ; i < init.length() ; i ++) cPool.addElement(init.charAt(i));
		pPool.addElement("lapin");
		
		this.plays = new Plays(new Dictionary(), pPool, pPool2, cPool,
				null, null, new Player());
		
		assertTrue("Vol du mot lapin contre un joueur en ajoutant un s", plays.createWordFromStealingPlayerPVP("lapin lapins", false));
		
	}
	
	@Test
	public void createWordFromAssemblingPlayerIATest(){
		
		LetterPool<String> pPool = new PlayerPool();
		LetterPool<Character> cPool = new CommonPool();
		
		pPool.addElement("garde");
		pPool.addElement("manger");
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				null, null, new Player());
		
		assertTrue("Assemblage du mot gardemanger avec garde et manger contre IA (joueur fixe)", plays.createWordFromAssemblingPlayerIA("garde manger gardemanger"));
		
	}
	
	@Test
	public void createWordFromAssemblingPlayerPVPTest(){
		
		LetterPool<String> pPool2 = new PlayerPool();
		LetterPool<Character> cPool = new CommonPool();
		
		pPool2.addElement("garde");
		pPool2.addElement("manger");
		
		this.plays = new Plays(new Dictionary(), null, pPool2, cPool,
				null, null, new Player());
		
		assertTrue("Assemblage du mot gardemanger avec garde et manger joueur 2", plays.createWordFromAssemblingPlayerPVP("garde manger gardemanger", false));
		
	}
	
	@Test
	public void isThereSameAmountOfLettersTest(){
		
		this.plays = new Plays(new Dictionary(), null, null, null,
				null, null, null);
		
		assertTrue("Test vérifiant que 2 mots contiennent le même nombre de lettres, OK", plays.isThereSameAmountOfLetters("pelle", "pelle"));
		assertFalse("Test vérifiant que 2 mots contiennent le même nombre de lettres, KO", plays.isThereSameAmountOfLetters("pelle", "peele"));
		
	}
	
	@Test
	public void createWordFromAddingLettersPlayerIATest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		
		cPool.addElement('x');
		pPool.addElement("bateau");
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				null, null, new Player());
		
		assertTrue("Rallongement du mot Bateau avec un x contre l'IA", plays.createWordFromAddingLettersPlayerIA("bateau bateaux"));
		
	}
	
	@Test
	public void createWordFromAddingLettersPlayerPVPTest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool2 = new PlayerPool();
		
		cPool.addElement('x');
		pPool2.addElement("bateau");
		
		this.plays = new Plays(new Dictionary(), null, pPool2, cPool,
				null, null, new Player());
		
		assertTrue("Rallongement du mot Bateau avec un x contre l'IA", plays.createWordFromAddingLettersPlayerPVP("bateau bateaux", false));
		
	}
	
	@Test
	public void createWordFromAnagramPlayerIATest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		LetterPool<String> iPool = new IAPool();
		
		iPool.addElement("voile");
		
		this.plays = new Plays(new Dictionary(), pPool, null, cPool,
				iPool, null, new Player());
		
		assertTrue("Vol du mot voile avec anagramme vers olive depuis l'IA", plays.createWordFromAnagramPlayerIA("ADV voile olive"));
		assertTrue("Anagramme du mot olive vers viole depuis le pool du joueur", plays.createWordFromAnagramPlayerIA("MOI olive viole"));
		
	}
	
	@Test
	public void createWordFromAnagramPlayerPVPTest(){
		
		LetterPool<Character> cPool = new CommonPool();
		LetterPool<String> pPool = new PlayerPool();
		LetterPool<String> pPool2 = new PlayerPool();
		
		pPool.addElement("voile");
		
		this.plays = new Plays(new Dictionary(), pPool, pPool2, cPool,
				null, null, new Player());
		
		assertTrue("Vol du mot voile avec anagramme vers olive depuis le joueur 1", plays.createWordFromAnagramPlayerPVP("ADV voile olive", false));
		assertTrue("Anagramme du mot olive vers viole depuis le pool du joueur 2", plays.createWordFromAnagramPlayerPVP("MOI olive viole", false));
		
	}
	
}
