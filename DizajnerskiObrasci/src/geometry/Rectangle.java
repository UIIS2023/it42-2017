package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends ArealShape implements Serializable{

	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height) {
		this.upperLeftPoint=upperLeftPoint;
		setWidth(width);
		setHeight(height);
	}
	public Rectangle(Point upperLeftPoint,int width,int height,boolean selected) {
		this(upperLeftPoint,width,height);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height,Color colorBorder,Color colorFill) {
		this(upperLeftPoint,width,height);
		this.setBorderColor(colorBorder);
		this.setColorFill(colorFill);
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height,Color colorBorder,Color colorFill,boolean selected) {
		this(upperLeftPoint,width,height);
		this.setBorderColor(colorBorder);
		this.setColorFill(colorFill);
		this.setSelected(selected);
	}
	
	public Rectangle clone() {
		Rectangle clone = new Rectangle();
		clone.setUpperLeftPoint(upperLeftPoint.clone());
		clone.setBorderColor(this.getBorderColor());
		clone.setColorFill(this.getColorFill());
		clone.setHeight(this.getHeight());
		clone.setSelected(this.isSelected());
		clone.setWidth(this.getWidth());
		return clone;
	}
	
	@Override
	public void draw(Graphics g) {
		if(getColorFill()!=null) {
			g.setColor(getColorFill());
			g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
		}
		if(getBorderColor()!=null) {
			g.setColor(getBorderColor());
		}
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
		g.setColor(Color.BLACK);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() -3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3,this.getUpperLeftPoint().getY() -3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() -3 + getHeight(), 6, 6);
			g.setColor(Color.BLACK);
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
		Rectangle r=(Rectangle)obj;
		return this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.width==r.width && this.height==r.height && this.getBorderColor().equals(r.getBorderColor()) && this.getColorFill().equals(r.getColorFill());
		}
		return false;
	}
	
	
	public boolean contains(Point p) {
		if(this.getUpperLeftPoint().getX() <= p.getX()
				&& p.getX() <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
		    return false;
		}
	}
	
	public int area() {
		return width*height;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return "rectangle:" + this.getUpperLeftPoint() + ":" + this.getWidth() + ":" + this.getHeight() + ":" + this.getBorderColor().getRGB() + ":" + this.getColorFill().getRGB() + ":" + isSelected(); 
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
