public class Method{

	public static void main(String[] args){
		System.out.println(google(25, 501));
		int y = google(3, 22) + google(4, 25);
		
		System.out.println(y);
		//Where most of our code has been.
	}

	public static int google(int x, int y){
		x = x - 50;
		y = y % 10;
		int z = 1;
		for(int i = 0; i<10; i++)
		{
			z = z* x ;
		}
		return z*y;
	}



}
