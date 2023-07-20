package model.action;

import model.shape.AbstractShape;

import java.awt.*;
import java.util.ArrayList;

public class MoveAction extends Action {

    private ArrayList<AbstractShape> movedShapes;
    private Point movedPoint;

    public MoveAction(ArrayList<AbstractShape> movedShapes, Point movedPoint) {
        super(ActionName.MOVE);
        this.movedShapes = movedShapes;
        this.movedPoint = movedPoint;
    }

    public void setMovedShapes(ArrayList<AbstractShape> movedShapes) {
        this.movedShapes = movedShapes;
    }

    public ArrayList<AbstractShape> getMovedShapes() {
        return movedShapes;
    }

    public void setMovedPoint(Point movedPoint) {
        this.movedPoint = movedPoint;
    }

    public Point getMovedPoint() {
        return movedPoint;
    }
}
