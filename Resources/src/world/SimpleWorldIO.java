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
