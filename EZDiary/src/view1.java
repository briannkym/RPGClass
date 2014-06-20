import java.util.Scanner;



public class view1 implements view{
	private diaryIO d;
	
	public view1(diaryIO d)
	{
		this.d = d;
	}
	
	@Override
	public void start(){
		d.openDiary();
		Scanner input = new Scanner(System.in);
		String thought; 
		
		do{ 
			System.out.print("Enter a thought: ");
			thought = input.nextLine();
			if (!thought.equals("")) {
				d.writeLine(thought);
			}
		} while (!thought.equals(""));
		input.close();

		System.out.println("Closing Diary.");
		d.closeDiary();
	}
	
}
