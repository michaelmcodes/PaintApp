package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public class RectangleFactory extends AbstractShapeFactory{
    @Override
    public AbstractShape create(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType, boolean isSelected) {
        return new Rectangle(startPoint, endPoint, backgroundColor, strokeColor, shapeShadingType, isSelected);
    }
}
