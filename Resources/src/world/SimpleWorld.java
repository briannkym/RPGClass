package world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

import sprite.*;
import view.*;

@SuppressWarnings("serial")
public class SimpleWorld extends JFrame implements Pinterface {

	private Img background;
	private Projector ip;
	private SimpleObject[][][] map;
	private final int cellWidth;
	private final int cellHeight;
	private SimpleWorldObject swo = NullSimpleWorldObject.getInstance();

	public SimpleWorld(SimpleObject[][][] map, int cellWidth, int cellHeight,
			String title) {
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		if (map[0][0].length == 2){
			this.map = map;
		}
		else {
			this.map = new SimpleObject[map.length][map[0].length][2];
		}
		BufferedImage bi = new BufferedImage(map.length * cellWidth,
				map[0].length * cellHeight, BufferedImage.TYPE_INT_ARGB);
		this.ip = new Projector(15.0f, bi, title, this);
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

	public SimpleObject getObject(int x, int y, int i) {
		return map[x][y][i];
	}

	public boolean destroy(int x, int y, int i) {
		if (map[x][y][i] == null) {
			return false;
		} else {
			map[x][y][i] = null;
			return true;
		}
	}

	public boolean destroy(SimpleObject o) {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y][0] == o) {
					map[x][y][0] = null;
					return true;
				} else if (map[x][y][1] == o) {
					map[x][y][1] = null;
					return true;
				}
			}
		}
		return false;
	}

	public boolean add(int x, int y, int i, SimpleObject o) {
		if (map[x][y][i] != null) {
			return false;
		} else {
			int[] m = o.getMovement();
			m[SimpleObject.X] = x;
			m[SimpleObject.Y] = y;
			m[SimpleObject.REL] = 0;
			m[SimpleObject.TIME] = 0;
			m[SimpleObject.PREX] = x;
			m[SimpleObject.PREY] = y;
			map[x][y][i] = o;
			return true;
		}
	}

	public void clear(int width, int height) {
		map = new SimpleObject[width][height][2];
	}

	public SimpleObject[][][] getMap() {
		return map;
	}

	public boolean load(SimpleObject[][][] map) {
		if (map.length == this.map.length
				&& map[0].length == this.map[0].length
				&& map[0][0].length == 2) {
			this.map = map;
			return true;
		} else {
			return false;
		}
	}

	private boolean move(int x1, int y1, int x2, int y2) {
		if (map[x2][y2][0] != null) {
			return false;
		} else {
			map[x2][y2][0] = map[x1][y1][0];
			map[x1][y1][0] = null;
			return true;
		}
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
		int[] m;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y][1] != null) {
					map[x][y][1].update();
					map[x][y][1].paintImage(g, x*cellWidth, y*cellWidth);
					if(map[x][y][0]!=null)
					{
						map[x][y][0].collision(map[x][y][1]);
						map[x][y][1].collision(map[x][y][0]);
					}
				}
				if (map[x][y][0] != null) {
					m = map[x][y][0].getMovement();
					map[x][y][0].update();

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
							map[x][y][0].collision(getObject(m[SimpleObject.X],
									m[SimpleObject.Y], 0));
							map[m[SimpleObject.X]][m[SimpleObject.Y]][0]
									.collision(map[x][y][0]);
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
				if (map[x][y][0] != null) {
					m = map[x][y][0].getMovement();
					if (m[SimpleObject.TIME] > 1) {
						m[SimpleObject.TIME] -= 1;
						int off_x = ((m[SimpleObject.PREX] - m[SimpleObject.X])
								* cellWidth * m[SimpleObject.TIME])
								/ m[SimpleObject.DUR];
						int off_y = ((m[SimpleObject.PREY] - m[SimpleObject.Y])
								* cellHeight * m[SimpleObject.TIME])
								/ m[SimpleObject.DUR];
						map[x][y][0].paintImage(g, x * cellWidth + off_x, y
								* cellHeight + off_y);
					} else {
						m[SimpleObject.TIME] = 0;
						m[SimpleObject.PREX] = x;
						m[SimpleObject.PREY] = y;
						map[x][y][0].paintImage(g, x * cellWidth, y * cellHeight);
					}
				}
			}
		}
		swo.updateScreen(ISlide, g);
		g.dispose();
	}
}
