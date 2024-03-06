package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends ArealShape implements Serializable {

	private Point center;
	private int radius;
	
	public Circle() {
		
	}
	
	public Circle(Point center,int radius){
		this.center=center;
		setRadius(radius);
	}
	public Circle(Point center,int radius,boolean selected){
		this(center,radius);
		setSelected(selected);
	}
	
	public Circle(Point center,int radius,Color colorBorder,Color colorFill){
		this(center,radius);
		this.setBorderColor(colorBorder);
		this.setColorFill(colorFill);
	}
	
	public Circle(Point center,int radius,Color colorBorder,Color colorFill,boolean selected){
		this(center,radius);
		this.setBorderColor(colorBorder);
		this.setColorFill(colorFill);
		setSelected(selected);
	}
	
	public Circle clone() {
		Circle clone=new Circle();
		clone.setCenter(this.getCenter().clone());
		clone.setRadius(this.getRadius());
		clone.setBorderColor(this.getBorderColor());
		clone.setColorFill(this.getColorFill());
		clone.setSelected(this.isSelected());
		return clone;
	}
	
	@Override
	public void draw(Graphics g) {
		if(getColorFill()!=null) {
			g.setColor(getColorFill());
			g.fillOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius()	,this.getRadius()*2, this.getRadius()*2);
		}
		if(getBorderColor()!=null) {
			g.setColor(getBorderColor());
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		g.setColor(Color.BLACK);
		
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 + getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 - getRadius(), getCenter().getY() - 3 , 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getRadius(), 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getRadius(), 6, 6);
			g.setColor(Color.BLACK);
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle c=(Circle)obj;
			return this.center.equals(c.getCenter()) && this.radius==c.radius && this.getBorderColor().equals(c.getBorderColor()) && this.getColorFill().equals(c.getColorFill());
		}
		return false;
	}
	
	public boolean contains(Point p) {
		return center.distance(p.getX(),p.getY()) <= radius;
	}
	
	
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String toString() {
		return "circle:" + this.center + ":" + this.radius + ":" + this.getBorderColor().getRGB() + ":" + this.getColorFill().getRGB() + ":" + isSelected();
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
