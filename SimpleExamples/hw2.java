public class hw2 {
	public static void main(String[] args){
		int i = 0;
		if(args.length > 0){
			i = Integer.parseInt(args[0]);
		}
		System.out.println("F(" + i + ")=" + fibonacci(i));
	}
	
	public static int fibonacci(int n){
		if(n > 1){
			return fibonacci(n-1)+fibonacci(n-2);
		} else if (n >= 0){
			return n;
		}
		return -1;
	}
}
