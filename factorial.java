class factorial
{
	public static void main(String[] args)
	{
		int i = 0;
		if (args.length > 0)
		{
			i = Integer.parseInt(args[0]);
		}
		
		System.out.println(i + "! = " + factorial(i));

	}

	public static int factorial(int n)
	{
		if(n > 1)
		{
			return n * factorial(n-1);
		}
		else if(n >= 0)
		{
			return 1;
		}
		return -1;
		
	}
}
