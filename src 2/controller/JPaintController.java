package controller;

import model.State;
import model.action.Action;
import model.action.DrawAction;
import model.interfaces.IApplicationState;
import model.interfaces.IStateListener;
import model.persistence.ApplicationState;
import model.shape.GeometricShape;
import util.Util;
import view.EventName;
import view.gui.Gui;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

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
                GeometricShape shape = drawAction.getDrawnShape();
                paintCanvas.actions.remove(action);
                paintCanvas.shapes.remove(shape);
                paintCanvas.undoneActions.add(action);
                break;
            }
        }
        paintCanvas.repaint();
    }

    private void redo() {
        if (paintCanvas.undoneActions.isEmpty()) {
            return;
        }
        Action action = paintCanvas.undoneActions.get(paintCanvas.undoneActions.size() - 1);
        switch (action.getActionName()) {
            case DRAW: {
                DrawAction drawAction = (DrawAction) action;
                GeometricShape shape = drawAction.getDrawnShape();
                paintCanvas.shapes.add(shape);
                paintCanvas.undoneActions.remove(action);
                paintCanvas.actions.add(action);
                break;
            }
        }
        paintCanvas.repaint();
    }

    private void copy() {
    }

    private void paste() {
    }

    private void delete() {
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
