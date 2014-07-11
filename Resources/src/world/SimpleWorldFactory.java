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
