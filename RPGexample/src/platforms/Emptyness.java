package platforms;

import java.util.Random;

import world.SimpleObject;

public class Emptyness extends SimpleObject{

	public static final char ID = 11;
	private static Random r= new Random();
	
	public Emptyness() {
		super(SimpleObject.NO_MOVES);
		this.setImage("../sprites/things/star"+r.nextInt(4)+".png");
		this.setOffset(0, 8);
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
