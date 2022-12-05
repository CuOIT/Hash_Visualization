package main;

import javax.swing.JButton;

public class UI_Button extends JButton{
	public UI_Button(String text) {
		super(text);
		Mouse_Listener mouse=new Mouse_Listener();
		addMouseListener(mouse);
	}
	
	public void run() {
		
	}
}
