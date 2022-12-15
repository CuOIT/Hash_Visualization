package hash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bucket extends Rectangle{
	int id;
	public int value;
	
	public Bucket(int id,int value,int x, int y,int width,int height) {
		this.id=id;
		this.value=value;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public void draw(Graphics2D g2) {
		g2.setColor(new Color(248, 200, 20));
		g2.fill(this);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,height/2));
		if(id>=0) {
			g2.drawString(Integer.toString(id),x-width/3,y+2*height/3);
			if(value!=0)
			{
				g2.setColor(Color.yellow);
				g2.fill(this);
			}
			else return;
		}else if(id==-1) {
			g2.setColor(Color.yellow);
			g2.fill(this);
		}
		else if(id==-2) {
			g2.setColor(Color.green);
			g2.fill(this);
			if(value==0) return;
		}
		g2.setColor(new Color(0,0,0));
		g2.drawString(Integer.toString(value), x+width/3, y+2*height/3);
		//g2.setColor(null)
	}
}
