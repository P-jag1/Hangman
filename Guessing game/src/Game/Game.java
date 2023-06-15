package Game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Image;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game{

	private JFrame frame;	
	private JTextField textField;
	
	private String word;
	private String fields = "";
	
	private char letter;
	private char[] myFields;
	
	private int tries = 0;
	private int ind = 1;
	
	private JTextField textLetter;
	private JLabel lblWon;
	private JButton btnGuess;
	private JButton btnAgain;
	private JButton btnRestart;
	private JLabel lblTries;
	private JLabel lblNewLabel;
	private JLabel lblImage;
	
	private BufferedImage myImage = null;

	public Game(String word) {
		this.word = word;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		
		frame.setTitle("Guessing Game");
		frame.setPreferredSize(new Dimension(400, 710));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(10, 11, 364, 67);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		fields = fillFields();
		drawSpacedText(fields);

		btnGuess = new JButton("GUESS");
		btnGuess.setEnabled(false);
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textLetter.getText().length() == 1) {
					try {
						letter = textLetter.getText().charAt(0);
						lblWon.setText("");
						guess();
					} catch(StringIndexOutOfBoundsException e1) {
						lblWon.setText("Not a letter as an input!");
					}
				} else {
					lblWon.setText("Not a letter as an input!");
				}

			}
		});
		
		 
		
		btnGuess.setBounds(150, 327, 89, 23);
		frame.getContentPane().add(btnGuess);
		
		textLetter = new JTextField();
		textLetter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				int k = e.getKeyCode();
				
				if (Character.isLetter(c) || Character.isISOControl(c) || (k == KeyEvent.VK_SHIFT) || (k == KeyEvent.VK_CAPS_LOCK)) {
					btnGuess.setEnabled(true);
					lblWon.setText("");	
				} else {
					btnGuess.setEnabled(false);
					lblWon.setText("Not a letter as an input");	
				}
				
				textLetter.setText("");
			}
		});
		textLetter.setText("");
		textLetter.setHorizontalAlignment(SwingConstants.CENTER);
		textLetter.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textLetter.setColumns(10);
		textLetter.setBounds(150, 228, 89, 67);
		frame.getContentPane().add(textLetter);		
		
		lblWon = new JLabel("");
		lblWon.setForeground(new Color(139, 0, 0));
		lblWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblWon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWon.setBounds(0, 89, 384, 117);
		frame.getContentPane().add(lblWon);
		
		btnAgain = new JButton("PLAY AGAIN");
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Gui();
				frame.dispose();
			}
		});
		btnAgain.setBounds(10, 327, 130, 23);
		frame.getContentPane().add(btnAgain);
		
		btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
		});
		btnRestart.setBounds(244, 327, 130, 23);
		frame.getContentPane().add(btnRestart);
		btnRestart.setEnabled(false);
		
		lblTries = new JLabel(String.valueOf(tries));
		lblTries.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTries.setHorizontalAlignment(SwingConstants.CENTER);
		lblTries.setBounds(48, 256, 56, 60);
		frame.getContentPane().add(lblTries);
		
		lblNewLabel = new JLabel("Number of Tries");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(32, 228, 94, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblImage = new JLabel(new ImageIcon(getImage(ind)));
		lblImage.setBounds(48, 361, 300, 300);
		frame.getContentPane().add(lblImage);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	 public Image getImage(int in) {
	     try {
	    	 myImage = ImageIO.read(new File("images/hang" + String.valueOf(in) + ".png"));
		 } catch (IOException e1) {
			lblWon.setText("Image not found!");
		 }
	       
	       return myImage;
	     }
	
	private void guess() {
		textLetter.setText("");
		myFields = fields.toCharArray();
		
		if(word.indexOf(letter) != -1) {
			for(int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == letter) {					
					myFields[i] = letter;
					fields = String.valueOf(myFields);
				} 			
			}
		} else {
			addTry();
		}	
		
		drawSpacedText(fields);	
		
		if (fields.equals(word)) {
			lblWon.setText("Congratulations, you won!");
			btnGuess.setEnabled(false);
			textLetter.setEnabled(false);
			btnRestart.setEnabled(true);
		}
	}
	
	private void drawSpacedText(String text) {
		text = text.replace("", " ").trim();
		textField.setText(text);
	}
	
	private String fillFields() {
		for(int i = 0; i < word.length(); i++) {
			if (Character.isWhitespace(word.charAt(i))) {
				fields = fields + " ";
			} else {
				fields = fields + "_";
			}
		
		}
		return fields;
	}
	
	private void addTry() {
		if (tries < 9) {
			++tries;
			++ind;
			lblTries.setText(String.valueOf(tries));
			lblImage.setIcon(new ImageIcon(getImage(ind)));
		} else {
			lblWon.setText("You run out of luck! :(");
			lblTries.setText("10!");
			lblImage.setIcon(new ImageIcon(getImage(11)));
			btnGuess.setEnabled(false);
			textLetter.setEnabled(false);
			btnRestart.setEnabled(true);
		}

	}
	
	private void restartGame() {
		fields = "";
		fields = fillFields();
		drawSpacedText(fields);
		lblWon.setText("");	
		tries = 0;
		ind = 1;
		lblTries.setText(String.valueOf(tries));
		lblImage.setIcon(new ImageIcon(getImage(ind)));
		btnGuess.setEnabled(true);
		textLetter.setEnabled(true);
		btnRestart.setEnabled(false);
	}	
}
