package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NullSimpleWorldObject extends SimpleWorldObject{

private static NullSimpleWorldObject n = new NullSimpleWorldObject();
    
    private NullSimpleWorldObject()
    {
    }
    
    public static NullSimpleWorldObject getInstance()
    {
        return n;
    }

	
	@Override
	public void updateScreen(BufferedImage bi, Graphics g) {		
	}

}
