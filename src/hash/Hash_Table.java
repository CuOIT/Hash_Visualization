package hash;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
 
public class Hash_Table<T> {
	public int  size;
	public ArrayList<T> buckets;
	Shape so;
	Rectangle table;
	BasicStroke stroke;
	public Hash_Table() {
		size=10;
		buckets=new ArrayList<T>();
		//stroke=new BasicStroke(1);
		table=new Rectangle(400,400,300,200);
		//so=stroke.createStrokedShape(table);

	}
//	public void  draw(Graphics g) {
//		Graphics2D g2=(Graphics2D)g;
//		g2.setColor(Color.blue);
//		g2.fill(so);
//	}
}
