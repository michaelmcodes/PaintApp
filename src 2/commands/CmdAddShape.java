package commands;

import model.DrawingModel;
import model.shape.AbstractShape;

public class CmdAddShape implements Command {

    private DrawingModel model;
    private AbstractShape shape;

    public CmdAddShape(DrawingModel model, AbstractShape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.add(shape);
    }

    @Override
    public void unexecute() {
        model.remove(shape);
    }
}
