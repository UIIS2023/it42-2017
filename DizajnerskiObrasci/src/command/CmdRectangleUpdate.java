package command;

import java.awt.Color;

import geometry.Rectangle;

public class CmdRectangleUpdate implements Command {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original=new Rectangle();
	
	public CmdRectangleUpdate(Rectangle oldState,Rectangle newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		oldState.setWidth(newState.getWidth());
		oldState.setHeight(newState.getHeight());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setColorFill(newState.getColorFill());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldState.setWidth(original.getWidth());
		oldState.setHeight(original.getHeight());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setColorFill(original.getColorFill());
		oldState.setSelected(original.isSelected());
	}
	
	public String toString() {
		return "modify,rectangle/" + this.original + "_" + this.newState;
	}

	public Rectangle getOldState() {
		return oldState;
	}

	public void setOldState(Rectangle oldState) {
		this.oldState = oldState;
	}

	
}
