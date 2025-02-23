package ui;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

public class BottomBar {
	private int x,y,width,height;
	private MyButton bMenu;
	private Playing playing;
	private ArrayList<MyButton> tileButtons = new ArrayList<MyButton>();
	private Tile selectedTile;

	public BottomBar(int x, int y, int width, int height, Playing playing) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing=playing;
		initButtons();
	}
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		int w=50;
		int h=50;
		int xStart=110;
		int yStart=650;
		int xOffset=(int)(w*1.1f);
		int i=0;
		for (Tile tile: playing.getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart+xOffset*i, yStart, w, h,i));
			i++;
		}
	}
	public void draw(Graphics g) {
		//background
		g.setColor(new Color(220,123,15));
		g.fillRect(x, y, width, height);
		//buttons
		drawButtons(g);
	}
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		drawTileButtons(g);
		drawSelectedTile(g);
	}

	private void drawSelectedTile(Graphics g) {
		if(selectedTile!=null) {
			g.drawImage(selectedTile.getSprite(), 550, 650, 50,50,null);
			g.setColor(Color.BLACK);
			g.drawRect(550, 650, 50, 50);
		}
	}
	private void drawTileButtons(Graphics g) {
		for (MyButton b : tileButtons) {
			//image/sprite
			g.drawImage(getButtImage(b.getId()), b.x, b.y, b.width,b.height,null);
			//mouse over
			if(b.isMouseOver()) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.BLACK);
			}
			//border
			g.drawRect(b.x, b.y, b.width, b.height);
			
			//mouse pressed
			if(b.isMousePressed()) {
				g.drawRect(b.x+1, b.y+1, b.width-2, b.height-2);
				g.drawRect(b.x+1, b.y+2, b.width-4, b.height-4);
			}
			
		}
	}
	private BufferedImage getButtImage(int id) {
		return playing.getTileManager().getSprite(id);
	}
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameStates(MENU);
		}else {
			for (MyButton b : tileButtons) {
				if(b.getBounds().contains(x, y)) {
					selectedTile=playing.getTileManager().getTile(b.getId());
					playing.setSelectedTile(selectedTile);
					return;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for (MyButton b : tileButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
				
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (MyButton b : tileButtons) {
			b.resetBooleans();
		}

	}
}
