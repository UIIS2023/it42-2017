package geometry;

import java.awt.Color;
import java.io.Serializable;

public abstract class ArealShape extends Shape implements Serializable {

	private Color colorFill;
	
	public ArealShape() {
		
	}
	
	public ArealShape(Color colorFill) {
		this.colorFill=colorFill;
	}

	public Color getColorFill() {
		return colorFill;
	}

	public void setColorFill(Color colorFill) {
		this.colorFill = colorFill;
	}
	
}
