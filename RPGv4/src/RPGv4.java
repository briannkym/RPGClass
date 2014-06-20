import java.util.Scanner;

public class RPGv4 {

	// Variables for the player.
	static int attack = 7;
	static int health = 20;
	// Variables for the NPC
	static int monster_attack = 5;
	static int max_monster_health = 15;

	public static void main(String[] args) {
		String command = "";
		map m = new map();
		//Instantiate the map..
		m.instantiate();
		// First we instantiate a scanner.
		Scanner input = new Scanner(System.in);
		System.out
				.println("Press Q to [Q]uit, O to [O]pen game, and [W] to save game.");
		while (!command.equals("Q")) {
			// Next we print the map
			m.printMap();
			System.out.println("Would you like to move? "
					+ "[Right][Left][Up][Down][Sleep]");
			// Then we use our scanner to read from the user.
			command = input.next();
			m.movePlayer(command.charAt(0));
		}
		System.out.println("Thank you for Playing!");
		input.close();
	}
}
