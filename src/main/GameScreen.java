package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	private Random random;
	private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	
	
	public GameScreen(BufferedImage img) {
		this.img=img;
		loadSprites();
		random=new Random();
		
		
	}
	
	private void loadSprites() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x*32, y*32, 32, 32));
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				g.drawImage(sprites.get(getRandomInt()),x*32, y*32,  null);
			}
		}
		
		//poziva se kad resize-ujem ekran ili pri svakom pozivu u ovoj fji
		//ova metoda govori ovoj klasi da pozove paintComponent i tako dobijamo beskonacnu pelju 
	}
	
	
	private int getRandomInt() {
		return random.nextInt(100);
	}
	private Color getRandomColor() {
		int r=random.nextInt(256);
		int g=random.nextInt(256);
		int b=random.nextInt(256);
		return new Color(r, g, b);
	}
	
}
