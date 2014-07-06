package platforms;

import world.SimpleObject;

public class Floor extends SimpleObject{

	public static final char ID = 9;
	
	public Floor() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/isocolors/greenS.png");
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
