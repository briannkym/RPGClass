package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import platforms.*;

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
	private int[] offset = this.getOffset();
	public static final char ID = 16;
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
		switch(s.id()){
		case Ramp.NORTH:
			move = false;
			offset[1]= -2;
			this.moveCell(0, 1, 8, true);
			break;
		case Ramp.EAST: 
			move = false;
			offset[1]= -2;
			this.moveCell(-1, 0, 8, true);
			break;
		case Ramp.WEST:
			move = false;
			offset[1]= -2;
			this.moveCell(1, 0, 8, true);
			break;
		case Ramp.SOUTH:
			move = false;
			offset[1]= -2;
			this.moveCell(0, -1, 8, true);
			break;
		case Stair.NORTH:
		case Stair.EAST:
		case Stair.WEST:
		case Stair.SOUTH:
			offset[1]= -2;
			break;
		case HighFloor.ID:
			switch(offset[1]){
			case 0:
				this.cancelMove();
				break;
			default:
				offset[1]= -4;
			}
			break;
		case Floor.ID:
			switch(offset[1]){
			case -4:
				this.cancelMove();
				break;
			default:
				offset[1]= 0;
			}
			break;
		case Emptyness.ID:
			this.cancelMove();
			break;
		}
	}

	@Override
	public void update() {
		if (move) {
			this.moveCell(x_dir, y_dir, speed, true);
		} else {
			this.getImage().setSlide(1);
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
			speed = 8;
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
