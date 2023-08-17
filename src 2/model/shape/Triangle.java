package model.shape;

import model.ShapeColor;
import model.ShapeShadingType;
import util.Constants;
import util.Util;

import java.awt.*;

public class Triangle extends AbstractShape {

    public Triangle(Point startPoint, Point endPoint, ShapeColor backgroundColor, ShapeColor strokeColor, ShapeShadingType shapeShadingType) {
        super(startPoint, endPoint, backgroundColor, strokeColor, shapeShadingType);
    }

    private Polygon selectedTriangleStrokePolygon(Graphics2D g) {
        return makeTrianglePolygonWithPadding(getStartPoint().x, getStartPoint().y, getEndPoint().x, getEndPoint().y);
    }

    @Override
    public void draw(Graphics2D g) {
        Polygon polygon = makeTrianglePolygon(getStartPoint().x, getStartPoint().y, getEndPoint().x, getEndPoint().y);

        switch (getShapeShadingType()) {
            case FILLED_IN: {
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.fillPolygon(polygon);

                if (isSelected) {
                    drawSelectedTriangle(g);
                }
                break;
            }
            case OUTLINE: {
                g.setStroke(new BasicStroke(5));
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.drawPolygon(polygon);

                if (isSelected) {
                    drawSelectedTriangle(g);
                }
                break;
            }
            case OUTLINE_AND_FILLED_IN: {
                g.setColor(Util.getColorFromShapeColor(getBackgroundColor()));
                g.fillPolygon(polygon);

                g.setStroke(new BasicStroke(5));
                g.setColor(Util.getColorFromShapeColor(getStrokeColor()));
                g.drawPolygon(polygon);

                if (isSelected) {
                    drawSelectedTriangle(g);
                }
                break;
            }
        }
    }

    @Override
    public boolean select(Point selectedPoint) {
        Polygon polygon = makeTrianglePolygon(getStartPoint().x, getStartPoint().y, getEndPoint().x, getEndPoint().y);
        return polygon.contains(selectedPoint.x, selectedPoint.y);
    }

    private Polygon makeTrianglePolygon(int startX, int startY, int endX, int endY) {
        Point midPoint;
        if (startX > endX) {
            midPoint = new Point((endX + (Math.abs(startX - endX) / 2)), endY);
        } else {
            midPoint = new Point((endX - (Math.abs(startX - endX) / 2)), endY);
        }

        int[] xs = {startX, endX, midPoint.x};
        int[] ys = {startY, startY, midPoint.y};
        return new Polygon(xs, ys, 3);
    }

    private Polygon makeTrianglePolygonWithPadding(int startX, int startY, int endX, int endY) {
        int padding = Constants.SELECTED_SHAPE_STROKE_PADDING;
        Point midPoint;
        int[] xs;
        int[] ys;
        if (startX > endX) {
            midPoint = new Point((endX + (Math.abs(startX - endX) / 2)), 0);
            if (startY < endY) {
                midPoint.y = endY + padding;
                ys = new int[]{startY - padding, startY - padding, midPoint.y + padding};
            } else {
                midPoint.y = endY - padding;
                ys = new int[]{startY + padding, startY + padding, midPoint.y - padding};
            }
            xs = new int[]{startX + 2 * padding, endX - 2 * padding, midPoint.x};
        } else {
            midPoint = new Point((endX - (Math.abs(startX - endX) / 2)), 0);
            if (startY < endY) {
                midPoint.y = endY + padding;
                ys = new int[]{startY - padding, startY - padding, midPoint.y + padding};
            } else {
                midPoint.y = endY - padding;
                ys = new int[]{startY + padding, startY + padding, midPoint.y - padding};
            }
            xs = new int[]{startX - 2 * padding, endX + 2 * padding, midPoint.x};
        }

        return new Polygon(xs, ys, 3);
    }

    private void drawSelectedTriangle(Graphics2D g) {

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Constants.SELECTED_SHAPE_STROKE_COLOR);
        g.drawPolygon(selectedTriangleStrokePolygon(g));
    }
}
