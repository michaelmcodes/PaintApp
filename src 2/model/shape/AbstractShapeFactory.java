package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public abstract class AbstractShapeFactory {
    public abstract AbstractShape create(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType, boolean isSelected);
}
