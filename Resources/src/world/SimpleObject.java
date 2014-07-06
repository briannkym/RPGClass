package world;

import java.awt.Graphics2D;
import java.io.File;

import sound.Sound;
import sound.SoundUpload;
import sound.TrackPlayer;
import sprite.ColorImg;
import sprite.Img;
import sprite.ImgUpload;
import sprite.NullImg;

public abstract class SimpleObject {

	public static final int NO_MOVES_NO_COLLIDES = 0, NO_MOVES = 1,
			NO_COLLIDES = 2, NORMAL = 3;
	
	private Img i = NullImg.getInstance();
	int time = 0;
	int updates = NORMAL;
	int dx, dy, pre_x, pre_y;
	int coor_x, coor_y, pre_cx, pre_cy;
	SimpleSolidMap w;

	protected final int[] off = { 0, 0 };

	abstract public void collision(SimpleObject s);

	abstract public void update();

	abstract public char id();
	
	public String getInfo(){
		return "";
	}
	
	public SimpleObject getCopy(String s){
		return null;
	}

	public SimpleObject() {
		this(NORMAL);
	}

	public SimpleObject(int optimization) {
		this.updates = optimization;
	}

	public SimpleObject(String sprite) {
		this(sprite, NORMAL);
	}

	public SimpleObject(String sprite, int optimization) {
		this(optimization);
		File f = new File(sprite);
		this.i = ImgUpload.getInstance(f.getParentFile()).getImg(f.getName());
	}
	
	public boolean cancelMove(){
		if(time!=0){
			coor_x = pre_cx;
			coor_y = pre_cy;
			time = 0;
			return true;
		} else {
			return false;
		}
	}

	public void moveCell(int x, int y, boolean relative) {
		moveCell(x, y, 0, relative);
	}

	public void moveCell(int x, int y, int time, boolean relative) {
		if (this.time == 0) {
			int temp_x, temp_y;
			if (relative) {
				temp_x = coor_x + x;
				temp_y = coor_y + y;
			} else {
				temp_x = x;
				temp_y = y;
			}
			if (temp_x >= 0 && temp_x < w.map.length && temp_y >= 0
					&& temp_y < w.map[0].length) {
				pre_cx= coor_x;
				pre_cy= coor_y;
				coor_x = temp_x;
				coor_y = temp_y;
				dx = (temp_x * w.cellWidth - pre_x) / time;
				dy = (temp_y * w.cellHeight - pre_y) / time;
				if (time > 0) {
					this.time = time;
				}
			}
		}
	}

	void move() {
		switch (updates) {
		case NO_MOVES:
			SimpleSolid s = w.map[coor_x][coor_y];
			if (s != null) {
				s.collision(this);
				collision(s);
			}
			break;
		case NORMAL:
			SimpleSolid S = w.map[coor_x][coor_y];
			if (S != null) {
				S.collision(this);
				collision(S);
			}
		case NO_COLLIDES:
			switch (time) {
			case 0:
				pre_x = coor_x * w.cellWidth;
				pre_y = coor_y * w.cellHeight;
				break;
			default:
				pre_x += dx;
				pre_y += dy;
				time -= 1;
				break;
			}
		default:
			break;
		}
		this.update();
	}

	public void setOffset(int off_x, int off_y) {
		this.off[0] = off_x;
		this.off[1] = off_y;
	}

	public int[] getOffset() {
		return off;
	}
	
	public int getX(){
		return coor_x;
	}
	
	public int getY(){
		return coor_y;
	}

	public void paintImage(Graphics2D g) {
		g.drawImage(i.getSlide(), pre_x + off[0], pre_y + off[1], null);
	}

	public void setImage(String sprite) {
		File f = new File(sprite);
		this.i = ImgUpload.getInstance(f.getParentFile()).getImg(f.getName());
	}

	public void setImage(int rgba, int width, int height) {
		this.i = new ColorImg(rgba, width, height);
	}

	public void setImage(Img i) {
		this.i = i;
	}

	public Img getImage() {
		return i;
	}

	public void playSound(String sound) {
		File f = new File(sound);
		Sound s = SoundUpload.getInstance(f.getParentFile()).getSound(
				f.getName());
		TrackPlayer.getPlayer().play(s);
	}

	public SimpleSolid getSolid() {
		return null;
	}
}
