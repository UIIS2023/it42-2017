package command;

import geometry.Shape;
import painting.DrawingModel;

public class CmdBringToBack implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index;
	private int size;
	
	public CmdBringToBack(DrawingModel model,Shape shape,int index,int size) {
		this.model=model;
		this.shape=shape;
		this.index=index;
		this.size=size;
	}
	
	@Override
	public void execute() {
		for(int j=index-1;j>=0;j--) {
			Shape start=model.getShapes().get(j);
			model.change(j+1, start);
		}
		model.change(0, shape);
		
	}

	@Override
	public void unexecute() {
		Shape current=model.getShapes().get(0);
		for(int j=1;j<model.getShapes().size();j++) {
			if(j==index) {
				Shape next=model.getShapes().get(j);
				model.change(j-1, next);
				model.change(index, current);
				return;
			} else {
				Shape next=model.getShapes().get(j);
				model.change(j-1, next);
			}
		}
		
	}
	
	public String toString() {
		return "bringtoback/" + shape + ":" + index + ":" + model.getShapes().size();
	}

}
