package platforms;

import world.SimpleObject;

public class Bricks extends SimpleObject{

	public static final char ID = 15;
	
	public Bricks() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/isocolors/greenBrick.png");
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
