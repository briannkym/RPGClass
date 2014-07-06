package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import sprite.Img;
import sprite.ImgUpload;

import world.SimpleObject;
import world.SimpleWorld;
import world.SimpleWorldFactory;
import world.SimpleWorldIO;

public class MapMaker extends SimpleObject implements MouseListener,
		KeyListener {

	final static int width = 16;
	final static int height = 16;
	private SimpleWorldFactory swf = SimpleWorldFactory.getInstance();
	private SimpleWorld w;
	private Character[] id;
	private int index = 0;
	private boolean destroy = false;
	private Img cursor = ImgUpload.getInstance(new File("../sprites/things"))
			.getImg("cursor.png");
	private SimpleWorldIO wio = new SimpleWorldIO("map.txt");

	public MapMaker(SimpleWorld w) {
		Register.loadObjects();
		id = swf.getKeys();
		this.w = w;
		this.setImage(cursor);
		this.setOffset(-4, -4);
		w.addSimpleObject(this, 0, 0);
		w.addKeyListener(this);
		w.getContentPane().addMouseListener(this);
	}

	public char nextIndex() {
		index = (index + 1) % id.length;
		return id[index];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int newx = e.getX() / width;
		int newy = e.getY() / height;
		if (this.getX() != newx || this.getY() != newy) {
			this.moveCell(newx, newy, 4, false);
			this.setImage(cursor);
		} else {
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				this.setImage(cursor);
				swf.addSimpleObject(id[index], newx, newy, w);
				w.reSortZ();
				break;
			case MouseEvent.BUTTON2:
				w.removeSimpleObject(newx, newy);
				w.addSimpleObject(this, getX(), getY());
				break;
			case MouseEvent.BUTTON3:
				this.setImage(swf.previewKey(nextIndex()));
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void collision(SimpleObject s) {
		if (destroy) {
			w.removeSimpleObject(s);
		}
	}

	@Override
	public void update() {
	}

	@Override
	public char id() {
		return 17;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			if (wio.openWorld()) {
				wio.writeWorld(w);
				System.out.println("Saving...");
			}
			wio.closeWorld();
			break;
		case KeyEvent.VK_L:
			wio.readWorld(w);
			w.addSimpleObject(this, getX(), getY());
			System.out.println("Loading...");
			wio.closeWorld();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}
