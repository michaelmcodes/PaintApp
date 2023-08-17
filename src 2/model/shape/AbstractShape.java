package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;
import util.Constants;
import util.Util;

import java.awt.*;

public abstract class AbstractShape implements Shape, Cloneable {

    private Point startPoint;
    private Point endPoint;
    private ShapeColor backgroundColor = Util.getDefaultState().getShapePrimaryColor();
    private ShapeColor strokeColor = Util.getDefaultState().getShapeSecondaryColor();
    private ShapeShadingType shapeShadingType;
    public boolean isSelected;

    public AbstractShape(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
    }

    public AbstractShape() {}

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean isSelected() { return isSelected; }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    @Override
    public ShapeColor getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public ShapeColor getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void move(Point movedPoint) {
        Point startingPoint = getStartPoint();
        Point endingPoint = getEndPoint();
        Point newStartPoint = new Point(startingPoint.x + movedPoint.x, startingPoint.y + movedPoint.y);
        Point newEndPoint = new Point(endingPoint.x + movedPoint.x, endingPoint.y + movedPoint.y);
        setStartPoint(newStartPoint);
        setEndPoint(newEndPoint);
    }

    @Override
    public abstract void draw(Graphics2D graphics2D);

    @Override
    public abstract boolean select(Point selectedPoint);

    @Override
    public AbstractShape clone() {
        try {
            return (AbstractShape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
