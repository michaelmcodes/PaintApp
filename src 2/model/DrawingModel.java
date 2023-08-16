package model;

import model.shape.AbstractShape;
import model.shape.CompoundShape;
import model.shape.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawingModel {

    private final CompoundShape compoundShape = new CompoundShape();

    public ArrayList<Shape> getAll() {
        return compoundShape.getAll();
    }

    public Shape getLatest() {
        return compoundShape.getLatest();
    }

    public void draw(Graphics2D graphics2D) {
        compoundShape.draw(graphics2D);
    }

    public void add(Shape shape) {
        compoundShape.add(shape);
    }

    public void addMultiple(ArrayList<Shape> newShapes) {
        compoundShape.addMultiple(newShapes);
    }

    public void remove(Shape shape) {
        compoundShape.remove(shape);
    }

    public void removeMultiple(ArrayList<Shape> newShapes) {
        compoundShape.removeMultiple(newShapes);
    }

}
