package Game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui {

	private JFrame frame;
	
	public Gui() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(211, 211, 211));
		
		frame.setTitle("Guessing game");
		frame.setPreferredSize(new Dimension(250, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to the Guessing Game");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(10, 11, 214, 57);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnStart = new JButton("NEW GAME");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameStart();				
				frame.dispose();
			}
		});
		btnStart.setBackground(new Color(192, 192, 192));
		btnStart.setBounds(59, 79, 116, 49);
		btnStart.setFocusPainted(false);
		frame.getContentPane().add(btnStart);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
			}
		});
		btnClose.setFocusPainted(false);
		btnClose.setBackground(Color.LIGHT_GRAY);
		btnClose.setBounds(59, 139, 116, 49);
		btnClose.setFocusPainted(false);
		frame.getContentPane().add(btnClose);
		
				
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}
}
