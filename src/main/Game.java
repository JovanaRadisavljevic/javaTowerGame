package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Playing;
import scenes.Settings;
import scenes.Menu;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	
	private Thread gameThread;
	private final double FPS_SET =120.0;
	private final double UPS_SET =60.0;
	
	//Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		initClasses();
		add(gameScreen);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void initClasses() {
		gameScreen=new GameScreen(this);
		render = new Render(this);
		menu=new Menu(this);
		playing=new Playing(this);
		settings=new Settings(this);
	}
	
	private void start() {
		gameThread=new Thread(this) {};
		gameThread.start();
	}

	private void updateGame() {
		
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
		
	}

	@Override
	public void run() {
		
		double timePreFrame = 1000000000.0/FPS_SET;
		double timePerUpdate=1000000000.0/UPS_SET;
		
		long lastFrame=System.nanoTime();
		long lastUpdate=System.nanoTime();
		long lastTimeCheck=System.currentTimeMillis();
		
		int frames =0;
		int updates=0;
		long now;
		
		while (true) {
			//render
			now=System.nanoTime();
			if(now-lastFrame>=timePreFrame) {
				repaint();
				lastFrame=now;
				frames++;
			}
			//update
			if(now-lastUpdate>=timePerUpdate) {
				updateGame();
				lastUpdate=now;
				updates++;
			}
			if(System.currentTimeMillis()-lastTimeCheck>=1000) {
				//ako je vece od 1 sekunde tj poziva se svake sekunde
				System.out.println("FPS="+frames+" UPS: "+updates);
				frames=0;
				updates=0;
				lastTimeCheck=System.currentTimeMillis();
			}
		}
		
	}
	//getters and setters
	public Render getRender() {
		return render;
	}
	public Menu getMenu() {
		return menu;
	}
	public Playing getPlaying() {
		return playing;
	}
	public Settings getSettings() {
		return settings;
	}
	
}
