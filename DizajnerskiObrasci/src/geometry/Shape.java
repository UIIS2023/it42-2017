package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.GrayFilter;

public abstract class Shape implements Moveable,Comparable<Object>,Serializable,Cloneable {

	private boolean selected;
	private Color borderColor;
	private Color colorLessBorder;
	
	public Shape() {
		
	}
	public Shape(boolean selected) {
		this.selected=selected;
	}
	
	public abstract boolean contains(Point p);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getColorLessBorder() {
		return colorLessBorder;
	}
	public void setColorLessBorder(Color colorLessBorder) {
		this.colorLessBorder = colorLessBorder;
	}
	
	
}
