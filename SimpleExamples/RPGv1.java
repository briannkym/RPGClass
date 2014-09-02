import java.util.Scanner;

public class RPGv1
{
		public static void main(String[] args)
		{
				String name;
				String answer;

				//First we instantiate a scanner.
				Scanner input = new Scanner(System.in);
				System.out.print("What is your name? ");
				//Next, we use our scanner to read a line in from the user.
				name = input.nextLine();

				System.out.println();
				System.out.println("Hello " + name +".");

				//Begin the game
				System.out.println("Suddenly, a monster attacks you in the wild!");
				System.out.println("The monster asks you a riddle: How does Bill" +
								" Gates enter his house?");

				System.out.print("He uses ");
				answer = input.next();

				if(answer.equals("Windows"))
				{
						System.out.println("The monster was defeated!");
				}
				else
				{
						System.out.println("The monster eats you. You lose.");
				}
				input.close();
		}
}


