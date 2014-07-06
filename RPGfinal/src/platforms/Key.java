package platforms;

import world.SimpleObject;
import world.SimpleSolid;

public class Key extends SimpleSolid{

	public static final char ID = 12;
	
	public Key() {
		super(true);
		this.setImage("../sprites/isocolors/greenKey.png");
	}
	
	@Override
	public char id() {
		return ID;
	}

	@Override
	public void collision(SimpleObject s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
