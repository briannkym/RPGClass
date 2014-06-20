

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class worldIO {
	private File f;
	private PrintWriter pw;
	private boolean canPrint = false;
	
	public worldIO(String path)	{
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
	
	public void writeWorld(char[][] w){
		if (canPrint)
		{
			for(int y = 0; y < w.length; y++){
				for(int x = 0; x < w[0].length; x++){
					pw.write(w[x][y]);;
				}
				pw.write("\n");
			}
			pw.flush();
		}
	}
	
	public char[][] readWorld(int width, int height)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			char[][] w = new char[width][height];
			for (int y = 0; y < w.length; y++)
			{
				line = reader.readLine();
				for(int x = 0; x < w[0].length; x++)
				{
					w[x][y] = line.charAt(x);
				}
			}
			reader.close();
			return w;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
							"Error: Couldn't read world.");
			return null;
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
