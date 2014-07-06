package world;

public class SimpleSolidMap {
	final SimpleSolid[][] map;
	final int cellWidth;
	final int cellHeight;
	
	public SimpleSolidMap(int width, int height, int cellWidth, int cellHeight){
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.map = new SimpleSolid[width][height];
	}
}
