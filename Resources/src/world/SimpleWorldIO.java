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
	private SimpleWorldFactory swf;
	
	public SimpleWorldIO(String path, SimpleWorldFactory swf)	{
		this.f = new File(path);
		this.swf = swf;
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
			SimpleObject[][] w_arr = w.getMap();
			pw.write(w_arr.length + "," + w_arr[0].length + "\n");
			for(int y = 0; y < w_arr.length; y++){
				for(int x = 0; x < w_arr[0].length; x++){
					pw.write(swf.getChar(w_arr[x][y]));
				}
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
			w.clear(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
			SimpleObject[][] w_arr = w.getMap();
			for (int y = 0; y < w_arr.length; y++)
			{
				line = reader.readLine();
				for(int x = 0; x < w_arr[0].length; x++)
				{
					w.add(x,y, swf.getSimpleObject(line.charAt(x)));
				}
			}
			reader.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
							"Error: Couldn't read world.");
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
