package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import sprite.Img;
import sprite.ImgUpload;

import world.SimpleObject;
import world.SimpleSolid;

public class Player extends SimpleSolid implements KeyListener {
	private Img gN = ImgUpload.getInstance(new File("../sprites/char1")).getImg("girlN.png"); 
	private Img gS = ImgUpload.getInstance(new File("../sprites/char1")).getImg("girlS.png"); 
	private Img gE = ImgUpload.getInstance(new File("../sprites/char1")).getImg("girlE.png"); 
	private Img gW = ImgUpload.getInstance(new File("../sprites/char1")).getImg("girlW.png"); 
	private int speed = 10;
	private int x_dir = 0;
	private int y_dir = 0;
	private boolean move = false;

	public Player() {
		this.setImage(gS);
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
		} else {
			this.getImage().setSlide(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
			this.setImage(gW);
			x_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_RIGHT:
			this.setImage(gE);
			x_dir = 1;
			move = true;
			break;
		case KeyEvent.VK_UP:
			this.setImage(gN);
			y_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_DOWN:
			this.setImage(gS);
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

		if ((x_dir & y_dir) == 0) {
			if (y_dir + x_dir == 0) {
				move = false;
			}
			speed = 5;
		}
	}

}
