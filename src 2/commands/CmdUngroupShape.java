package commands;

import model.DrawingModel;
import model.shape.GroupShape;
import model.shape.Shape;

import java.util.ArrayList;

public class CmdUngroupShape implements Command {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private DrawingModel model;

    public CmdUngroupShape(ArrayList<Shape> selectedShapes, DrawingModel model) {
        this.model = model;

        for (Shape shape : selectedShapes) {
            if (shape instanceof GroupShape) {
                shapes.add(shape);
            }
        }
    }

    @Override
    public void execute() {
        for (Shape groupShape : shapes) {
            model.addMultiple(((GroupShape) groupShape).getChildren());
        }
        model.removeMultiple(shapes);
    }

    @Override
    public void unexecute() {
        for (Shape groupShape : shapes) {
            model.removeMultiple(((GroupShape) groupShape).getChildren());
        }
        model.addMultiple(shapes);
    }
}