package main;
import java.awt.Dimension;
import java.awt.FlowLayout;

import	javax.swing.JFrame;
public class Main {
	public static JFrame window;
	public static void main(String[] args) {
	window=new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("HASH TABLE");
	window.setPreferredSize(new Dimension(1200,800));
	//window.setLayout(new FlowLayout());
	App app=new App();
	//UI_Panel ui_panel=new UI_Panel();
	window.add(app);
	//window.add(ui_panel);
	window.pack();
	window.setLocationRelativeTo(null);
	window.setVisible(true);
//	window.addK
	app.run();
//	gamePanel.setUpGame();
//	
//	gamePanel.startGameThread();
	}
}