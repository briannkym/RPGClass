import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JOptionPane;


public class diaryIO {
	
	private File f;
	private PrintWriter pw;
	private boolean canPrint = false;
	
	
	public diaryIO(String path)	{
		this.f = new File(path);
	}
	
	public boolean openDiary() {
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
			FileOutputStream fos = new FileOutputStream(f, true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			pw = new PrintWriter(bos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		canPrint = true;
		return true;
	}
	
	public void writeLine(String line){
		if (canPrint)
		{
			Date d= new Date();
			pw.write(d.toString()+ ": " + line + "\n");
			//pw.flush();
		}
	}

	public void closeDiary()
	{
		if (pw!=null)
		{
			pw.close();
		}
	}
}
