import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class view2 extends KeyAdapter implements view{
	private JFrame jf;
	private JPanel jp;
	private JTextField textField;
	private JTextArea textArea;
	
	private diaryIO d;
	
	public view2(diaryIO d, String title)
	{
		this.d = d;
		jf = new JFrame(title);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:
			if(!textField.getText().equals("")){
				d.writeLine(textField.getText());
				System.out.println("Saving a thought.");
				textArea.setText(d.read());
			}
			textField.setText("");
			break;
		}
	}

	@Override
	public void start() {
		d.openDiary();
		textField = new JTextField(40);
        textField.addKeyListener(this);
        textArea = new JTextArea(d.read(), 10,40);
        textArea.setEditable(false);
        jp = new JPanel(new BorderLayout());
        jp.add(textField, BorderLayout.NORTH);
        jp.add(textArea, BorderLayout.SOUTH);
        jf.add(jp);
        jf.addWindowListener(new wl());
        jf.pack();
		jf.setVisible(true);
	}
	
	private class wl extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing Diary.");
			d.closeDiary();
		}
	}
}


