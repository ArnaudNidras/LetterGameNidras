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
	private JPanel panel;
	private JPanel bpanel;
	
	private JTextArea pPool;
	private JTextArea cPool;
	private JTextArea iPool;
	private JLabel logs;
	
	private JPanel bottomPanel;
	private BoxLayout bottomLayout;
	
	private JButton passButton;
	private JTextArea console;
	private JButton consoleButton;
	
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
		this.passButton = new JButton("Passer");
		this.console = new JTextArea();
		this.consoleButton = new JButton("OK");
		
		this.jFrame.setSize(600, 500);
		this.jFrame.setResizable(true);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pPool = new JTextArea("Player Pool");
		this.cPool = new JTextArea("Common Pool");
		this.iPool = new JTextArea("IA Pool");
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
		this.bpanel.add(bottomPanel, BorderLayout.SOUTH);
		this.bpanel.add(logs, BorderLayout.NORTH);
		this.bpanel.add(panel, BorderLayout.CENTER);
		this.jFrame.add(bpanel);
		this.jFrame.setVisible(true);
		
		this.game = game;
		
		this.passButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	game.wakeUp();
            	
            }
        });
		
		this.consoleButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	if(console.getText().length() != 0){
            		
            		if(console.getText().startsWith("/c ")){
            			
            			if(game.getPlays().createWordFromCommonPoolPlayer(console.getText().substring(3))) /*game.wakeUp()*/;
            			
            		}
            		
            		else if(console.getText().startsWith("/s ")){
            			
            			if(game.getPlays().createWordFromStealingPlayer(console.getText().substring(3))) /*game.wakeUp()*/;
            			
            		}
            		
            		else if(console.getText().startsWith("/a ")){
            			
            			if(game.getPlays().createWordFromAssemblingPlayer(console.getText().substring(3))) /*game.wakeUp()*/;
            			
            		}
            		
            		else if(console.getText().startsWith("/l ")){
            			
            			if(game.getPlays().createWordFromAddingLettersPlayer(console.getText().substring(3))) /*game.wakeUp()*/;
            			
            		}
            		
            		else if(console.getText().startsWith("/ana ")){
            			
            			if(game.getPlays().createWordFromAnagramPlayer(console.getText().substring(5))) /*game.wakeUp()*/;
            			
            		}
            		
            	}
            	else logs.setText("Entrez une commande/mot ou passez");
            	
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
		
		for(int k = 0 ; k < game.getIAPool().getNumberOfElements() ; k ++){
			
			iPool.append(game.getIAPool().getElement(k) + "\n");
			
		}
		
		console.setText("");
		
	}
	
}
