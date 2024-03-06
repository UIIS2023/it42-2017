package command;

import geometry.Line;

public class CmdLineUpdate implements Command {

	private Line oldState;
	private Line newState;
	private Line original=new Line();
	
	public CmdLineUpdate(Line oldState,Line newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.getStartPoint().setX(newState.getStartPoint().getX());
		oldState.getStartPoint().setY(newState.getStartPoint().getY());
		oldState.getEndPoint().setX(newState.getEndPoint().getX());
		oldState.getEndPoint().setY(newState.getEndPoint().getY());
		oldState.getStartPoint().setBorderColor(newState.getStartPoint().getBorderColor());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {
		oldState.getStartPoint().setX(original.getStartPoint().getX());
		oldState.getStartPoint().setY(original.getStartPoint().getY());
		oldState.getEndPoint().setX(original.getEndPoint().getX());
		oldState.getEndPoint().setY(original.getEndPoint().getY());
		oldState.getStartPoint().setBorderColor(original.getStartPoint().getBorderColor());
		oldState.setSelected(original.isSelected());
		
	}
	
	public String toString() {
		return "modify,line/" + this.original + "_" + this.newState;
	}

	public Line getOldState() {
		return oldState;
	}

	public void setOldState(Line oldState) {
		this.oldState = oldState;
	}
	
	

}
