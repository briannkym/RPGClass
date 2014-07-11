package player;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import world.SimpleWorldObject;

public class GUI extends SimpleWorldObject{

	@Override
	public void updateScreen(BufferedImage bi, Graphics2D g) {
		g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
		g.drawString("Sulyeob!", 20, 300);
	}

}
