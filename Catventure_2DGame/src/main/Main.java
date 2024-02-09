package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String [] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // window is not resizeable
		window.setTitle("Catventure 2D Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null); // the window will be displayed at the center of the screen
		window.setVisible(true); // so we can see the window
		
		gamePanel.startGameThread();
	}

}
