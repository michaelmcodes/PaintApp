package commands;

import model.shape.AbstractShape;
import model.shape.Shape;

public class CmdUpdateShape implements Command {
	private Shape oldState;
	private Shape newState;
	private Shape originalState;

	public CmdUpdateShape(Shape oldState, Shape newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = ((AbstractShape) oldState).clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
	}
}