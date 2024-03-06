package painting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

import geometry.Shape;

public class DrawingModel {
	
	private List<Shape> shapes=new ArrayList<Shape>();
	
	public void selectObject(Shape shape) {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(true);
			}
		}
	}
	
	public void diselectObject(Shape shape) {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(false);
			}
		}
	}
	
	public static List<Shape> deselectAll(List<Shape> shapes) {
		Iterator<Shape> iterator = shapes.iterator();

		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			shape.setSelected(false);
		}
		
		return shapes;
	}
	
	public int numberSelectedObject() {
		int counter=0;
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected()) {
				counter++;
			}
		}
		return counter;
	}
	
	public int selectedLastShape() {
		for(int i=shapes.size()-1;i>=0;i--) {
			if(shapes.get(i).isSelected() && i==0) {
				return 1;
			} 
		}
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected() && i==shapes.size()-1) {
				return 2;
			}
		}
		return 0;
	}
	
	public int listSize() {
		return shapes.size();
	}
	
	public void change(int i,Shape shape) {
		shapes.remove(i);
		shapes.add(i,shape);
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public void add(Shape shape) {
		this.getShapes().add(shape);
	}
	
	public void remove(Shape shape) {
		this.getShapes().remove(shape);
	}
	
	public Shape get(int i) {
		return this.getShapes().get(i);
	}


}
