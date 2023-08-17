package model;

import model.shape.*;

import java.awt.*;

public class ShapeCreator {
    public AbstractShape createShape(ShapeType shapeType, Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType) {
        AbstractShapeFactory abstractShapeFactory = null;
        switch (shapeType) {
            case TRIANGLE: {
                abstractShapeFactory = new TriangleFactory();
                break;
            }
            case ELLIPSE: {
                abstractShapeFactory = new EllipseFactory();
                break;
            }
            case RECTANGLE: {
                abstractShapeFactory = new RectangleFactory();
                break;
            }
        }
        return abstractShapeFactory.create(startPoint, endPoint, backgroundColor, strokeColor, shapeShadingType);

    }
}
