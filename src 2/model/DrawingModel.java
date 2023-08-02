package model;

import model.shape.AbstractShape;

import java.util.ArrayList;

public class DrawingModel {

    private ArrayList<AbstractShape> shapes = new ArrayList<>();

    public ArrayList<AbstractShape> getAll() {
        return shapes;
    }

    public AbstractShape getLatest() {
        return shapes.get(shapes.size() - 1);
    }

    public void add(AbstractShape shape) {
        shapes.add(shape);
    }

    public void addMultiple(ArrayList<AbstractShape> newShapes) {
        shapes.addAll(newShapes);
    }

    public void remove(AbstractShape shape) {
        shapes.remove(shape);
    }

    public void removeMultiple(ArrayList<AbstractShape> newShapes) {
        this.shapes.removeAll(newShapes);
    }

}
