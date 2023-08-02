package commands;

import model.shape.AbstractShape;

import java.awt.*;

public class CmdMoveShape implements Command {

    private AbstractShape oldState;
    private AbstractShape originalState;
    private Point diffPoint;

    public CmdMoveShape(AbstractShape oldState, Point diffPoint) {
        this.oldState = oldState;
        this.diffPoint = diffPoint;
    }

    @Override
    public void execute() {
        originalState = oldState.clone();
        oldState.move(diffPoint);
    }

    @Override
    public void unexecute() {
        oldState.setStartPoint(originalState.getStartPoint());
        oldState.setEndPoint(originalState.getEndPoint());
    }
}
