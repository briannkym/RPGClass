
public class main {
	
	public static void main(String args[])
	{
		diaryIO d = new diaryIO("diary.txt");
		view v = new view1(d);
		v.start();		
	}
}
