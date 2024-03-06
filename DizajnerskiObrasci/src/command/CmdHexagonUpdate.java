package command;

import geometry.HexagonAdapter;

public class CmdHexagonUpdate implements Command {

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	
	public CmdHexagonUpdate(HexagonAdapter oldState,HexagonAdapter newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.getHexagon().setX(this.newState.getHexagon().getX());
		oldState.getHexagon().setY(this.newState.getHexagon().getY());
		oldState.getHexagon().setR(newState.getHexagon().getR());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setColorFill(newState.getColorFill());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {
		oldState.getHexagon().setX(original.getHexagon().getX());
		oldState.getHexagon().setY(original.getHexagon().getY());
		oldState.getHexagon().setR(original.getHexagon().getR());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setColorFill(original.getColorFill());
		oldState.setSelected(original.isSelected());
	}

	public String toString() {
		return "modify,hexagon/" + this.original + "_" + this.newState;
	}

	public HexagonAdapter getOldState() {
		return oldState;
	}

	public void setOldState(HexagonAdapter oldState) {
		this.oldState = oldState;
	}
	
	
	
}
