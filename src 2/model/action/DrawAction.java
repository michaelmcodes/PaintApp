package model.action;

import model.shape.AbstractShape;

public class DrawAction extends Action {

    private AbstractShape drawnShape;

    public DrawAction(AbstractShape drawnShape) {
        super(ActionName.DRAW);
        this.drawnShape = drawnShape;
    }

    public void setDrawnShape(AbstractShape drawnShape) {
        this.drawnShape = drawnShape;
    }

    public AbstractShape getDrawnShape() {
        return drawnShape;
    }

}
