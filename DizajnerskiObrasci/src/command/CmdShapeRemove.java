package command;

import java.util.List;

import geometry.Shape;
import painting.DrawingModel;

public class CmdShapeRemove implements Command {

	private List<Shape> deletedShapes;
	private DrawingModel model;
	private boolean fromLog;
	
	public CmdShapeRemove(DrawingModel model,List<Shape> deletedShapes,boolean fromLog) {
		this.model=model;
		this.deletedShapes=deletedShapes;
		this.fromLog=fromLog;
	}

	@Override
	public void execute() {
		if(this.fromLog) {
			for(Shape deleted:this.deletedShapes) {
				for(Shape shape:this.model.getShapes()) {
					if(deleted.equals(shape)) {
						deleted=shape;
					}
				}
			}
		}
		for(Shape shape:this.deletedShapes) {
			this.model.getShapes().remove(shape);
		}
		
	}

	@Override
	public void unexecute() {
		for(Shape shape:this.deletedShapes) {
			this.model.getShapes().add(shape);
		}
		
	}
	
	@Override
	public String toString() {
		String shapes = "";
		for (int i = 0; i < this.deletedShapes.size(); i++) {
			Shape shape = this.deletedShapes.get(i);
			if(i == this.deletedShapes.size() - 1) {
				shapes += shape.toString();
			} else {
				shapes += shape.toString() + "_";
			}
		}
		return "delete/" + shapes;
	}

}
