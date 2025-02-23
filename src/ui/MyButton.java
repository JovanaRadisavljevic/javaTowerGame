package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	public int x,y,width,height;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver,mousePressed;
	private int id;
	
	public MyButton(String text, int x,int y,int width,int height) {
		this.text=text;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.id=-1;
		initBounds();
	}
	//for tile buttons
	public MyButton(String text, int x,int y,int width,int height,int id) {
		this.id=id;
		this.text=text;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		initBounds();
	}
	public void draw(Graphics g) {
		//body
		drawBody(g);
		//border
		drawBorder(g);
		//text
		drawtext(g);
		
	}
	private void drawBorder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		if(mousePressed) {
			g.drawRect(x+1, y+1, width-2, height-2);
			g.drawRect(x+2, y+2, width-4, height-4);
		}
	}
	private void drawBody(Graphics g) {
		if(mouseOver) {
			g.setColor(Color.GRAY);
		}else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x, y, width, height);
	}
	private void drawtext(Graphics g) {
		int w=g.getFontMetrics().stringWidth(text);
		int h =g.getFontMetrics().getHeight();
		g.drawString(text, x-w/2+width/2, y+h/3+height/2);
	}
	public Rectangle getBounds() {
		return bounds;
	}
	private void initBounds() {
		this.bounds=new Rectangle(x,y,width,height);
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver=mouseOver;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed=mousePressed;
	}
	public void resetBooleans() {
		this.mouseOver=false;
		this.mousePressed=false;
	}
	public int getId() {
		return id;
	}
	public boolean isMouseOver() {
		return mouseOver;
	}
	public boolean isMousePressed() {
		return mousePressed;
	}
	
}
