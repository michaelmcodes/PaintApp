package commands;

import model.DrawingModel;
import model.shape.GroupShape;
import model.shape.Shape;

import java.util.ArrayList;

public class CmdGroupShape implements Command {

    private ArrayList<Shape> shapes;
    private DrawingModel model;
    private GroupShape groupShape = new GroupShape();

    public CmdGroupShape(ArrayList<Shape> shapes, DrawingModel model) {
        this.shapes = shapes;
        this.model = model;

        for (Shape shape : shapes) {
            groupShape.addChild(shape);
        }
    }

    @Override
    public void execute() {
        model.removeMultiple(shapes);
        model.add(groupShape);
    }

    @Override
    public void unexecute() {
        model.addMultiple(shapes);
        model.remove(groupShape);
    }
}