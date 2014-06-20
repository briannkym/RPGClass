package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import sound.*;

public abstract class SimpleWorldObject {
	
	public abstract void updateScreen(BufferedImage bi, Graphics g);
	
	public void playSound(String sound){
		File f = new File(sound);
		Sound s = SoundUpload.getInstance(f.getParentFile()).getSound(f.getName());
		TrackPlayer.getPlayer().play(s);
	}
	
	public boolean loadImageResources(String spriteFile){
		File f = new File(spriteFile);
		if(f.isDirectory()){
			SoundUpload.getInstance(f.getParentFile());
			return true;
		} 
		return false;
	}
	
	public boolean loadSoundResources(String soundFile){
		File f = new File(soundFile);
		if(f.isDirectory()){
			SoundUpload.getInstance(f.getParentFile());
			return true;
		} 
		return false;
	}
}