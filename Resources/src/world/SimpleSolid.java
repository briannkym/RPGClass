package world;

public abstract class SimpleSolid extends SimpleObject {
	
	public SimpleSolid() {
		super(NO_COLLIDES);
	}
	
	public SimpleSolid(boolean NO_MOVES) {
		super(NO_MOVES_NO_COLLIDES);
		if(!NO_MOVES){
			this.updates = NO_COLLIDES;
		}
	}

	public SimpleSolid(String sprite) {
		super(sprite, NO_COLLIDES);
	}

	public SimpleSolid(String sprite, boolean NO_MOVES) {
		super(sprite, NO_MOVES_NO_COLLIDES);
		if(!NO_MOVES){
			this.updates = NO_COLLIDES;
		}
	}
	
	public boolean cancelMove(){
		if(time!=0 && w.map[pre_cx][pre_cy] == null){
			w.map[coor_x][coor_y] = null;
			coor_x = pre_cx;
			coor_y = pre_cy;
			w.map[coor_x][coor_y] = this;
			time = 0;
			return true;
		} else {
			return false;
		}
	}

	public void moveCell(int x, int y, int time, boolean relative) {
		if (this.time == 0) {
			int temp_x, temp_y;
			if (relative) {
				temp_x = coor_x + x;
				temp_y = coor_y + y;
			} else {
				temp_x = x;
				temp_y = y;
			}
			if (temp_x >= 0 && temp_x < w.map.length && temp_y >= 0
					&& temp_y < w.map[0].length) {
				SimpleSolid s = w.map[temp_x][temp_y];
				if (s != null) {
					s.collision(this);
					collision(s);
				} else {
					pre_cx= coor_x;
					pre_cy= coor_y;
					w.map[coor_x][coor_y] = null;
					coor_x = temp_x;
					coor_y = temp_y;
					dx = (temp_x * w.cellWidth - pre_x) / time;
					dy = (temp_y * w.cellHeight - pre_y) / time;
					w.map[temp_x][temp_y] = this;

					if (time > 0) {
						this.time = time;
					}
				}
			}
		}
	}

	public SimpleSolid getSolid() {
		return this;
	}
	
	public SimpleSolid getNorthSolid(){
		if(coor_y - 1 >=0){
			return w.map[coor_x][coor_y - 1];
		}
		return null;
	}
	
	public SimpleSolid getWestSolid(){
		if(coor_x - 1 >= 0){
			return w.map[coor_x - 1][coor_y];
		}
		return null;
	}
	
	public SimpleSolid getEastSolid(){
		if(coor_x + 1 < w.map.length){
			return w.map[coor_x + 1][coor_y];
		}
		return null;
	}
	
	public SimpleSolid getSouthSolid(){
		if(coor_y + 1 < w.map[0].length){
			return w.map[coor_x][coor_y + 1];
		}
		return null;
	}
}
