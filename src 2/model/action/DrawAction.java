package model.action;

import model.shape.GeometricShape;

public class DrawAction extends Action {

    private GeometricShape drawnShape;

    public DrawAction(GeometricShape drawnShape) {
        super(ActionName.DRAW);
        this.drawnShape = drawnShape;
    }

    public void setDrawnShape(GeometricShape drawnShape) {
        this.drawnShape = drawnShape;
    }

    public GeometricShape getDrawnShape() {
        return drawnShape;
    }

}
