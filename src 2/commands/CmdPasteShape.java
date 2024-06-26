package commands;

import model.DrawingModel;
import model.shape.AbstractShape;
import model.shape.Shape;

import java.util.ArrayList;

public class CmdPasteShape implements Command {

    private DrawingModel model;
    private ArrayList<Shape> shapes;

    public CmdPasteShape(DrawingModel model, ArrayList<Shape> shapes) {
        this.model = model;
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        model.addMultiple(shapes);
    }

    @Override
    public void unexecute() {
        model.removeMultiple(shapes);
    }
}
