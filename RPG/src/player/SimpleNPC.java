package player;

import world.SimpleObject;
import world.SimpleSolid;

public class SimpleNPC extends SimpleSolid{
	private boolean right = false;

	public SimpleNPC() {
		//A red 16x16 square.
		this.setImage(0xFFFF0000, 16, 16);
	}

	@Override
	public char id() {
		return 'P';
	}

	@Override
	public void collision(SimpleObject s) {
		right = !right;
	}

	@Override
	public void update() {
		if (right) {
			this.moveCell(1, 0, 10 ,true);
		} else {
			this.moveCell(-1, 0, 10 ,true);
		}
	}
}
