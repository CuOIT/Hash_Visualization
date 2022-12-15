package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import hash.Hash_Table;

public class App extends JPanel{
	public Hash_Table hashTable;
	public Graphics g;
	public JPanel p;
	public int speed;
	public App() {
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		newHash(20,hashTable.CHAINING_TABLE,hashTable.DIVISION_METHOD);
		speed=400;
		addComponents();
		
	}
	public void addComponents() {
		p=new JPanel();
		p.setLayout(new GridLayout(3,4,4,20));
		p.setBackground(Color.BLACK);
		p.setSize(new Dimension(0,0));
		JComboBox comboBox=new JComboBox();
		comboBox.addItem("Chaining");
		comboBox.addItem("Linear probe table");
		comboBox.addItem("Quadratic probe table");
		comboBox.addItem("Double hash table");
		p.add(comboBox);
		JComboBox comboBox2=new JComboBox();
		comboBox2.addItem("Division method");
		comboBox2.addItem("Multiplication method");
		comboBox2.addItem("Universal hashing");
		p.add(comboBox2);
		JTextField sizeBox=new JTextField(10);
		p.add(sizeBox);
		JButton newButton=new JButton("New");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sizeBox.getText()!="") {
					int size=Integer.parseInt(sizeBox.getText());
					if(comboBox.getSelectedItem().toString()=="Linear probe table")
						hashTable.hashTableType=hashTable.LINEAR_PROBE_TABLE;
					else if(comboBox.getSelectedItem().toString()=="Quadratic probe table")
						hashTable.hashTableType=hashTable.QUADRA_PROBE_TABLE;
					else if(comboBox.getSelectedItem().toString()=="Double hash table")
						hashTable.hashTableType=hashTable.DOUBLE_HASH_TABLE;
					else if(comboBox.getSelectedItem().toString()=="Chaining")
						hashTable.hashTableType=hashTable.CHAINING_TABLE;
					if(comboBox2.getSelectedItem().toString()=="Multiplication method")
						hashTable.hashFunctionType=hashTable.MULTI_METHOD;
					else if(comboBox2.getSelectedItem().toString()=="Universal hashing")
						hashTable.hashFunctionType=hashTable.UNIVERSAL_HASHING;
				newHash(size,hashTable.hashTableType,hashTable.hashFunctionType);
				}
				else newHash(10,hashTable.hashTableType,hashTable.hashFunctionType);
			}
		});
		p.add(newButton);
		JTextField box=new JTextField(10);
		p.add(box);
		JButton insertButton=new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(box.getText()!="") {
					int value=Integer.parseInt(box.getText());
						hashTable.action=hashTable.INSERT;
						hashTable.selectedBucket.value=value;
				}
			}
		});
		p.add(insertButton);
		JButton delButton= new JButton("Delete");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(box.getText()!="") {
					int value=Integer.parseInt(box.getText());	
					hashTable.selectedBucket.value=value;
					hashTable.action=hashTable.DELETE;
				}	
			}
		});
		p.add(delButton);
		JButton searchButton= new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(box.getText()!="") {
					int value=Integer.parseInt(box.getText());
					hashTable.selectedBucket.value=value;
					hashTable.action=hashTable.SEARCH;
				}
			}
		});
		p.add(searchButton);
		JButton randInsert=new JButton("Random Insert");
		randInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hashTable.action=hashTable.RANDOM_INSERT;
				hashTable.randomInsertCount=15;
			}
		});
		p.add(randInsert);		
		
		JButton stopButton= new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hashTable.randomInsertCount=0;
			}
		});
		p.add(stopButton);
		JButton speedButton= new JButton("Speed up");
		speedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(speed>100) {
					speed=speed/2;
					speedButton.setText("x"+Integer.toString(400/speed));
				}
				else {
					speed=400;
					speedButton.setText("Speed up");
				}
			}
		});
		p.add(speedButton);
		
		JButton quitButton= new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		p.add(quitButton);
		add(p);
	}
	public void newHash(int size,int type,int fType) {
		hashTable=new Hash_Table(size,type,fType);
		
	}
	public void run() {

		while(true) {
			hashTable.update();
			repaint();
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		hashTable.draw(g2);
	}
}
