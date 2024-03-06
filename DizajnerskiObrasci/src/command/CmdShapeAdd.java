package command;

import geometry.Shape;
import painting.DrawingModel;

public class CmdShapeAdd implements Command {

	private DrawingModel model;
	private Shape shape;
	
	public CmdShapeAdd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		
		model.add(shape);
	}

	@Override
	public void unexecute() {
		
		model.remove(shape);
	}
	
	public String toString() {
		return "add," + shape;
	}

}
