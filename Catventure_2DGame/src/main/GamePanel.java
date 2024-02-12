package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//Screen settings
	final int originalTileSize = 16; // 16x16 px tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 px tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 px
	public final int screenHeight = tileSize * maxScreenRow; // 576 px
	
	// FPS Frames per second
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	

	@Override
	public void run() {
		double drawIntervall = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawIntervall;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	// paint all the tiles 
	public void paintComponent(Graphics g) {
		// super means the JPanel class, thats the parent class from this
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		// its important to write the tiles befor the player, opposite the tiles will hide the player
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
}
