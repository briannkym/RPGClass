package main;

import player.*;
import world.*;

public class Puzzles {
	SimpleWorld w;
	Player p;
	GUI g;
	
	public static void main(String[] args){
		Puzzles h = new Puzzles();
	}
	
	public Puzzles()
	{
		Register.loadObjects();
		w = new SimpleWorld(20, 20, 16, 16, "Sulyeob");
		SimpleWorldIO wio = new SimpleWorldIO("map.txt");
		wio.readWorld(w);
		w.setBGImage(0xFF000000, 16, 16);
		p = new Player();
		g = new GUI();
		w.addKeyListener(p);
		w.addSimpleObject(p, 5, 5);
		w.setSimpleWorldObject(g);
		w.start(false);
	}
}
