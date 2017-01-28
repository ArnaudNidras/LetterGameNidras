package com.nidras.lettergametp;

public class Game {
	
	private Dictionary dictionary;
	private Player player;
	private IA ia;
	
	public Game(Dictionary dictionary, Player player, IA ia){
		
		this.dictionary = dictionary;
		this.player = player;
		this.ia = ia;
		
	}

}
