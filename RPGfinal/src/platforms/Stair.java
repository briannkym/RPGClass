package platforms;

import java.io.File;

import sprite.Img;
import sprite.ImgUpload;
import world.SimpleObject;

public class Stair extends SimpleObject{
	private static Img N = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueSN.png"); 
	private static Img S = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueSS.png"); 
	private static Img E = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueSE.png"); 
	private static Img W = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueSW.png");
	private char direction = 5;
	public static final char NORTH = 5, EAST = 6, WEST = 7, SOUTH = 8;
	
	public Stair(char direction) { 
		super(SimpleObject.NO_MOVES);
		this.direction = direction;
		switch(direction){
		case NORTH:
			this.setImage(N);
			break;
		case EAST:
			this.setImage(E);
			break;
		case WEST:
			this.setImage(W);
			break;
		default:
			direction = 8;
			this.setImage(S);
			break;
		}
	}
	
	@Override
	public String getInfo(){
		return "" + direction;
	}
	
	@Override
	public SimpleObject getCopy(String s){
		return new Stair(s.charAt(0));
	}
	
	@Override
	public char id() {
		return direction;
	}

	@Override
	public void collision(SimpleObject s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() { 
	}

}
