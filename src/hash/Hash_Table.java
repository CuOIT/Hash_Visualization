package hash;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
public class Hash_Table {
	public final int SCREEN_WIDTH=2000;
	public final int SCREEN_HEIGHT=900;
	
	public int  size;
	
	public int x=500;
	public int y=150;
	
	public int width,height;
	
	public ArrayList<LinkedList<Bucket>> buckets;
	
	public Bucket cBucket;
	public int iterator=0;
	public int selectedValue=0;
	
	public Bucket selectedBucket;
	//public Bucket hashFunctionBucket;
	
	public String message="";
	public int hashTableType=CHAINING_TABLE;
	public static final int CHAINING_TABLE=1;
	public static final int LINEAR_PROBE_TABLE=2;
	public static final int QUADRA_PROBE_TABLE=3;
	public static final int DOUBLE_HASH_TABLE=4;
	
	public int hashFunctionType=DIVISION_METHOD;
	public static final int DIVISION_METHOD=5;
	public static final int MULTI_METHOD=6;
	public static final int UNIVERSAL_HASHING=7;
	
	public int action=0;
	public static final int INSERT=8;
	public static final int DELETE=9;
	public static final int SEARCH=10;
	public static final int RANDOM_INSERT=11;
	
	public int randomValue;
	public int randomInsertCount;
	
	//a,b in UNIVERSAL_HASHING;
	public int a;
	public int b;
	
	public Hash_Table(int size,int type,int hashFunctionType) {
		this.size=size;
		this.hashTableType=type;
		this.hashFunctionType=hashFunctionType;
		

		if(size>=15) {
		height=(8*SCREEN_HEIGHT)/10/size;
		width=2*height;
		}
		else {
		height=50;
		width=100;
		}
		selectedBucket=new Bucket(-1,0,x/10,height*size/2+y,width-2,height-2);
		//hashFunctionBucket=new Bucket(-1,0,x/2,height*size/2+y,width-2,height-2);
		cBucket=new Bucket(-2,0,x,y,width,height-1);
		buckets=new ArrayList<LinkedList<Bucket>>();
		for(int i=0;i<size;i++) {
		LinkedList<Bucket> list=new LinkedList<Bucket>();
		Bucket bucket=new Bucket(i,0,x,height*i+y,width,height-1);
		list.addFirst(bucket);
		buckets.add(list);
		}
		Random rn=new Random();
		while(a==0)	a=Math.abs(rn.nextInt()%379);
		b=Math.abs(rn.nextInt()%379);

	}
	
	public void insert(int value) {
		if(hashTableType==CHAINING_TABLE) {
			int i=h(value,hashFunctionType);
			cBucket.x=x+iterator*6*width/5;
			cBucket.y=y+height*i;	
			cBucket.value=value;
			if(iterator==buckets.get(i).size()) {
				Bucket b=new Bucket(-1,value,cBucket.x,cBucket.y,width,height-1);
				buckets.get(i).add(b);
				iterator=0;
				action=0;
			}
			else iterator++;
		}
		else {
				int i=hash(value,iterator,hashTableType);
				cBucket.value=buckets.get(i).get(0).value;
				cBucket.x=x;
				cBucket.y=y+height*i;
				if(iterator>=size)
				{
				iterator=0;
				action=0;
				randomInsertCount=0;
				}
				else if(buckets.get(i).get(0).value!=0) {
					iterator++;
				}
				else {
					cBucket.value=value;
					buckets.get(i).get(0).value=value;
					iterator=0;
					action=0;
				}
			}

	}
	
	public void randomInsert() {
		if(iterator==0) {
			Random rn=new Random();
			randomValue=Math.abs(rn.nextInt()%1000);
		}
			selectedBucket.value=randomValue;
			insert(randomValue);
		if(iterator==0 && randomInsertCount!=0) {
			action=RANDOM_INSERT;
			randomInsertCount--;
		}
	}
	
	
	public void search(int value) {
		if(hashTableType==CHAINING_TABLE) {
			int i=h(value,hashFunctionType);
			cBucket.x=x+iterator*6*width/5;
			cBucket.y=y+height*i;
			if(iterator==buckets.get(i).size()) {
				cBucket.x-=6*width/5;
				iterator=0;
				action=0;
				message="Not found!";
				return;
			}
			else if(buckets.get(i).get(iterator).value==value) {
				cBucket.value=buckets.get(i).get(iterator).value;
				message="Found value "+value+" in bucket "+i; 
				iterator=0;
				action=0;
				return;
			}
			cBucket.value=buckets.get(i).get(iterator).value;
			iterator++; 
		}else {
			//OPEN ADDRESSING
				int i=hash(value,iterator,hashTableType);
				cBucket.x=x;
				cBucket.y=y+height*i;
				int temp=buckets.get(i).get(0).value;
				cBucket.value=temp;
				if(temp==0 || iterator==size)
				{
					iterator=0;
					action=0;
					message="Not found!";
					return;
				}
				else if(temp==value)
					{
						iterator=0;
						action=0;
						message="Found value "+value+" in bucket "+i; 
						return;
					}
					iterator++; 
				}
	}
	
	public void delete(int value) {
		if(hashTableType==CHAINING_TABLE) {
			int i=h(value,hashFunctionType);
			cBucket.x=x+iterator*6*width/5;
			cBucket.y=y+height*i;
			if(iterator==buckets.get(i).size()) {
				cBucket.x-=6*width/5;
				iterator=0;
				action=0;
				message="Not found!";
				return;
			}
			else if(buckets.get(i).get(iterator).value==value) {
				buckets.get(i).remove(iterator);
				if(iterator<buckets.get(i).size())
					cBucket.value=buckets.get(i).get(iterator).value;
				else cBucket.value=0;
				while(iterator<buckets.get(i).size())
				{
					buckets.get(i).get(iterator).x-=6*width/5;
					iterator++;
				}
				message="Deleted value "+value+" in bucket "+i;
				iterator=0;
				action=0;
				
				return;
			}
			cBucket.value=buckets.get(i).get(iterator).value;
			iterator++; 
		}else {
				int i=hash(value,iterator,hashTableType);
				cBucket.x=x;
				cBucket.y=y+height*i;
				int temp=buckets.get(i).get(0).value;
				if(temp==0 || iterator==size)
				{
					iterator=0;
					action=0;
					message="Not found!";
					return;
				}
				else if(temp==value)
					{
						iterator=0;
						action=0;
						buckets.get(i).get(0).value=0;
						cBucket.value=0;
						message="Deleted value "+value+" in bucket "+i;  
						return;
					}
					iterator++; 
				}
				
	}
	public void drawArrow(Graphics g, int x0, int y0, int x1,
            int y1, int headLength, int headAngle) {
        double offs = headAngle * Math.PI / 180.0;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int[] xs = { x1 + (int) (headLength * Math.cos(angle + offs)), x1,
                x1 + (int) (headLength * Math.cos(angle - offs)) };
        int[] ys = { y1 + (int) (headLength * Math.sin(angle + offs)), y1,
                y1 + (int) (headLength * Math.sin(angle - offs)) };
        g.drawLine(x0, y0, x1, y1);
        g.drawPolyline(xs, ys, 3);
	}
	
	public void  draw(Graphics2D g2) {
		for(int i=0;i<buckets.size();i++)
		{
			g2.setColor(Color.blue);
			int len=buckets.get(i).size();
			for(int j=0;j<len;j++)
			{	
				int xA=x+(6*width/5)*j;
				int yA=y+height/2+height*i;
				if(j>0)
				drawArrow(g2,xA-width/5,yA,xA,yA,width/12,width/2);
				buckets.get(i).get(j).draw(g2);

			}
			}
		selectedBucket.draw(g2);
		//hashFunctionBucket.draw(g2);
		cBucket.draw(g2);
	}

	public int h(int k,int hashFunctionType) {
		if(hashFunctionType==DIVISION_METHOD)
		return k%size;
		else if(hashFunctionType==MULTI_METHOD) {
			double A=(Math.sqrt(5)-1)/2;
			return (int)(size*((k*A)%1));
		}
		else
		{
			return (((a*k)+b)%379)%size;
		}
	}
	
	public int hash(int k,int i,int hashTableType) {
		if(hashTableType==LINEAR_PROBE_TABLE) 
			return (h(k,hashFunctionType)+i)%size;
		else if(hashTableType==QUADRA_PROBE_TABLE) 
			return (h(k,hashFunctionType)+(size-1)*i+(size-1)*i*i)%size;
		else return (h(k,hashFunctionType)+i* ((size-1)-(k%(size-1)))  )%size;

		
	}

	public void update() {
		if(action==0)
			if(message!="") {
				JOptionPane.showMessageDialog(new JFrame(), message);
				message="";
				return;
			}
		if(action==INSERT) {
			insert(selectedBucket.value);
		}
		else if(action==DELETE) {
			delete(selectedBucket.value);
		}
		else if(action==SEARCH) {
			search(selectedBucket.value);
		}
		else if(action==RANDOM_INSERT) {
			randomInsert();
		}
	}
}
