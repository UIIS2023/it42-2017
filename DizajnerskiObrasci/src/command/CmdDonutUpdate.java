package command;

import geometry.Donut;

public class CmdDonutUpdate implements Command {

	private Donut oldState;
	private Donut newState;
	private Donut original=new Donut();
	
	public CmdDonutUpdate(Donut oldState,Donut newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setRadius(newState.getRadius());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setColorFill(newState.getColorFill());
		oldState.setColorLessBorder(newState.getColorLessBorder());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setRadius(original.getRadius());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setColorFill(original.getColorFill());
		oldState.setColorLessBorder(original.getColorLessBorder());
		oldState.setSelected(original.isSelected());
		
	}
	
	public String toString() {
		return "modify,donut/" + this.original + "_" + this.newState;
	}

	public Donut getOldState() {
		return oldState;
	}

	public void setOldState(Donut oldState) {
		this.oldState = oldState;
	}

	
}
