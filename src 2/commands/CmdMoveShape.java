package commands;

import model.shape.AbstractShape;
import model.shape.GroupShape;
import model.shape.Shape;

import java.awt.*;

public class CmdMoveShape implements Command {

    private Shape oldState;
    private Point diffPoint;

    public CmdMoveShape(Shape oldState, Point diffPoint) {
        this.oldState = oldState;
        this.diffPoint = diffPoint;
    }

    @Override
    public void execute() {
        oldState.move(diffPoint);
    }

    @Override
    public void unexecute() {
        oldState.move(new Point(-diffPoint.x, -diffPoint.y));
    }
}
