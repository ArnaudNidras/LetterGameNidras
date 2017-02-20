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
		
		this.jFrame.setSize(600, 500);
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
            			
            			if(createWordFromCommonPool(console.getText().substring(3))) game.wakeUp();
            			
            		}
            		
            		else if(console.getText().startsWith("/s ")){
            			
            			if(createWordFromStealing(console.getText().substring(3))) game.wakeUp();
            			
            		}
            		
            		else if(console.getText().startsWith("/a ")){
            			
            			if(createWordFromAssembling(console.getText().substring(3))) game.wakeUp();
            			
            		}
            		
            		else if(console.getText().startsWith("/l ")){
            			
            			if(createWordFromAddingLetters(console.getText().substring(3))) game.wakeUp();
            			
            		}
            		
            		
            		
            	}
            	else logs.setText("Entrez une commande/mot ou passez");
            	
            }
            
        });
		
		
	}
	
	public boolean createWordFromCommonPool(String word){
		
		if(game.getCommonPool().makeWord(word)){
			
			if(game.getDictionary().isInDictionary(word)){
				
				if(!game.getPlayerPool().containsWord(word)){
			
        			game.getPlayerPool().addElement(word);
        			for(int i = 0 ; i < word.length() ; i ++) game.getCommonPool().removeElement(word.charAt(i));
        			game.getCommonPool().addElement(game.getPlayer().drawLetter());
        			update();
        			
    			
				}
				else logs.setText("Le mot est déjà en votre possession");
				
			}
			else logs.setText("Le mot n'existe pas !");
			
		}
		else logs.setText("Il manque des lettres !");
		
		return false;
		
	}	
	
	public boolean createWordFromStealing(String word){
		
		if(word.contains(" ") && (word.length() - word.substring(0, word.indexOf(' ')).length()) > 1){
		
			String beg = word.substring(0, word.indexOf(' '));
			String end = word.substring(word.indexOf(' ') + 1);
			System.out.println(beg + " " + end);
			
			if(game.getDictionary().isInDictionary(end)){
				
				if(!game.getPlayerPool().containsWord(end)){
				
					if(game.getIAPool().containsWord(beg)){
						
						System.out.println(end.replace(beg, ""));
						
						if(game.getCommonPool().makeWord(end.replace(beg, ""))){
							
							game.getIAPool().removeElement(beg);
							
							game.getPlayerPool().addElement(end);
		        			for(int k = 0 ; k < end.replace(beg, "").length() ; k ++) game.getCommonPool().removeElement(end.replace(beg, "").charAt(k));
		        			game.getCommonPool().addElement(game.getPlayer().drawLetter());
		        			update();
		        			
		        			return true;
							
						}
						else logs.setText("Il n'y a pas assez de lettres pour voler ce mot !");
						
					}
					else logs.setText("Impossible de voler ce mot !");
				
				}
				else logs.setText("Le mot est déjà en votre possession !");
				
			}
			else logs.setText("Le mot n'existe pas !");
		
		}
		else logs.setText("Mauvaise syntaxe !");
		
		return false;
		
	}
	
	public boolean createWordFromAssembling(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[3];
		
		words[0] = words[1] = words[2] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 2 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		if(isThereSomethingAfterLastSpace){
			
			if(game.getDictionary().isInDictionary(words[2])){
				
				if(game.getPlayerPool().containsWord(words[0]) && game.getPlayerPool().containsWord(words[1])){
					
					if(words[0].length() + words[1].length() == words[2].length()){
						
						if(isThereSameAmountOfLetters(words[0] + words[1], words[2])){
							
							game.getPlayerPool().addElement(words[2]);
							game.getPlayerPool().removeElement(words[0]);
							game.getPlayerPool().removeElement(words[1]);
							
							game.getCommonPool().addElement(game.getPlayer().drawLetter());
		        			update();
		        			
		        			return true;
							
						}
						else logs.setText("Il n'y a pas le même nombre de lettres utilisées");
						
					}
					else logs.setText("L'intégralité des deux mots doit être utilisée !");
					
				}
				else logs.setText("Le mot est déjà en votre possession !");
				
			}
			else logs.setText("Le mot n'existe pas !");
			
		}
		else logs.setText("Mauvaise syntaxe !");
		
		return false;
		
	}
	
	public boolean createWordFromAddingLetters(String word){
		
		int countSpace = 0;
		boolean isThereSomethingAfterLastSpace = false;
		String[] words = new String[2];
		
		words[0] = words[1] = "";
		
		for(int i = 0 ; i < word.length() ; i ++){
			
			if(word.charAt(i) == ' ') countSpace ++;
			if(countSpace == 1 && word.charAt(i) == ' ' && i < word.length() - 1) isThereSomethingAfterLastSpace = true;
			if(word.charAt(i) != ' ') words[countSpace] += word.charAt(i);
			
		}
		
		
		if(isThereSomethingAfterLastSpace){
			
			if(game.getDictionary().isInDictionary(words[1])){
				
				if(!game.getPlayerPool().containsWord(words[1])){

					String temp = words[1];
					for(int j = 0 ; j < words[0].length() ; j ++){
						
						temp = temp.replace(words[0].substring(j, j+1), "");
						
					}
					
					if(game.getCommonPool().makeWord(temp)){
						
						game.getPlayerPool().addElement(words[1]);
						game.getPlayerPool().removeElement(words[0]);
	        			for(int j = 0 ; j < temp.length() ; j ++) game.getCommonPool().removeElement(temp.charAt(j));
	        			game.getCommonPool().addElement(game.getPlayer().drawLetter());
	        			update();
						
					}
					else logs.setText("Il n'y a pas assez de lettres !");
					
				}
				else logs.setText("Le mot est déjà en votre possession !");
				
			}
			else logs.setText("Le mot n'existe pas !");
			
		}
		else logs.setText("Mauvaise syntaxe !");
		
		
		return false;
		
	}
	
	public boolean isThereSameAmountOfLetters(String a, String b){
		
		int[] counter = new int[26];
		
		for(int count = 0 ; count < 26 ; count ++) counter[count] = 0;
		
		for(int i = 0 ; i < a.length() ; i ++){
			
			counter[((int) a.charAt(i)-97)] ++;
			
		}
		
		for(int j = 0 ; j < b.length() ; j ++){
			
			counter[((int) b.charAt(j)-97)] --;
			
		}
		
		for(int check = 0 ; check < 26 ; check ++){
			
			if(counter[check] != 0) return false;
			
		}
		
		return true;
		
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
			
			if(i % 30 == 0 && i != 0) cpool.append("\n");
			
			cpool.append(game.getCommonPool().getElement(i) + " ");
			
		}
		
		ppool.setText("");
		
		for(int j = 0 ; j < game.getPlayerPool().getNumberOfElements() ; j ++){
			
			ppool.append(game.getPlayerPool().getElement(j) + "\n");
			
		}
		
		ipool.setText("");
		
		for(int k = 0 ; k < game.getIAPool().getNumberOfElements() ; k ++){
			
			ipool.append(game.getIAPool().getElement(k) + "\n");
			
		}
		
		console.setText("");
		
	}
	
}
