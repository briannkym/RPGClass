import java.util.Scanner;

public class quizGame
{
	static String[] questions = {"Pick a number (1-4)", 
		"Pick a color (1. Red, 2. Blue, 3. Green, 4. Purple)",
		"Pick an animal (1. Panda, 2. Squirrel, 3. Whale, 4. Jigglypuff) "};
	
	public static void main(String[] args)
	{
		int i = 0;
		Scanner input = new Scanner(System.in);
		for(String s : questions)
		{
			System.out.println(s);
			i = (i + input.nextInt())%4;
		}

		switch (i)
		{
			case 0 :
				System.out.println("You will have a super lucky day!");
				break;
			case 1 :
				System.out.println("You will learn a lot today!");
				break;
			case 2 :
				System.out.println("You will have a bad day today. Don't go outside.");
				break;
			case 3 :
				System.out.println("Today is a good day to make friends");
				break;
			default:
				System.out.println("Error!");
		}
		input.close();
	}
}
