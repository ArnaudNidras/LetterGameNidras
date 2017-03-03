package com.nidras.lettergametp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI {
	
	private JFrame jFrame;
	private BoxLayout layout;
	private BorderLayout blayout;
	private BoxLayout tlayout;
	private JPanel panel;
	private JPanel bpanel;
	private JPanel tpanel;
	
	private JTextArea pPool;
	private JTextArea cPool;
	private JTextArea iPool;
	private JLabel logs;
	
	private JPanel bottomPanel;
	private BoxLayout bottomLayout;
	
	private JButton passButton;
	private JTextArea console;
	private JButton consoleButton;
	
	private JButton gameModeButton;
	
	private Game game;
	
	public GUI(final Game game){
		
		this.jFrame = new JFrame();
		this.panel = new JPanel();
		this.layout = new BoxLayout(panel,BoxLayout.X_AXIS);
		this.panel.setLayout(layout);
		this.blayout = new BorderLayout();
		this.bpanel = new JPanel();
		this.bpanel.setLayout(blayout);
		this.bottomPanel = new JPanel();
		this.bottomLayout = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		this.bottomPanel.setLayout(bottomLayout);
		this.tpanel = new JPanel();
		this.tlayout = new BoxLayout(tpanel, BoxLayout.X_AXIS);
		this.tpanel.setLayout(tlayout);
		this.passButton = new JButton("Passer");
		this.console = new JTextArea();
		this.consoleButton = new JButton("OK");
		this.gameModeButton = new JButton("Play against player");
		
		this.jFrame.setSize(600, 500);
		this.jFrame.setResizable(true);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pPool = new JTextArea("Player 1 Pool");
		this.cPool = new JTextArea("Common Pool");
		this.iPool = new JTextArea("IA Pool / Player 2 Pool");
		this.pPool.setEditable(false);
		this.cPool.setEditable(false);
		this.iPool.setEditable(false);
		this.logs = new JLabel("Logs here");
		
		this.pPool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.cPool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.iPool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.console.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		this.pPool.setSize(100, 100);
		
		this.panel.add(pPool);
		this.panel.add(cPool);
		this.panel.add(iPool);
		this.bottomPanel.add(passButton);
		this.bottomPanel.add(console);
		this.bottomPanel.add(consoleButton);
		this.tpanel.add(gameModeButton);
		this.tpanel.add(logs);
		this.bpanel.add(bottomPanel, BorderLayout.SOUTH);
		this.bpanel.add(tpanel, BorderLayout.NORTH);
		this.bpanel.add(panel, BorderLayout.CENTER);
		this.jFrame.add(bpanel);
		this.jFrame.setVisible(true);
		
		if(game.getPVP()) gameModeButton.setText("Play against IA");
		else gameModeButton.setText("Play against Player");
		
		this.game = game;
		
		this.passButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	game.wakeUp(false);
            	
            }
        });
		
		this.consoleButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	if(console.getText().length() != 0){
            		
            		if(console.getText().startsWith("/c ")){
            			
            			if(!game.getPVP()) game.getPlays().createWordFromCommonPoolPlayerIA(console.getText().substring(3));
            			else game.getPlays().createWordFromCommonPoolPlayerPVP(console.getText().substring(3), game.getIsPlayer1Turn());
            			
            		}
            		
            		else if(console.getText().startsWith("/s ")){
            			
            			if(!game.getPVP()) game.getPlays().createWordFromStealingPlayerIA(console.getText().substring(3));
            			else game.getPlays().createWordFromStealingPlayerPVP(console.getText().substring(3), game.getIsPlayer1Turn());
            			
            		}
            		
            		else if(console.getText().startsWith("/a ")){
            			
            			if(!game.getPVP()) game.getPlays().createWordFromAssemblingPlayerIA(console.getText().substring(3));
            			else game.getPlays().createWordFromAssemblingPlayerPVP(console.getText().substring(3), game.getIsPlayer1Turn());
            			
            		}
            		
            		else if(console.getText().startsWith("/l ")){
            			
            			if(!game.getPVP()) game.getPlays().createWordFromAddingLettersPlayerIA(console.getText().substring(3));
            			else game.getPlays().createWordFromAddingLettersPlayerPVP(console.getText().substring(3), game.getIsPlayer1Turn());
            			
            		}
            		
            		else if(console.getText().startsWith("/ana ")){
            			
            			if(!game.getPVP()) game.getPlays().createWordFromAnagramPlayerIA(console.getText().substring(5));
            			else game.getPlays().createWordFromAnagramPlayerPVP(console.getText().substring(5), game.getIsPlayer1Turn());
            			
            		}
            		
            		update();
            		
            	}
            	else logs.setText("Entrez une commande/mot ou passez");
            	
            }
            
        });
		
		this.gameModeButton.addActionListener(new ActionListener() {
	        public synchronized void actionPerformed(ActionEvent event2) {
	        	
	        	if(game.getPVP()){
	        		
	        		game.wakeUp(true);
	        		game.resetGame(false);
	        		
	        	}
	        	else{
	        		game.wakeUp(true);
	        		game.resetGame(true);
	        		
	        	}
	        	
	        }
	    });
		
	}
		
	public void pPoolAddText(String i){
		
		pPool.append(i);
		
	}
	
	public void cPoolAddText(String i){
		
		cPool.append(i);
		
	}

	public void iPoolAddText(String i){
	
		iPool.append(i);
	
	}
	
	public void pPoolSetText(String i){
		
		pPool.setText(i);
		
	}
	
	public void cPoolSetText(String i){
		
		cPool.setText(i);
		
	}

	public void iPoolSetText(String i){
	
		iPool.setText(i);
	
	}
	
	public void setLogsLabel(String i){
		
		logs.setText(i);
		
	}
	
	public void update(){
		
		cPool.setText("");
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++){
			
			if(i % 30 == 0 && i != 0) cPool.append("\n");
			
			cPool.append(game.getCommonPool().getElement(i) + " ");
			
		}
		
		pPool.setText("");
		
		for(int j = 0 ; j < game.getPlayerPool().getNumberOfElements() ; j ++){
			
			pPool.append(game.getPlayerPool().getElement(j) + "\n");
			
		}
		
		iPool.setText("");
		
		if(!game.getPVP()){
		
			for(int k = 0 ; k < game.getIAPool().getNumberOfElements() ; k ++){
				
				iPool.append(game.getIAPool().getElement(k) + "\n");
				
			}
		
		}
		else{
			
			for(int k = 0 ; k < game.getPlayer2Pool().getNumberOfElements() ; k ++){
				
				iPool.append(game.getPlayer2Pool().getElement(k) + "\n");
				
			}
			
		}
		
		console.setText("");
		
		if(game.getPVP()) gameModeButton.setText("Play against IA");
		else gameModeButton.setText("Play against Player");
		
	}
	
}
