package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import world.SimpleObject;

public class Player extends SimpleObject implements KeyListener {

	private int speed = 10;
	private int x_dir = 0;
	private int y_dir = 0;
	private boolean move = false;

	public Player() {
		this.setImage(0xFFFF0000, 20, 20);
	}

	@Override
	public char id() {
		return 'P';
	}

	@Override
	public void collision(SimpleObject s) {
	}

	@Override
	public void update() {
		if (move) {
			this.moveCell(x_dir, y_dir, speed, true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			x_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_RIGHT:
			x_dir = 1;
			move = true;
			break;
		case KeyEvent.VK_UP:
			y_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_DOWN:
			y_dir = 1;
			move = true;
			break;
		case KeyEvent.VK_SPACE:
			break;
		}

		if (x_dir != 0 && y_dir != 0) {
			speed = 7;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			x_dir = 0;
			break;
		case KeyEvent.VK_RIGHT:
			x_dir = 0;
			break;
		case KeyEvent.VK_UP:
			y_dir = 0;
			break;
		case KeyEvent.VK_DOWN:
			y_dir = 0;
			break;
		case KeyEvent.VK_SPACE:
			break;
		}

		if ((x_dir ^ y_dir) == 0) {
			if (y_dir == 0) {
				move = false;
			}
			speed = 5;
		}
	}

}
