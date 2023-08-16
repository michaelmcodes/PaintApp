package model.shape;

import java.awt.*;
import java.util.ArrayList;

public class CompoundShape extends AbstractShape {

    private final ArrayList<Shape> shapes = new ArrayList<>();

    public ArrayList<Shape> getAll() {
        return shapes;
    }

    public Shape getLatest() {
        return shapes.get(shapes.size() - 1);
    }
    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void addMultiple(ArrayList<Shape> newShapes) {
        shapes.addAll(newShapes);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void removeMultiple(ArrayList<Shape> newShapes) {
        this.shapes.removeAll(newShapes);
    }

    @Override
    public void setSelected(boolean selected) {
        for (Shape shape : shapes) {
            shape.setSelected(selected);
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Shape shape : shapes) {
            shape.draw(graphics2D);
        }

    }

    @Override
    public boolean select(Point selectedPoint) {
        for (Shape shape : shapes) {
            if (shape.select(selectedPoint)) {
                return true;
            }
        }
        return false;
    }
}
