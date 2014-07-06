package world;

import java.util.HashMap;
import java.util.Map;

import sprite.Img;

public class SimpleWorldFactory {
	
	private static SimpleWorldFactory swf = new SimpleWorldFactory();
	Map <Character, SimpleObject>objects = new HashMap<Character, SimpleObject>();
	
	public static SimpleWorldFactory getInstance(){
		return swf;
	}
	
	public void register(SimpleObject o){
		if(!objects.containsKey(o.id())){
			objects.put(o.id(), o);
		}
	}
	
	public String getString(SimpleObject o){
		return (int)o.id() +"!,!"+ o.getX() +"!,!"+ o.getY() +"!,!"+ o.getInfo();
	}
	
	public Character[] getKeys(){
		return objects.keySet().toArray(new Character[1]);
	}
	
	public Img previewKey(char c){
		return objects.get(c).getImage();
	}
	
	public boolean addSimpleObject(char c, int x, int y, String s, SimpleWorld w){
		boolean successful = false;
		SimpleObject n = objects.get(c);
		if(n == null){
			return false;
		}
		SimpleObject o = n.getCopy(s);
		if(o == null){
			try {
				SimpleObject newO = n.getClass().newInstance();
				successful = w.addSimpleObject(newO, x, y);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			successful = w.addSimpleObject(o, x, y);
		}
		
		return successful;
	}
	
	public boolean addSimpleObject(char c, int x, int y, SimpleWorld w){
		return addSimpleObject(c, x, y, objects.get(c).getInfo(), w);
	}
	
	public boolean addSimpleObject(String s, SimpleWorld w){
		String[] info = s.split("!,!");
		char c = (char)Integer.parseInt(info[0]);
		int x = Integer.parseInt(info[1]);
		int y = Integer.parseInt(info[2]);
		if(info.length == 4){
			return addSimpleObject(c, x, y, info[3], w);
		} else {
			return addSimpleObject(c, x, y, "", w);
		}
		
	}
}
