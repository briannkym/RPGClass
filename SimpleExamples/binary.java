class binary
{
	public static void main(String[] args)
	{
		int i = 0;
		if (args.length > 0)
		{
			i = Integer.parseInt(args[0]);
		}
		convert(i);
	}

	public static void convert(int n)
	{
		int i;
		for(i = 1; i <= n; i*=2);
		
		System.out.print(n + " base 10 = ");
		while(n > 0 || i > 0)
		{
			if(n >= i)
			{
				n -= i;
				System.out.print("1");	
			}
			else
			{
				System.out.print("0");
			} 
			i /= 2;
		}

		System.out.println(" base 2(binary).");
	}
}
