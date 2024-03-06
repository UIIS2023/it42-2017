package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Serializable {

	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint,Point endPoint) {
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line(Point startPoint,Point endPoint,Color c) {
		this(startPoint,endPoint);
		setBorderColor(c);
	}
	
	public Line(Point startPoint,Point endPoint,boolean selected) {
		this(startPoint,endPoint);
		setSelected(selected);
	}
	
	public Line(Point startPoint,Point endPoint,Color c,boolean selected) {
		this(startPoint,endPoint);
		setBorderColor(c);
		setSelected(selected);
	}
	
	public Line clone()
	{
		Line clone=new Line();
		clone.setBorderColor(this.getBorderColor());
		clone.setEndPoint(this.getEndPoint().clone());
		clone.setSelected(this.isSelected());
		clone.setStartPoint(this.getStartPoint().clone());
		return clone;
	}
	
	@Override
	public void draw(Graphics g) {
		if (this.getBorderColor()!=null) {
			g.setColor(this.getBorderColor());
		} else {
            g.setColor(Color.BLACK);
		}
		g.drawLine(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());
		g.setColor(Color.BLACK);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getStartPoint().getX() - 3, getStartPoint().getY() - 3, 6, 6);
			g.drawRect(getEndPoint().getX() - 3, getEndPoint().getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
			g.setColor(Color.BLACK);
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line l=(Line)obj;
			return this.startPoint.equals(l.getStartPoint()) && this.endPoint.equals(l.getEndPoint()) && this.getBorderColor().equals(l.getBorderColor());
		}
		return false;
	}
		
	
	public Point middleOfLine() {
		int middleByX=(this.getStartPoint().getX() + this.getEndPoint().getX())/2;
		int middleByY=(this.getStartPoint().getY() + this.getEndPoint().getY())/2;
		Point p=new Point(middleByX,middleByY);
		return p;
	}
	
	
	public boolean contains(Point p) {
		if ((startPoint.distance(p.getX(), p.getY()) + endPoint.distance(p.getX(), p.getY())) - length() <= 0.05) {
			return true;
		} else {
			return false;

		}
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "line:" + this.startPoint + ":" + this.endPoint + ":" + this.getBorderColor().getRGB() + ":" + isSelected();
	}
	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
