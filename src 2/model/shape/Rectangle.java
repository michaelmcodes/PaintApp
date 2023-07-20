package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;
import util.Util;

import java.awt.*;

public class Rectangle extends GeometricShape {

    public Rectangle(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType, boolean isSelected) {
        super(startPoint, endPoint, backgroundColor, strokeColor, shapeShadingType, isSelected);
    }

    @Override
    public void draw(Graphics2D g) {

        int px = Math.min(getStartPoint().x, getEndPoint().x);
        int py = Math.min(getStartPoint().y, getEndPoint().y);
        int pw = Math.abs(getStartPoint().x - getEndPoint().x);
        int ph = Math.abs(getStartPoint().y - getEndPoint().y);


        switch (getShapeShadingType()) {
            case FILLED_IN: {
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.fillRect(px, py, pw, ph);

                break;
            }
            case OUTLINE: {
                g.setStroke(new BasicStroke(5));
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.drawRect(px, py, pw, ph);

                break;
            }
            case OUTLINE_AND_FILLED_IN: {
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.fillRect(px, py, pw, ph);

                g.setStroke(new BasicStroke(5));
                g.setColor(Util.getColorFromShapeColor(getStrokeColor()));
                g.drawRect(px, py, pw, ph);

                break;
            }
        }
    }

    @Override
    public boolean select(Point selectedPoint) {

        int x1 = this.getStartPoint().x, x2 = this.getEndPoint().x;
        int y1 = this.getStartPoint().y, y2 = this.getEndPoint().y;

        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        return r.contains(selectedPoint.x, selectedPoint.y);
    }
}
