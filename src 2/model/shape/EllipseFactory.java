package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;

public class EllipseFactory extends AbstractShapeFactory{
    @Override
    public AbstractShape create(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType) {
        return new Ellipse(startPoint, endPoint, backgroundColor, strokeColor, shapeShadingType);
    }
}
