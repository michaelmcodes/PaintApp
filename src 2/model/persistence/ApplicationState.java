package model.persistence;

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import model.interfaces.IStateListener;
import util.Util;
import view.interfaces.IUiModule;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private final State state = Util.getDefaultState();
    private IStateListener stateListener = null;

    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
    }

    public void setStateListener(IStateListener stateListener) {
        this.stateListener = stateListener;
    }


    @Override
    public void setActiveShape() {
        state.setShapeType(uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog()));
        if (stateListener != null)
            stateListener.onStateChanged(state);
    }

    @Override
    public void setActivePrimaryColor() {
        state.setShapePrimaryColor(uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog()));
        if (stateListener != null)
            stateListener.onStateChanged(state);
    }

    @Override
    public void setActiveSecondaryColor() {
        state.setShapeSecondaryColor(uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog()));
        if (stateListener != null)
            stateListener.onStateChanged(state);
    }

    @Override
    public void setActiveShadingType() {
        state.setShapeShadingType(uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog()));
        if (stateListener != null)
            stateListener.onStateChanged(state);
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        state.setMouseMode(uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog()));
        if (stateListener != null)
            stateListener.onStateChanged(state);
    }

    @Override
    public ShapeType getActiveShapeType() {
        return state.getShapeType();
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return state.getShapePrimaryColor();
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return state.getShapeSecondaryColor();
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return state.getShapeShadingType();
    }

    @Override
    public MouseMode getActiveMouseMode() {
        return state.getMouseMode();
    }
}
