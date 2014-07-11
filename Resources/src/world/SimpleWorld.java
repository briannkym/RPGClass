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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sprite.ColorImg;
import sprite.Img;
import sprite.ImgUpload;
import view.Pinterface;
import view.Projector;

public class SimpleWorld extends JFrame implements Pinterface{

	List <SimpleObject>objects = new LinkedList<SimpleObject>();
	List <SimpleObject>addList = new LinkedList<SimpleObject>();
	List <SimpleObject>removeList = new LinkedList<SimpleObject>();
	
	private static Comparator<SimpleObject> c = new Comparator<SimpleObject>(){
		@Override
		public int compare(SimpleObject o1, SimpleObject o2) {
			if(o2.getSolid()!=null){
				if(o1.getSolid()!=null){
					return o1.coor_y - o2.coor_y;
				}
				else
				{
					return -1;
				}
			} else if (o1.getSolid()!=null){
				return 1;
			} else {
				return o1.coor_y - o2.coor_y;
			}
		}
		
	};
		
	private Img background;
	private Projector ip;
	private String title;
	
	SimpleSolidMap m;
	private SimpleWorldObject swo = NullSimpleWorldObject.getInstance();

	public SimpleWorld(int width, int height, int cellWidth, int cellHeight, String title){
		m = new SimpleSolidMap(width, height, cellWidth, cellHeight);
		BufferedImage bi = new BufferedImage(m.map.length * cellWidth,
				m.map[0].length * cellHeight, BufferedImage.TYPE_INT_ARGB);
		this.title = title;
		this.ip = new Projector(20.0f, bi, title ,this);
	}
	
	public void start(boolean fullscreen){
		if(fullscreen){
			ip.init(this);
		} else {
			Container c = this.getContentPane();
	        JPanel jp=ip.init(new Dimension(m.map.length * m.cellWidth, m.map[0].length * m.cellHeight));
	        c.setLayout(new BorderLayout());
	        c.add(jp, BorderLayout.NORTH);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setTitle(title);
	        this.setIgnoreRepaint(true);
	        this.pack();
	        this.setResizable(false);
	        this.setVisible(true);
		}
	}
	
	public boolean addSimpleObject(SimpleObject o, int x, int y){
		SimpleSolid s = o.getSolid();
		if(s!=null){
			if(m.map[x][y] == null)
			{
				m.map[x][y] = s;
			} else {
				return false;
			}
			 
		}
		addList.add(o);
		o.w = m;
		o.coor_x = x;
		o.dx = 0;
		o.pre_x = x * m.cellWidth;
		o.coor_y = y;
		o.dy = 0;
		o.pre_y = y * m.cellHeight;
		return true;
	}
	
	public boolean removeSimpleObject(SimpleObject o){
		int i = objects.indexOf(o);
		if (i!=-1){
			removeList.add(o);
			SimpleSolid s = o.getSolid();
			if(s!=null)
			{
				m.map[o.coor_x][o.coor_y] = null;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeSimpleObject(int x, int y){
		boolean removed = false;
		for(SimpleObject o : objects){
			if(o.getX()==x&&o.getY()==y){
				removeList.add(o);
				SimpleSolid s = o.getSolid();
				if(s!=null)
				{
					m.map[o.coor_x][o.coor_y] = null;
				}
				removed = true;
			}
		}
		return removed;
	}
	
	public void clearAll(int width, int height){
		clearAll(width, height, m.cellWidth, m.cellHeight);
	}
	
	public void clearAll(int width, int height, int cellWidth, int cellHeight){
		objects.clear();
		m = new SimpleSolidMap(width, height, cellWidth, cellHeight);
	}
	
	public void setSimpleWorldObject(SimpleWorldObject swo){
		this.swo = swo;
	}
	
	public SimpleWorldObject getSimpleWorldObject(){
		return swo;
	}
	
	public void setBGImage(String sprite)	{
		File f = new File(sprite);
		this.background = ImgUpload.getInstance(f.getParentFile()).getImg(f.getName());
	}
	
	public void setBGImage(int rgba, int width, int height) {
		this.background = new ColorImg(rgba, width, height);
	}
	
	public void setBGImage(Img i){
		this.background = i;
	}
	
	public void reSortZ(){
		Collections.sort(objects,c);
	}
	
	public Projector getProjector(){
		return ip;
	}
	
	@Override
	public void iUpdate(BufferedImage ISlide) {

		Graphics2D g = ISlide.createGraphics();
		g.setColor(new Color(0xFFFFFFFF));
		g.fillRect(0, 0, ISlide.getWidth(), ISlide.getHeight());

		if (background != null) {
			BufferedImage bg = background.getSlide();
			int bg_width = bg.getWidth();
			int bg_height = bg.getHeight();

			for (int x = 0; x < ISlide.getWidth(); x += bg_width) {
				for (int y = 0; y < ISlide.getHeight(); y += bg_height) {
					g.drawImage(bg, x, y, null);
				}
			}
		}
		
		for(SimpleObject s: objects){
			s.move();
		}
		
		for(SimpleObject s: objects){
			s.paintImage(g);
		}
		
		for(SimpleObject s: addList){
			objects.add(s);
		}
		addList.clear();
		
		for(SimpleObject s: removeList){
			objects.remove(s);
		}
		removeList.clear();
		
		swo.updateScreen(ISlide, g);
		g.dispose();
	}
}
