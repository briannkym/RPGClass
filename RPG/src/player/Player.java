package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import sprite.Img;
import sprite.ImgUpload;

import world.SimpleObject;

public class Player extends SimpleObject implements KeyListener {
	private Img RobN = ImgUpload.getInstance(new File("../sprites")).getImg("NRobWalk.png"); 
	private Img RobS = ImgUpload.getInstance(new File("../sprites")).getImg("SRobWalk.png"); 
	private Img RobE = ImgUpload.getInstance(new File("../sprites")).getImg("ERobWalk.png"); 
	private int speed = 10;
	private int x_dir = 0;
	private int y_dir = 0;
	private boolean move = false;

	public Player() {
		super("../sprites/SRobWalk.png");
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
			this.setDrawMode(SimpleObject.FLIP_X, 0);
			this.setImage(RobE);
			x_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_RIGHT:
			this.setDrawMode(SimpleObject.NONE, 0);
			this.setImage(RobE);
			x_dir = 1;
			move = true;
			break;
		case KeyEvent.VK_UP:
			this.setDrawMode(SimpleObject.NONE, 0);
			this.setImage(RobN);
			y_dir = -1;
			move = true;
			break;
		case KeyEvent.VK_DOWN:
			this.setDrawMode(SimpleObject.NONE, 0);
			this.setImage(RobS);
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
