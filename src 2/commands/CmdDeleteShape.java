package commands;

import model.DrawingModel;
import model.shape.AbstractShape;

import java.util.ArrayList;

public class CmdDeleteShape implements Command {

    private DrawingModel model;
    private ArrayList<AbstractShape> shapes;

    public CmdDeleteShape(DrawingModel model,ArrayList<AbstractShape> shapes) {
        this.model = model;
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        model.removeMultiple(shapes);
    }

    @Override
    public void unexecute() {
        model.addMultiple(shapes);
    }
}
