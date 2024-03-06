package command;

import geometry.Point;

public class CmdPointUpdate implements Command {

	private Point oldState;
	private Point newState;
	private Point original=new Point();
	
	public CmdPointUpdate(Point oldState,Point newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {		
		original=oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setSelected(newState.isSelected());
		
	}
	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setSelected(original.isSelected());
		
	}
	
	public String toString() {
		
		return "modify,point/" + this.original + "_" + this.newState;
	}

	public Point getOldState() {
		return oldState;
	}

	public void setOldState(Point oldState) {
		this.oldState = oldState;
	}
	
	
	
}
