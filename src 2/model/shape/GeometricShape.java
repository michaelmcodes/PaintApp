package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public abstract class GeometricShape {

    private Point startPoint;
    private Point endPoint;
    private ShapeColor backgroundColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    public boolean isSelected;

    public GeometricShape(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType, boolean isSelected) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
        this.isSelected = isSelected;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    public void setBackgroundColor(ShapeColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeColor(ShapeColor strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    public ShapeColor getBackgroundColor() {
        return backgroundColor;
    }

    public ShapeColor getStrokeColor() {
        return strokeColor;
    }

    public abstract void draw(Graphics2D graphics2D);
    public abstract boolean select(Point selectedPoint);
}
