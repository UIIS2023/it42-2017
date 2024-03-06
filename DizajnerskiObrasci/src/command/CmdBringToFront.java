package command;

import geometry.Shape;
import painting.DrawingModel;

public class CmdBringToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index;
	private int size;
	
	
	public CmdBringToFront(DrawingModel model,Shape shape,int index,int size) {
		this.model=model;
		this.shape=shape;
		this.index=index;
		this.size=size;
	}
	
	@Override
	public void execute() {
		for(int j=index+1;j<model.getShapes().size();j++) {
			Shape start=model.getShapes().get(j);
			model.change(j-1, start);
		}
		model.change(model.getShapes().size()-1, shape);
	}

	@Override
	public void unexecute() {
		int size=model.getShapes().size()-1;
		Shape current=model.getShapes().get(size);
		for(int j=size-1;j>=0;j--) {
			if(j==index) {
				Shape next=model.getShapes().get(j);
				model.change(j+1, next);
				model.change(index, current);
			} else {
				Shape next=model.getShapes().get(j);
				model.change(j+1, next);
			}
		}
	}
	
	public String toString() {
		return "bringtofront/" + shape + ":" + index + ":" + model.getShapes().size();
	}
}
