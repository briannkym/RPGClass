package main;

import player.*;
import world.*;

public class RPG {
	SimpleWorld w; 
	Player p;
	
	public static void main(String[] args){
		RPG h = new RPG();
	}
	
	public RPG()
	{
		w = new SimpleWorld(20, 20, 16, 16, "Sulyeob");
		p = new Player();
		w.addSimpleObject(p, 0, 0);
		w.addSimpleObject(new SimpleNPC(), 10, 10);
		w.addKeyListener(p);
		w.start(true);
	}
}
