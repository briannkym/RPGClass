import java.util.Scanner;

public class HW1
{
		public static void main(String[] args)
		{
				Scanner input = new Scanner(System.in);
				System.out.print("Would you like to [add], [sub]tract,"+
								"[mul]tiply, [div]ide?, or [exp]onentiate?");
				String operation = input.next();
				
				System.out.println();
				System.out.println("Please enter your equation: ");
				if(operation.equals("add"))
				{
						int i1 = input.nextInt();
						System.out.print(" + ");
						int i2 = input.nextInt();
						int r = i1 + i2;
						System.out.print(" = " + r);
				}
				else if (operation.equals("sub"))
				{
						int i1 = input.nextInt();
						System.out.print(" - ");
						int i2 = input.nextInt();
						int r = i1 - i2;
						System.out.print(" = " + r);
				}
				else if (operation.equals("mul"))
				{
						int i1 = input.nextInt();
						System.out.print(" * ");
						int i2 = input.nextInt();
						int r = i1 * i2;
						System.out.print(" = " + r);
				}
				else if (operation.equals("div"))
				{
						int i1 = input.nextInt();
						System.out.print(" / ");
						int i2 = input.nextInt();
						int r = i1 / i2;
						System.out.print(" = " + r);
				}
				else if (operation.equals("exp"))
				{
						int i1 = input.nextInt();
						System.out.print(" ^ ");
						int i2 = input.nextInt();
						int r = (int) Math.pow(i1, i2);
						System.out.print(" = " + r);
				}
				else
				{
						System.out.println("Invalid operation.");
				}
				input.close();
		}
}

