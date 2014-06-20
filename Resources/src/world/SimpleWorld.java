package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

import sprite.*;
import view.*;

@SuppressWarnings("serial")
public class SimpleWorld extends JFrame implements Pinterface {

	private Img background;
	private Projector ip;
	private SimpleObject[][] map;
	private final int cellWidth;
	private final int cellHeight;
	private SimpleWorldObject swo = NullSimpleWorldObject.getInstance();

	public SimpleWorld(SimpleObject[][] map, int cellWidth, int cellHeight,
			String title) {
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		this.map = map;
		BufferedImage bi = new BufferedImage(map.length * cellWidth,
				map[0].length * cellHeight, BufferedImage.TYPE_INT_ARGB);
		this.ip = new Projector(25.0f, bi, title, this);
		ip.init(this);
	}

	public void setBackground(String sprite) {
		File f = new File(sprite);
		this.background = ImgUpload.getInstance(f.getParentFile()).getImg(
				f.getName());
	}

	public void setBackground(Img i) {
		this.background = i;
	}

	public SimpleObject getObject(int x, int y) {
		return map[x][y];
	}

	public boolean destroy(int x, int y) {
		if (map[x][y] == null) {
			return false;
		} else {
			map[x][y] = null;
			return true;
		}
	}

	public boolean destroy(SimpleObject o) {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y] == o) {
					map[x][y] = null;
					return true;
				}
			}
		}
		return false;
	}

	public boolean add(int x, int y, SimpleObject o) {
		if (map[x][y] != null) {
			return false;
		} else {
			int[] m = o.getMovement();
			m[SimpleObject.X] = x;
			m[SimpleObject.Y] = y;
			m[SimpleObject.REL] = 0;
			m[SimpleObject.TIME] = 0;
			m[SimpleObject.PREX] = x;
			m[SimpleObject.PREY] = y;
			map[x][y] = o;
			return true;
		}
	}

	public void clear(int width, int height) {
		map = new SimpleObject[width][height];
	}

	public SimpleObject[][] getMap() {
		return map;
	}

	public boolean load(SimpleObject[][] map) {
		if (map.length == this.map.length
				&& map[0].length == this.map[0].length) {
			this.map = map;
			return true;
		} else {
			return false;
		}
	}

	private boolean move(int x1, int y1, int x2, int y2) {
		if (map[x2][y2] != null) {
			return false;
		} else {
			map[x2][y2] = map[x1][y1];
			map[x1][y1] = null;
			return true;
		}
	}

	@Override
	public void iUpdate(BufferedImage ISlide) {

		Graphics g = ISlide.getGraphics();
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
		int[] m;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y] != null) {
					m = map[x][y].getMovement();
					map[x][y].update();

					if (m[SimpleObject.REL] == 1) {
						m[SimpleObject.X] += x;
						m[SimpleObject.Y] += y;
						m[SimpleObject.REL] = 0;
					}

					if (m[SimpleObject.X] < 0) {
						m[SimpleObject.X] = 0;
					} else if (m[SimpleObject.X] >= map.length) {
						m[SimpleObject.X] = map.length - 1;
					}

					if (m[SimpleObject.Y] < 0) {
						m[SimpleObject.Y] = 0;
					} else if (m[SimpleObject.Y] >= map[0].length) {
						m[SimpleObject.Y] = map[0].length - 1;
					}

					if (m[SimpleObject.X] != x || m[SimpleObject.Y] != y) {
						if (!move(x, y, m[SimpleObject.X], m[SimpleObject.Y])) {
							map[x][y].collision(getObject(m[SimpleObject.X],
									m[SimpleObject.Y]));
							map[m[SimpleObject.X]][m[SimpleObject.Y]]
									.collision(map[x][y]);
							m[SimpleObject.X] = x;
							m[SimpleObject.Y] = y;
							m[SimpleObject.TIME] = 0;
						}
					}
				}
			}
		}

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y] != null) {
					m = map[x][y].getMovement();
					if (m[SimpleObject.TIME] > 1) {
						m[SimpleObject.TIME] -= 1;
						int off_x = ((m[SimpleObject.PREX] - m[SimpleObject.X])
								* cellWidth * m[SimpleObject.TIME])
								/ m[SimpleObject.DUR];
						int off_y = ((m[SimpleObject.PREY] - m[SimpleObject.Y])
								* cellHeight * m[SimpleObject.TIME])
								/ m[SimpleObject.DUR];
						map[x][y].paintImage(g, x * cellWidth + off_x, y
								* cellHeight + off_y);
					} else {
						m[SimpleObject.TIME] = 0;
						m[SimpleObject.PREX] = x;
						m[SimpleObject.PREY] = y;
						map[x][y].paintImage(g, x * cellWidth, y * cellHeight);
					}
				}
			}
		}
		swo.updateScreen(ISlide, g);
		g.dispose();
	}
}
