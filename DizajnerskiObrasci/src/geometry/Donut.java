package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import painting.PnlDrawing;

public class Donut extends Circle implements Serializable {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center,int radius,int innerRadius){
		super(center,radius);
	    setInnerRadius(innerRadius);	
	}

	public Donut(Point center,int radius,int innerRadius,boolean selected) {
		this(center,radius,innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center,int radius,int innerRadius,Color colorLessBorder) {
		this(center,radius,innerRadius);
		setColorLessBorder(colorLessBorder);
	}
	
	public Donut(Point center,int radius,int innerRadius,Color colorLessBorder,boolean selected) {
		this(center,radius,innerRadius);
		setColorLessBorder(colorLessBorder);
		setSelected(selected);
	}
	
	public Donut(Point center,int radius,int innerRadius,Color borderColor,Color colorFill,Color colorLessBorder) {
		this(center,radius,innerRadius);
		setBorderColor(borderColor);
		setColorFill(colorFill);
		setColorLessBorder(colorLessBorder);
	}
	
	public Donut(Point center,int radius,int innerRadius,Color borderColor,Color colorFill,Color colorLessBorder,boolean selected) {
		this(center,radius,innerRadius);
		setBorderColor(borderColor);
		setColorFill(colorFill);
		setColorLessBorder(colorLessBorder);
		setSelected(selected);
	}
	
	public Donut clone() {
		Donut clone=new Donut();
		clone.setCenter(this.getCenter().clone());
		clone.setInnerRadius(this.getInnerRadius());
		clone.setRadius(this.getRadius());
		clone.setBorderColor(this.getBorderColor());
		clone.setColorFill(this.getColorFill());
		clone.setColorLessBorder(this.getColorLessBorder());
		clone.setSelected(this.isSelected());
		return clone;
	}
	
	public void draw(Graphics g) {
		
		Ellipse2D innerCircle=new Ellipse2D.Double((this.getCenter().getX()-this.getInnerRadius()),(this.getCenter().getY()-this.getInnerRadius()),this.getInnerRadius()*2,this.innerRadius*2);
		Area inner=new Area(innerCircle);
		Ellipse2D outerCircle=new Ellipse2D.Double(getCenter().getX()-getRadius(),getCenter().getY()-getRadius(),this.getRadius()*2,this.getRadius()*2);
		Area donut=new Area(outerCircle);
		donut.subtract(inner);
		Graphics2D gr=(Graphics2D)g;
		g.setColor(getBorderColor());
		gr.draw(donut);
		g.setColor(getColorFill());
		gr.fill(donut);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 + getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 - getRadius(), getCenter().getY() - 3 , 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getRadius(), 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getRadius(), 6, 6);
			
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 + getInnerRadius(), getCenter().getY() - 3 , 6, 6);
			g.drawRect(getCenter().getX() - 3 - getInnerRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getInnerRadius(), 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() -3 + getInnerRadius(), 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut d=(Donut)obj;
			return super.equals(d) && this.innerRadius==d.innerRadius && this.getColorLessBorder().equals(d.getColorLessBorder());
		}
		return false;
	}
	
	public boolean contains(Point p) {
		double dFromCenter=this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius && super.contains(p);
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return "donut:" + super.toString() + ":" + this.getInnerRadius() + ":" + this.getColorLessBorder().getRGB() + ":" + isSelected();
	
	}
	
}
