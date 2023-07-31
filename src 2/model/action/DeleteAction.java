package model.action;

import model.shape.AbstractShape;

import java.util.ArrayList;

public class DeleteAction extends Action {

    private ArrayList<AbstractShape> deleteShape;

    public DeleteAction(ArrayList<AbstractShape> deleteShape) {
        super(ActionName.DELETE);
        this.deleteShape = deleteShape;
    }

    public void setDeletedShapes(ArrayList<AbstractShape> deleteShape) {
        this.deleteShape = deleteShape;
    }

    public ArrayList<AbstractShape> getDeletedShapes() {
        return deleteShape;
    }

}
