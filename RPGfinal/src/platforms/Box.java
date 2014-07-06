package platforms;

import world.SimpleObject;

public class Box extends SimpleObject{

	public static final char ID = 14;
	
	public Box() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/isocolors/greenBox.png");
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
