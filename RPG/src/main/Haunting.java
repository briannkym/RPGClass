package main;

import player.Player;
import world.*;

public class Haunting {
	SimpleWorld w;
	Player p;
	
	public static void main(String[] args){
		Haunting h = new Haunting();
	}
	
	public Haunting()
	{
		w = new SimpleWorld(new SimpleObject[20][20], 20, 20, "Sulyeob");
		p = new Player();
		w.add(0, 0, p);
		w.addKeyListener(p);
	}
}
