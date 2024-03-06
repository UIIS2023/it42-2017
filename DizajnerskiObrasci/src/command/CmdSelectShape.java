package command;

import geometry.Shape;
import painting.DrawingModel;

public class CmdSelectShape implements Command {

	private DrawingModel model;
	private Shape shape;
	
	public CmdSelectShape(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		model.selectObject(shape);
	}

	@Override
	public void unexecute() {
		model.diselectObject(shape);
	}
	
	public String toString() {
		return "selected/" + shape;
	}
	
	public Shape getShape() {
		return this.shape;
	}

}
