package commands;

import model.DrawingModel;
import model.shape.AbstractShape;
import model.shape.Shape;

public class CmdAddShape implements Command {

    private DrawingModel model;
    private Shape shape;

    public CmdAddShape(DrawingModel model, Shape shape) {
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
