package model.action;

import model.shape.GeometricShape;

import java.awt.*;
import java.util.ArrayList;

public class MoveAction extends Action {

    private ArrayList<GeometricShape> movedShapes;
    private Point movedPoint;

    public MoveAction(ArrayList<GeometricShape> movedShapes, Point movedPoint) {
        super(ActionName.MOVE);
        this.movedShapes = movedShapes;
        this.movedPoint = movedPoint;
    }

    public void setMovedShapes(ArrayList<GeometricShape> movedShapes) {
        this.movedShapes = movedShapes;
    }

    public ArrayList<GeometricShape> getMovedShapes() {
        return movedShapes;
    }

    public void setMovedPoint(Point movedPoint) {
        this.movedPoint = movedPoint;
    }

    public Point getMovedPoint() {
        return movedPoint;
    }
}
