package model.shape;

import model.ShapeColor;
import util.Constants;
import util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GroupShape extends AbstractShape {

    private final ArrayList<Shape> children = new ArrayList<>();

    private boolean isSelected = false;

    public void addChildren(ArrayList<Shape> groupedShapes) {
        if (!children.containsAll(groupedShapes))
            children.addAll(groupedShapes);
    }

    public void addChild(Shape shape) {
        if (!children.contains(shape)) {
            shape.setSelected(false);
            children.add(shape);
        }

    }

    public ArrayList<Shape> getChildren() {
        return children;
    }

    @Override
    public void setStartPoint(Point startPoint) {
        for (Shape shape : children) {
            shape.setStartPoint(startPoint);
        }
    }

    @Override
    public void setEndPoint(Point endPoint) {
        for (Shape shape : children) {
            shape.setEndPoint(endPoint);
        }
    }

    @Override
    public void copy() {
        for (Shape child : children) {
            child.copy();
        }

    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public Point getStartPoint() {
        java.awt.Shape boundingRect = createBoundingBox();
        return new Point(boundingRect.getBounds().x, boundingRect.getBounds().y);
    }

    @Override
    public Point getEndPoint() {
        java.awt.Shape boundingRect = createBoundingBox();
        return new Point(boundingRect.getBounds().x + boundingRect.getBounds().width, boundingRect.getBounds().y + boundingRect.getBounds().height);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void move(Point movedPoint) {
        for (Shape shape : children) {
            shape.move(movedPoint);
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Shape shape : children) {
            shape.draw(graphics2D);
        }
        if (isSelected)
            drawBoundingBox(graphics2D, createBoundingBox());
    }

    @Override
    public AbstractShape clone() {
        ArrayList<Shape> newChildren = new ArrayList<>();
        for (Shape shape : children) {
            newChildren.add(((AbstractShape) shape).clone());
        }
        GroupShape groupShape = new GroupShape();
        groupShape.addChildren(newChildren);
        return groupShape;
    }

    private void drawBoundingBox(Graphics2D g, java.awt.Shape rectangle) {
        int padding = Constants.SELECTED_SHAPE_STROKE_PADDING;

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Constants.SELECTED_SHAPE_STROKE_COLOR);
        g.drawRect(rectangle.getBounds().x - padding, rectangle.getBounds().y - padding, rectangle.getBounds().width + 2 * padding, rectangle.getBounds().height + 2 * padding);

    }

    @Override
    public boolean select(Point selectedPoint) {
        java.awt.Shape r = createBoundingBox();
        if (r != null) {
            return r.contains(selectedPoint);
        }
        return false;
    }

    public java.awt.Shape createBoundingBox() {
        ArrayList<Integer> xCoordinates = new ArrayList<>();
        ArrayList<Integer> yCoordinates = new ArrayList<>();

        for (Shape shape : children) {
            xCoordinates.add(shape.getStartPoint().x);
            xCoordinates.add(shape.getEndPoint().x);

            yCoordinates.add(shape.getStartPoint().y);
            yCoordinates.add(shape.getEndPoint().y);
        }
        int xMin = Collections.min(xCoordinates);
        int yMin = Collections.min(yCoordinates);
        int xMax = Collections.max(xCoordinates);
        int yMax = Collections.max(yCoordinates);

        Point startPoint = new Point(xMin, yMin);
        Point endPoint = new Point(xMax, yMax);

        int x1 = startPoint.x, x2 = endPoint.x;
        int y1 = startPoint.y, y2 = endPoint.y;

        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        return r;
    }
}
