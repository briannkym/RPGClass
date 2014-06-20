
public class main {
	
	public static void main(String args[])
	{
		diaryIO d = new diaryIO("diary.txt");
		view v = new view2(d, "Diary 2.0");
		v.start();		
	}
}
