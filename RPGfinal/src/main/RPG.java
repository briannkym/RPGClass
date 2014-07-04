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
		w = new SimpleWorld(new SimpleObject[20][20][2], 16, 16, "Sulyeob");
		p = new Player();
		w.add(0, 0, 0, p);
		w.addKeyListener(p);		
	}
}
