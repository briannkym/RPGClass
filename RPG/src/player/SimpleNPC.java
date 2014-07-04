package player;

import world.SimpleObject;

public class SimpleNPC extends SimpleObject{
	private boolean right = false;

	public SimpleNPC() {
		//A red 20x20 square.
		this.setImage(0xFFFF0000, 20, 20);
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
