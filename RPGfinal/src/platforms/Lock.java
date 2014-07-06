package platforms;

import world.SimpleObject;

public class Lock extends SimpleObject{

	public static final char ID = 13;
	
	public Lock() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/isocolors/greenLock.png");
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
