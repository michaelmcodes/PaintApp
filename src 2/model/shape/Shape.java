package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public interface Shape {
    Point getStartPoint();
    Point getEndPoint();
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setSelected(boolean isSelected);
    boolean isSelected();
    void setShapeShadingType(ShapeShadingType shapeShadingType);
    ShapeShadingType getShapeShadingType();
    ShapeColor getBackgroundColor();
    ShapeColor getStrokeColor();
    void copy();
    void move(Point movedPoint);
    void draw(Graphics2D graphics2D);
    boolean select(Point selectedPoint);
}
