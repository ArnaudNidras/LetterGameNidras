package com.nidras.lettergametp.players;

import java.util.ArrayList;
import java.util.Collections;

import com.nidras.lettergametp.pool.LetterPool;
import com.nidras.lettergametp.pool.PlayerPool;

public class Player {

	private LetterPool<String> playerPool;
	private ArrayList<Character> allowedChar;
	
	public Player(){
		
		this.playerPool = new PlayerPool();
		
		this.allowedChar = new ArrayList<Character>();
		initAllowedChar();
		
	}
	 
	public LetterPool<String> getPlayerPool(){
		
		return this.playerPool;
		
	}
	
	public void initAllowedChar(){
		
		for(int i = 0 ; i < 26 ; i ++) allowedChar.add((char)(97+i));
		
		allowedChar.add('a');
		allowedChar.add('a');
		//allowedChar.add('a');
		allowedChar.add('e');
		allowedChar.add('e');
		//allowedChar.add('e');
		allowedChar.add('i');
		allowedChar.add('i');
		//allowedChar.add('i');
		allowedChar.add('o');
		allowedChar.add('o');
		//allowedChar.add('o');
		allowedChar.add('u');
		allowedChar.add('u');
		//allowedChar.add('u');
		
	}
	
	public Character drawLetter(){
		
		Collections.shuffle(allowedChar);
		return allowedChar.get(0);
		
	}
	
}
