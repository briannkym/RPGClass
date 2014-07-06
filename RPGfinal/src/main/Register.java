package main;

import platforms.Box;
import platforms.Bricks;
import platforms.Emptyness;
import platforms.Floor;
import platforms.HighFloor;
import platforms.Key;
import platforms.Lock;
import platforms.Ramp;
import platforms.Stair;
import player.Player;
import world.SimpleWorldFactory;

public class Register {
	public static void loadObjects(){
		SimpleWorldFactory swf = SimpleWorldFactory.getInstance();
		swf.register(new Box());
		swf.register(new Bricks());
		swf.register(new Emptyness());
		swf.register(new Floor());
		swf.register(new HighFloor());
		swf.register(new Key());
		swf.register(new Lock());
		swf.register(new Ramp(Ramp.NORTH));
		swf.register(new Ramp(Ramp.EAST));
		swf.register(new Ramp(Ramp.WEST));
		swf.register(new Ramp(Ramp.SOUTH));
		swf.register(new Stair(Stair.NORTH));
		swf.register(new Stair(Stair.EAST));
		swf.register(new Stair(Stair.WEST));
		swf.register(new Stair(Stair.SOUTH));
		swf.register(new Player());
	}
}
