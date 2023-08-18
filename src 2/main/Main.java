package main;

import controller.JPaintController;
import model.DrawingModel;
import model.PaintObservable;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        DrawingModel model = new DrawingModel();

        PaintCanvas paintCanvas = new PaintCanvas();
        paintCanvas.setModel(model);

        PaintObservable paintObservable = new PaintObservable();
        paintObservable.addObserver(paintCanvas);

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState, model, paintObservable);

    }
}
