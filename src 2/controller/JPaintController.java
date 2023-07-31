package controller;

import model.State;
import model.action.Action;
import model.action.DeleteAction;
import model.action.DrawAction;
import model.action.MoveAction;
import model.interfaces.IApplicationState;
import model.interfaces.IStateListener;
import model.persistence.ApplicationState;
import model.shape.AbstractShape;
import view.EventName;
import view.gui.Gui;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class JPaintController implements IJPaintController, IStateListener {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = (PaintCanvas) ((Gui) uiModule).getCanvas();
        ((ApplicationState) applicationState).setStateListener(this);
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.UNDO, this::undo);
        uiModule.addEvent(EventName.REDO, this::redo);
        uiModule.addEvent(EventName.COPY, this::copy);
        uiModule.addEvent(EventName.PASTE, this::paste);
        uiModule.addEvent(EventName.DELETE, this::delete);
        uiModule.addEvent(EventName.GROUP, this::group);
        uiModule.addEvent(EventName.UNGROUP, this::ungroup);
    }

    private void undo() {
        if (paintCanvas.actions.isEmpty()) {
            return;
        }
        Action action = paintCanvas.actions.get(paintCanvas.actions.size() - 1);
        switch (action.getActionName()) {
            case DRAW: {
                DrawAction drawAction = (DrawAction) action;
                AbstractShape shape = drawAction.getDrawnShape();
                paintCanvas.actions.remove(action);
                paintCanvas.shapes.remove(shape);
                paintCanvas.redoActions.add(action);
                break;
            }
            case MOVE: {
                MoveAction moveAction = (MoveAction) action;
                for (AbstractShape movedShape : moveAction.getMovedShapes()) {
                    paintCanvas.shapes.remove(movedShape);
                    Point startingPoint = movedShape.getStartPoint();
                    Point endingPoint = movedShape.getEndPoint();
                    Point newStartPoint = new Point(startingPoint.x - moveAction.getMovedPoint().x, startingPoint.y - moveAction.getMovedPoint().y);
                    Point newEndPoint = new Point(endingPoint.x - moveAction.getMovedPoint().x, endingPoint.y - moveAction.getMovedPoint().y);
                    movedShape.setStartPoint(newStartPoint);
                    movedShape.setEndPoint(newEndPoint);
                    paintCanvas.shapes.add(movedShape);
                }
                paintCanvas.actions.remove(action);
                paintCanvas.redoActions.add(action);
                break;
            }
            case DELETE: {
                DeleteAction deleteAction = (DeleteAction) action;
                paintCanvas.shapes.addAll(deleteAction.getDeletedShapes());
                paintCanvas.actions.remove(action);
                paintCanvas.redoActions.add(action);
                break;
            }
        }
        paintCanvas.repaint();

    }

    private void redo() {
        if (paintCanvas.redoActions.isEmpty()) {
            return;
        }
        Action action = paintCanvas.redoActions.get(paintCanvas.redoActions.size() - 1);
        switch (action.getActionName()) {
            case DRAW: {
                DrawAction drawAction = (DrawAction) action;
                AbstractShape shape = drawAction.getDrawnShape();
                paintCanvas.shapes.add(shape);
                paintCanvas.redoActions.remove(action);
                paintCanvas.actions.add(action);
                break;
            }
            case MOVE: {
                MoveAction moveAction = (MoveAction) action;
                for (AbstractShape movedShape : moveAction.getMovedShapes()) {
                    paintCanvas.shapes.remove(movedShape);
                    Point startingPoint = movedShape.getStartPoint();
                    Point endingPoint = movedShape.getEndPoint();
                    Point newStartPoint = new Point(startingPoint.x + moveAction.getMovedPoint().x, startingPoint.y + moveAction.getMovedPoint().y);
                    Point newEndPoint = new Point(endingPoint.x + moveAction.getMovedPoint().x, endingPoint.y + moveAction.getMovedPoint().y);
                    movedShape.setStartPoint(newStartPoint);
                    movedShape.setEndPoint(newEndPoint);
                    paintCanvas.shapes.add(movedShape);
                }
                paintCanvas.redoActions.remove(action);
                paintCanvas.actions.add(action);
                break;
            }
            case DELETE: {
                DeleteAction deleteAction = (DeleteAction) action;
                for (AbstractShape movedShape : deleteAction.getDeletedShapes()) {
                    paintCanvas.shapes.remove(movedShape);
                }
                paintCanvas.actions.add(action);
                paintCanvas.redoActions.remove(action);
                break;
            }
        }
        paintCanvas.repaint();
    }

    private void copy() {
        paintCanvas.copiedShapes.clear();
        for (AbstractShape shape : paintCanvas.shapes) {
            if (shape.isSelected) {
                AbstractShape newShape = shape.clone();
                Point startPoint = newShape.getStartPoint();
                newShape.setSelected(false);
                newShape.setStartPoint(new Point(600, 400));
                newShape.setEndPoint(new Point(newShape.getEndPoint().x - startPoint.x + 600, newShape.getEndPoint().y - startPoint.y + 400));
                paintCanvas.copiedShapes.add(newShape);
            }
        }
    }

    private void paste() {
        if (paintCanvas.copiedShapes.isEmpty())
            return;
        for (AbstractShape shape : paintCanvas.copiedShapes) {
            paintCanvas.shapes.add(shape);
            paintCanvas.actions.add(new DrawAction(shape));
        }
        paintCanvas.repaint();
    }

    private void delete() {
        ArrayList<AbstractShape> deletedShapes = new ArrayList<>();
        Iterator<AbstractShape> shapeIterator = paintCanvas.shapes.iterator();
        while (shapeIterator.hasNext()) {
            AbstractShape shape = shapeIterator.next();
            if (shape.isSelected) {
                deletedShapes.add(shape);
                shapeIterator.remove();
            }
        }
        paintCanvas.actions.add(new DeleteAction(deletedShapes));

        paintCanvas.repaint();
    }

    private void group() {
    }

    private void ungroup() {
    }

    @Override
    public void onStateChanged(State state) {
        paintCanvas.setState(state);
    }
}
