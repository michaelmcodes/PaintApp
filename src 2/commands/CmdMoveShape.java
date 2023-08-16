package commands;

import model.shape.AbstractShape;
import model.shape.Shape;

import java.awt.*;

public class CmdMoveShape implements Command {

    private Shape oldState;
    private Shape originalState;
    private Point diffPoint;

    public CmdMoveShape(Shape oldState, Point diffPoint) {
        this.oldState = oldState;
        this.diffPoint = diffPoint;
    }

    @Override
    public void execute() {
        originalState = ((AbstractShape) oldState).clone();
        oldState.move(diffPoint);
    }

    @Override
    public void unexecute() {
        oldState.setStartPoint(originalState.getStartPoint());
        oldState.setEndPoint(originalState.getEndPoint());
    }
}
