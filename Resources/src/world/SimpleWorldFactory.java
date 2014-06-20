package world;

public abstract class SimpleWorldFactory {
	
	public char getChar(SimpleObject o){
		return o.id();
	}
	
	abstract public SimpleObject getSimpleObject(char c);
}
