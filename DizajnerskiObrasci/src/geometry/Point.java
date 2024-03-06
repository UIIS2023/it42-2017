package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable {

	private int x;
	private int y;
	
	
	public Point() {
		
	}
	
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point(int x,int y,boolean selected) {
		this(x,y);
		setSelected(selected);;
	}
	
	public Point(int x,int y,Color c) {
		this(x,y);
		setBorderColor(c);
	}

	public Point(int x,int y,Color b,boolean selected) {
		this.x=x;
		this.y=y;
		setBorderColor(b);
		setSelected(selected);
	}
	
	@Override
	public Point clone()
	{
		Point clone=new Point();
		clone.setX(this.getX());
		clone.setY(this.getY());
		clone.setBorderColor(this.getBorderColor());
		clone.setSelected(this.isSelected());
		return clone;
	}
	
	@Override
	public void draw(Graphics g) {
        if(this.getBorderColor()!=null) {
			g.setColor(getBorderColor());
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		g.setColor(Color.BLACK);
		
        
        if(isSelected()) {
        	g.setColor(Color.BLUE);
        	g.drawRect(this.x-3	, this.y-3, 6, 6);
        	g.setColor(Color.BLACK);
        }
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point p=(Point)obj;
			return this.x==p.x && this.y==p.y && this.getBorderColor().equals(p.getBorderColor());
		}
		return false;
	}
	
	public void moveBy(int byX,int byY) {
		this.x=this.x+byX;
		this.y=this.y+byY;
	}
	
	public boolean contains(Point p) {
		return this.distance(p.getX(), p.getY()) <= 3;
	}
	
	public double distance(int x2,int y2) {
		double dx=this.x-x2;
		double dy=this.y-y2;
		double d=Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x=x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "point:" + this.x + ":" + this.y + ":" + this.getBorderColor().getRGB() + ":" + isSelected();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
