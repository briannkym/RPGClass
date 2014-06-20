import java.util.Scanner;

public class RPGv3
{

		//Variables for the player.
		static int attack = 7;
		static int health = 20;
		static int player_x = 1;
		static int player_y = 1;

		//Variables for the NPC
		static int monster_attack = 5;
		static int max_monster_health = 15;

		//Array for the map
		static char[][] map = new char[10][10];
		
		public static void main(String[] args)
		{
				String command = "";
								
				//Instantiate the map
				for(int y = 0; y < map.length; y++)
				{
						  for(int x = 0; x < map[0].length; x++)
						  {
									 map[x][y] = '\u2591';
						  }
				}

				//Add some monsters
				map[2][8] = 'M';
				map[7][6] = 'M';

				//First we instantiate a scanner.
				Scanner input = new Scanner(System.in);
				System.out.println("Press Q to [Q]uit. ");
				while(!command.equals("Q"))
				{
						  //Next we print the map
						  printMap();
						  //Check for a monster
						  if(map[player_x][player_y]=='M')
						  {
									 //Battle a monster!
									 battleMonster();
									 map[player_x][player_y] = '\u2591';
						  }
						  System.out.println("Would you like to move? "+
												"[Right][Left][Up][Down][Sleep]");
						  //Then we use our scanner to read from the user.
						  command = input.next();
						  movePlayer(command.charAt(0));
				}
				System.out.println("Thank you for Playing!");
				input.close();
		}

		public static void printMap()
		{

			for(int y = 0; y < map.length; y++)
			{
				for(int x = 0; x < map[0].length; x++)
				{
					if(x == player_x && y == player_y)
					{
							  System.out.print(" " + 'P');
					}
					else
					{
							  System.out.print(" " + map[x][y]); 
					}
				}

			System.out.println();
			}

		}

		public static void battleMonster()
		{
			 int monster_health = max_monster_health;
			 System.out.println("A spider notices you!");
			 while(monster_health > 0)
			 {
						health -= (int)(monster_attack*Math.random());
						System.out.println("The spider hits you : " + health +"hp remaining!");
						if(health <=0)
						{
								  System.out.println("Game Over");
								  System.exit(0);
						}
						monster_health -= (int)(attack*Math.random());
						System.out.println("You bop the spider on its head.");
			 }
			 System.out.println("You have defeated the spider!");
		}

		public static void movePlayer(char c)
		{
			  switch (c)
			  {
						 case 'R':
									if(player_x < 9)
									{
											  player_x++;
									}
									break;
						 case 'L':
									if(player_x > 0)
									{
											  player_x--;
									}
									break;
						 case 'U':
									if(player_y > 0)
									{
											  player_y--;
									}
									break;
						 case 'D':
									if(player_y < 9)
									{
											  player_y++;
									}
									break;
						 case 'S':
									health = (health + 20)/2;
									System.out.println("You have restored some health");
									break;
						 case 'Q': 
									break;
						 default:
									System.out.println("We do not recognize "+
														 "that command.");
			  }

		}
}


