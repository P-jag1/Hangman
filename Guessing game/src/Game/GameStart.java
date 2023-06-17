package Game;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class GameStart {

	private JFrame gameStartFrame;
	private JTextField txtWord;
	
	private boolean prevFocus = false;
	
	public GameStart() {
		initializeGame();
	}

	
	public void initializeGame() {
		gameStartFrame = new JFrame();
		
		gameStartFrame.setTitle("Hangman game");
		gameStartFrame.setPreferredSize(new Dimension(200, 200));
		gameStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameStartFrame.getContentPane().setLayout(null);
		
		txtWord = new JTextField();
		txtWord.setHorizontalAlignment(SwingConstants.CENTER);
		txtWord.setText("Enter your word");
		txtWord.setBounds(10, 11, 164, 20);
		gameStartFrame.getContentPane().add(txtWord);
		txtWord.setColumns(10);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setForeground(new Color(153, 0, 0));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setBounds(10, 42, 164, 25);
		gameStartFrame.getContentPane().add(lblNotification);
		
		txtWord.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (!prevFocus) {
					txtWord.setText("");
					prevFocus = true;
				}
				
			}
			
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		txtWord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				int k = e.getKeyCode();
				
				if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c) || (k == KeyEvent.VK_SHIFT) || (k == KeyEvent.VK_CAPS_LOCK)) {
					txtWord.setEditable(true);
					lblNotification.setText("");
				} else {
					txtWord.setEditable(false);
					lblNotification.setText("Not a letter as an input!");
				}
					
			}
			
		});
				
		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = txtWord.getText();
				if (word.equals("Enter your word")) {
					lblNotification.setText("Input invalid!");
				} else {
					new Game(word);
					gameStartFrame.dispose();	
				}
			}
		});
		
		btnPlay.setBounds(29, 79, 118, 43);
		gameStartFrame.getContentPane().add(btnPlay);
		btnPlay.setFocusPainted(false);
		
		gameStartFrame.pack();
		gameStartFrame.setLocationRelativeTo(null);
		btnPlay.requestFocusInWindow();
		gameStartFrame.setVisible(true);		
	}
}
