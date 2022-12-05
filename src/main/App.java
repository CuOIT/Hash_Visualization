package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hash.Hash_Table;

public class App extends JPanel implements Runnable{

	public final int SCREEN_WIDTH=1200;
	public final int SCREEN_HEIGHT=800;
	public Graphics g;
	public Hash_Table hashTable;
	public Mouse_Listener mouse=new Mouse_Listener();
	public App() {
		//this.setPreferredSize(new Dimension(800,700));
		//set size of this class
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		addMouseListener(mouse);
		this.setFocusable(true);
		hashTable=new Hash_Table();
		add(new UI_Button("New"));
	}
	
	@Override
	public void run() {
		//raphics2D g2=(Graphics2D) g;
		//hashTable.draw(g);
		update();
		repaint();
	}
	public void update() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
		g2.setColor(Color.blue);
		g2.drawString("HASH", 100,100);
	}
}
