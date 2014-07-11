/*The MIT License (MIT)

Copyright (c) 2014 Brian Nakayama

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
					w.map[temp_x][temp_y] = this;

					if (time > 0) {
						this.time = time;
						dx = (temp_x * w.cellWidth - pre_x) / time;
						dy = (temp_y * w.cellHeight - pre_y) / time;
					} else {
						time = 0;
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
