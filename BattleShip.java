import java.util.*;

public class BattleShip {
	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Har Har Har, welcome to me pirate ship game!");
		//Declare and instantiate the map.
		char[][] map = new char[4][4];
		
		//Instantiate the map variables with blank characters.
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map[y].length; x++){
				map[y][x] = '_';
			}
		}
		
		//Set the enemy ship to random position.
		int enemyShipY = (int)Math.random()*map.length;
		int enemyShipX = (int)Math.random()*map[0].length;
		
		map[enemyShipY][enemyShipX] = 'S';

		int chances = 10;
		//A loop for the game. It ends when the user types quit.
		for(String cont =""; !cont.equals("quit") && chances > 0;cont = s.nextLine()){
			//Decrement the number of chances.
			chances --;
			
			// Get a guess from the user!
			System.out.println("Whar X would you land lubber tink the ship be?");
			int guessX = s.nextInt();
			System.out.println("Whar Y would you land lubber  tink the ship be?");
			int guessY = s.nextInt();
			
			//See if they guessed correctly!
			if(map[guessY][guessX] == 'S'){
				map[guessY][guessX] = 'H';
				System.out.println("Arg you sunk my battle ship!");
			} else {
				map[guessY][guessX] = 'M';
				System.out.println("Har Har Har you missed!");
			}
			
			
			//Print out the map.
			for(int y = 0; y < map.length && chances > 0; y++){
				for(int x = 0; x < map[y].length; x++){
					if(map[y][x] == 'S'){
						System.out.print(" _");
					} else {
						System.out.print(" " + map[y][x]);
					}
				}
				System.out.println();
			}
			
			
			System.out.println("Would you like to stop?[quit]");
			//The terminal automatically reads this line for some reason.
			s.nextLine();			
		}
	}
}

