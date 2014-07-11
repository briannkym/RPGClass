package platforms;

import java.io.File;

import sprite.Img;
import sprite.ImgUpload;
import world.SimpleObject;

public class Ramp extends SimpleObject{
	private static Img N = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueRN.png"); 
	private static Img S = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueRS.png"); 
	private static Img E = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueRE.png"); 
	private static Img W = ImgUpload.getInstance(new File("../sprites/isocolors")).getImg("blueRW.png");
	private char direction = 1;
	public static final char NORTH = 1, EAST = 2, WEST = 3, SOUTH = 4;
	
	public Ramp(char direction) {
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
			direction = 4;
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
		return new Ramp(s.charAt(0));
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
