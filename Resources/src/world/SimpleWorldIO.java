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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class SimpleWorldIO {
	private File f;
	private PrintWriter pw;
	private boolean canPrint = false;
	private SimpleWorldFactory swf = SimpleWorldFactory.getInstance();
	
	public SimpleWorldIO(String path)	{
		this.f = new File(path);
	}
	
	public boolean openWorld() {
		if(!f.exists()) {
			try{
				 if (!f.createNewFile())
				 {
					 return false;
				 }
			} catch(IOException e){ 
				e.printStackTrace();
				return false;
			}
		}
		
		if(openPrinter()) {
		return true;
		}
		else {
			return false;
		}
	}
	
	private boolean openPrinter() {
		try {
			FileOutputStream fos = new FileOutputStream(f, false);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			pw = new PrintWriter(bos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		canPrint = true;
		return true;
	}
	
	public void writeWorld(SimpleWorld w){
		if (canPrint)
		{
			pw.write(w.m.map.length + "," + w.m.map[0].length + "\n");
			for(SimpleObject o : w.objects){
				pw.write(swf.getString(o));
				pw.write("\n");
			}
			pw.flush();
		}
	}
	
	public void readWorld(SimpleWorld w)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			String[] dim = line.split(",");
			w.clearAll(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
			while((line = reader.readLine())!=null){
				swf.addSimpleObject(line, w);		
			}
			reader.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
							"Error: Couldn't read world.");
			e.printStackTrace();
		}
	}
	
	public void closeWorld()
	{
		if (pw!=null)
		{
			pw.close();
		}
	}
}
