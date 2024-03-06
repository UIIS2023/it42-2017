package command;

import geometry.Shape;
import painting.DrawingModel;

public class CmdDiselectShape implements Command {

	private DrawingModel model;
	private Shape shape;
	
	public CmdDiselectShape(DrawingModel model) {
		this.model=model;
	}
	
	public CmdDiselectShape(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		model.diselectObject(shape);
	}

	@Override
	public void unexecute() {
		model.selectObject(shape);
	}
	
	public String toString() {
		return "diselected/" + shape;
	}

}
