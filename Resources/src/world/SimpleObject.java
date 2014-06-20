package world;

import java.awt.Graphics;
import java.io.File;

import sound.Sound;
import sound.SoundUpload;
import sound.TrackPlayer;
import sprite.*;

public abstract class SimpleObject {

	public static final int X = 0, Y = 1, REL = 2, TIME = 3, DUR = 4, PREX = 5, PREY = 6;
	private Img i = NullImg.getInstance();
	//next x, next y, relative, time, duration 
	private final int[] m = {0, 0, 0, 0, 1, 0, 0};
	private final int[] off={ 0, 0};
	
	
	abstract public char id();
	abstract public void collision(SimpleObject s);
	abstract public void update();
	
	public SimpleObject(){
	}
	
	public SimpleObject(String sprite){
		File f = new File(sprite);
		this.i = ImgUpload.getInstance(f.getParentFile()).getImg(f.getName());
	}
	
	public void moveCell(int x, int y, boolean relative) {
		moveCell(x, y, 0, relative);
	}
	
	public void moveCell(int x, int y, int time, boolean relative) {
		if(m[TIME] == 0){
			m[X] = x;
			m[Y] = y;
			m[REL] = relative ? 1 : 0;
			m[TIME] = Math.abs(time);
			m[DUR] = Math.abs(time);
		}
	}
	
	public int[] getMovement()
	{
		return m;
	}

	public void setOffset(int off_x, int off_y){
		this.off[0] = off_x;
		this.off[1] = off_y;
	}
	
	public int[] getOffset(){
		return off;
	}
	
	public void setImage(String sprite)	{
		File f = new File(sprite);
		this.i = ImgUpload.getInstance(f.getParentFile()).getImg(f.getName());
	}
	
	public void setImage(int rgba, int width, int height) {
		this.i = new ColorImg(rgba, width, height);
	}
	
	public void setImage(Img i){
		this.i = i;
	}
	
	public Img getImage(){
		return i;
	}
	
	public void paintImage(Graphics g, int x, int y) {
		g.drawImage(i.getSlide(), x + off[0], y + off[1], null);
	}
	
	public void playSound(String sound){
		File f = new File(sound);
		Sound s = SoundUpload.getInstance(f.getParentFile()).getSound(f.getName());
		TrackPlayer.getPlayer().play(s);
	}
}