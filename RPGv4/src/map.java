public class map {

	// Array for the map
	char[][] map = new char[10][10];
	player p = new player(1, 1, 7, 20);
	monster m = new monster(5, 15);
	worldIO w = new worldIO("map.txt");
	
	public map() {
	}

	public void instantiate() {
		// Instantiate the map
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				map[x][y] = '\u2591';
			}
		}

		// Add some monsters
		map[2][8] = 'M';
		map[7][6] = 'M';
	}

	public char getPlayerCollision() {
		return map[p.x][p.y];
	}

	public void setSquare(int x, int y, char c) {
		map[x][y] = c;
	}

	public void printMap() {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				if (x == p.x && y == p.y) {
					System.out.print(" " + 'P');
				} else {
					System.out.print(" " + map[x][y]);
				}
			}
			System.out.println();
		}
	}

	public void movePlayer(char direction) {
		switch (direction) {
		case 'R':
			if (p.x < 9) {
				p.x++;
			}
			break;
		case 'L':
			if (p.x > 0) {
				p.x--;
			}
			break;
		case 'U':
			if (p.y > 0) {
				p.y--;
			}
			break;
		case 'D':
			if (p.y < 9) {
				p.y++;
			}
			break;
		case 'S':
			p.health = (p.health + 20) / 2;
			System.out.println("You have restored some health");
			break;
		case 'Q':
			break;

		case 'O':
			if(!loadMap()){
				System.out.println("Load map failed.");
			}
			break;
		case 'W':
			if(!saveMap()){
				System.out.println("Save map failed.");
			}
			break;

		default:
			System.out.println("We do not recognize " + "that command.");
		}
		// Check for a monster
		if (getPlayerCollision() == 'M') {
			// Battle a monster!
			m.battleMonster(p);
			setSquare(p.x, p.y, 'X');
		}

	}

	public boolean loadMap() {
		if(w.openWorld()){
			map = w.readWorld(10, 10);
			w.closeWorld();
		}
		else {
			instantiate();
			return false;
		}
		if(map == null){
			instantiate();
			return false;
		}
		return true;
	}

	public boolean saveMap() {
		if(w.openWorld()){
			w.writeWorld(map);
			w.closeWorld();
			return true;
		}
		return false;
	}

}
