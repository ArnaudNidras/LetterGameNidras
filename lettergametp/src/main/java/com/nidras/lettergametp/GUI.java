package com.nidras.lettergametp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUI {
	
	private JFrame jFrame;
	private BoxLayout layout;
	private BorderLayout blayout;
	private JPanel panel;
	private JPanel bpanel;
	
	private JTextArea ppool;
	private JTextArea cpool;
	private JTextArea ipool;
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
		//panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
		
		this.jFrame.setSize(600, 200);
		this.jFrame.setResizable(true);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ppool = new JTextArea("Player Pool");
		this.cpool = new JTextArea("Common Pool");
		this.ipool = new JTextArea("IA Pool");
		this.ppool.setEditable(false);
		this.cpool.setEditable(false);
		this.ipool.setEditable(false);
		this.logs = new JLabel("Logs here");
		
		this.ppool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.cpool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.ipool.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.console.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		this.ppool.setSize(100, 100);
		
		this.panel.add(ppool);
		this.panel.add(cpool);
		this.panel.add(ipool);
		this.bottomPanel.add(passButton);
		this.bottomPanel.add(console);
		this.bottomPanel.add(consoleButton);
		this.bpanel.add(bottomPanel, BorderLayout.SOUTH);
		this.bpanel.add(logs, BorderLayout.NORTH);
		this.bpanel.add(panel, BorderLayout.CENTER);
		this.jFrame.add(bpanel);
		//this.jFrame.pack();
		this.jFrame.setVisible(true);
		
		this.game = game;
		
		
		
		
		this.passButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	//synchronized(this){game.notify();}
            	game.wakeUp();
            	
            }
        });
		
		this.consoleButton.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event2) {
            	
            	//synchronized(this){game.notify();}
            	if(console.getText().length() != 0){
            		
            		if(game.getCommonPool().makeWord(console.getText()) && game.getDictionary().isInDictionary(console.getText())){
            			
            			game.getPlayerPool().addElement(console.getText());
            			for(int i = 0 ; i < console.getText().length() ; i ++) game.getCommonPool().removeElement(console.getText().charAt(i));
            			update();
            			game.wakeUp();
            			
            		}
            		else logs.setText("Le mot n'existe pas !");
            		
            	}
            	else logs.setText("Entrez une commande/mot ou passez");
            	
            }
        });
		
		
	}
	
	public void ppoolAddText(String i){
		
		ppool.append(i);
		
	}
	
	public void cpoolAddText(String i){
		
		cpool.append(i);
		
	}

	public void ipoolAddText(String i){
	
		ipool.append(i);
	
	}
	
	public void ppoolSetText(String i){
		
		ppool.setText(i);
		
	}
	
	public void cpoolSetText(String i){
		
		cpool.setText(i);
		
	}

	public void ipoolSetText(String i){
	
		ipool.setText(i);
	
	}
	
	public void setLogsLabel(String i){
		
		logs.setText(i);
		
	}
	
	public void update(){
		
		cpool.setText("");
		
		for(int i = 0 ; i < game.getCommonPool().getNumberOfElements() ; i ++){
			
			cpool.append(game.getCommonPool().getElement(i) + "\n");
			
		}
		
		ppool.setText("");
		
		for(int j = 0 ; j < game.getPlayerPool().getNumberOfElements() ; j ++){
			
			ppool.append(game.getPlayerPool().getElement(j) + "\n");
			
		}
		
		ipool.setText("");
		
		for(int k = 0 ; k < game.getIAPool().getNumberOfElements() ; k ++){
			
			ipool.append(game.getIAPool().getElement(k) + "\n");
			
		}
		
	}
	
}
