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
		w = new SimpleWorld(new SimpleObject[20][20][2], 20, 20, "Sulyeob");
		p = new Player();
		w.add(0, 0, 0, p);
		w.add(10,10, 0, new SimpleNPC());
		w.addKeyListener(p);
	}
}
