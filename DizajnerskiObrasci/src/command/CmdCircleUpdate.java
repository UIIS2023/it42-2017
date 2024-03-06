package command;

import geometry.Circle;

public class CmdCircleUpdate implements Command {

	private Circle oldState;
	private Circle newState;
	private Circle original=new Circle();
	
	public CmdCircleUpdate(Circle oldState,Circle newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		oldState.setColorFill(newState.getColorFill());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setRadius(original.getRadius());
		oldState.setColorFill(original.getColorFill());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setSelected(original.isSelected());
		
	}
	
	public String toString() {
		return "modify,circle/" + this.original + "_" + this.newState;
	}

	public Circle getOldState() {
		return oldState;
	}

	public void setOldState(Circle oldState) {
		this.oldState = oldState;
	}

	
}
