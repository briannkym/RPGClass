package main;

import world.SimpleWorld;

public class PuzzlesEdit {
	SimpleWorld w;
	MapMaker m;
	
	public static void main(String[] args){
		PuzzlesEdit h = new PuzzlesEdit();
	}
	
	public PuzzlesEdit(){
		w = new SimpleWorld(20, 20, 16, 16, "Sulyeob");
		w.setBGImage(0xFF000000, 16, 16);
		m = new MapMaker(w);
		w.start(false);
	}
}
