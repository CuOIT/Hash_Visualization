package main;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse_Listener extends MouseAdapter{
	public Component com;
	
	public Mouse_Listener() {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+" "+e.getY());
		//if (com.isInstance(JButton))
		System.out.println("Insert a key to hashtable");
		//else
	
	}
}
