package commands;

import model.shape.AbstractShape;

/**
 * Class that represent command for update existing rectangle from the draw.
 */
public class CmdUpdateShape implements Command {
	private AbstractShape oldState;
	private AbstractShape newState;
	private AbstractShape originalState;

	public CmdUpdateShape(AbstractShape oldState, AbstractShape newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
	}
}