package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends ArealShape implements Cloneable,Serializable {

	private Hexagon hexagon;
	
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(int x,int y,int r) {
		hexagon=new Hexagon(x, y, r);
	}
	
	public HexagonAdapter(int x, int y, int r, Color b, Color f, boolean selected) {
		this.hexagon = new Hexagon(x, y, r);
		setColorFill(f);
		setBorderColor(b);
		setSelected(selected);
	}
	
	public HexagonAdapter(Point center, int r, Color b, Color f) {
		this.hexagon = new Hexagon(center.getX(),center.getY(), r);
		setBorderColor(b);
		setColorFill(f);
	}
	
	public HexagonAdapter(Point center, int r, Color b, Color f,boolean selected) {
		this.hexagon = new Hexagon(center.getX(),center.getY(), r);
		setBorderColor(b);
		setColorFill(f);
		setSelected(selected);
	}
	
	public HexagonAdapter clone() {
		HexagonAdapter clone=new HexagonAdapter(this.getHexagon().getX(),this.getHexagon().getY(),this.getHexagon().getR(),this.getColorFill(),this.getBorderColor(),this.isSelected());
		clone.getHexagon().setSelected(this.getHexagon().isSelected());
		return clone;
	}

	@Override
	public boolean contains(Point p) {
		return hexagon.doesContain(p.getX(),p.getY());
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
		this.hexagon.setBorderColor(getBorderColor());
		this.hexagon.setAreaColor(getColorFill());
		
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter h=(HexagonAdapter)obj;
			return this.getHexagon().getX()==h.getHexagon().getX() && this.getHexagon().getY()==h.getHexagon().getY() && this.getHexagon().getR()==h.getHexagon().getR() && this.getBorderColor().equals(h.getBorderColor()) && this.getColorFill().equals(h.getColorFill());
		}
		return false;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter) {
			return (int) (6 * ((this.hexagon.getR() * Math.sqrt(3)) / 4)
					      -6 * ((((HexagonAdapter) o).hexagon.getR() * Math.sqrt(3)) / 4));
		}
		return 0;
	}
	
	public String toString() {
		return "hexagon:" + this.getHexagon().getX() + ":" + this.getHexagon().getY() + ":" + this.getHexagon().getR() + ":" + this.getBorderColor().getRGB() + ":" + this.getColorFill().getRGB() + ":" + isSelected();
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public void setSelected(boolean selected) {
		getHexagon().setSelected(selected);
	}
	
	public boolean isSelected() {
		return getHexagon().isSelected();
	}
}
