package platforms;

import world.SimpleObject;

public class HighFloor extends SimpleObject{

	public static final char ID = 10;
	
	public HighFloor() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/isocolors/greenT.png");
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
			
	}

}
