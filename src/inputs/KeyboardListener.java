package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameStates;

public class KeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A)
			GameStates.gameState=GameStates.MENU;
		if(e.getKeyCode()==KeyEvent.VK_S)
			GameStates.gameState=GameStates.PLAYING;
		if(e.getKeyCode()==KeyEvent.VK_D)
			GameStates.gameState=GameStates.SETTINGS;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
