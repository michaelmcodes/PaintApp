package controller;

import commands.*;
import model.DrawingModel;
import model.MouseMode;
import model.ShapeCreator;
import model.State;
import model.action.Action;
import model.action.DeleteAction;
import model.action.DrawAction;
import model.action.MoveAction;
import model.interfaces.IApplicationState;
import model.interfaces.IStateListener;
import model.persistence.ApplicationState;
import model.shape.AbstractShape;
import util.Util;
import view.EventName;
import view.gui.Gui;
import view.gui.PaintCanvas;
import view.handler.MouseListenerHandler;
import view.handler.MouseMotionListenerHandler;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class JPaintController implements IJPaintController, IStateListener {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvas paintCanvas;

    private final DrawingModel model;
    private final Stack<Command> commands = new Stack<>();
    private final Stack<Command> undoCommands = new Stack<>();

    private final ShapeCreator shapeCreator = new ShapeCreator();

    public ArrayList<AbstractShape> copiedShapes = new ArrayList<>();

    public ArrayList<Action> actions = new ArrayList<>();

    private State state = Util.getDefaultState();
    private Point currentMousePressedPoint;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, DrawingModel model) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = (PaintCanvas) ((Gui) uiModule).getCanvas();
        ((ApplicationState) applicationState).setStateListener(this);
        this.model = model;
        setupEvents();
        setMouseHandlers();
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

    private void setMouseHandlers() {
        paintCanvas.addMouseListener(new MouseListenerHandler(this));
        paintCanvas.addMouseMotionListener(new MouseMotionListenerHandler(this));
    }

    private void executeCommand(Command command, boolean shouldAddToCommands) {
        command.execute();
        if (shouldAddToCommands)
            commands.push(command);

        paintCanvas.repaint();
    }

    private void undo() {

        if (commands.empty()) return;

        if ((commands.peek() instanceof CmdEmpty)) {
            undoCommands.push(commands.pop());
            undo();
        } else if ((commands.peek() instanceof CmdMoveShape)) {
            while (commands.peek() instanceof CmdMoveShape) {
                commands.peek().unexecute();
                undoCommands.push(commands.pop());
            }
        } else {
            commands.peek().unexecute();
            undoCommands.push(commands.pop());
        }
        paintCanvas.repaint();

    }

    private void redo() {

        if (undoCommands.empty()) return;

        if ((undoCommands.peek() instanceof CmdEmpty)) {
            commands.push(undoCommands.pop());
            redo();
        } else if ((undoCommands.peek() instanceof CmdMoveShape)) {
            while (undoCommands.peek() instanceof CmdMoveShape) {
                undoCommands.peek().execute();
                commands.push(undoCommands.pop());
            }
        } else {
            undoCommands.peek().execute();
            commands.push(undoCommands.pop());
        }
        paintCanvas.repaint();
    }

    private void copy() {
        copiedShapes.clear();
        for (AbstractShape shape : model.getAll()) {
            if (shape.isSelected) {
                AbstractShape newShape = shape.clone();
                Point startPoint = newShape.getStartPoint();
                newShape.setSelected(false);
                newShape.setStartPoint(new Point(600, 400));
                newShape.setEndPoint(new Point(newShape.getEndPoint().x - startPoint.x + 600, newShape.getEndPoint().y - startPoint.y + 400));
                copiedShapes.add(newShape);
            }
        }
    }

    private void paste() {
        if (copiedShapes.isEmpty())
            return;
        executeCommand(new CmdPasteShape(model, copiedShapes), true);
        paintCanvas.repaint();
    }

    private void delete() {
        executeCommand(new CmdDeleteShape(model, getSelectedShapes()), true);
        paintCanvas.repaint();
    }

    private void group() {
    }

    private void ungroup() {
    }

    private void setSelectedShapes(Point selectedPoint, boolean isDragging) {
        boolean isAnyShapeSelected = false;
        for (AbstractShape shape : model.getAll()) {
            if (shape.select(selectedPoint)) {
                executeCommand(new CmdSelectShape(shape, true), false);
                isAnyShapeSelected = true;
            }
        }
        if (!isDragging)
            if (!isAnyShapeSelected) {
                unselectAllTheShapes();
            }
    }

    private void unselectAllTheShapes() {
        for (AbstractShape shape : model.getAll()) {
            executeCommand(new CmdSelectShape(shape, false), false);
        }
    }

    private ArrayList<AbstractShape> getSelectedShapes() {
        ArrayList<AbstractShape> shapes = new ArrayList<>();
        for (AbstractShape shape : model.getAll()) {
            if (shape.isSelected()) shapes.add(shape);
        }
        return shapes;
    }

    private void moveSelectedShapes(int differenceInX, int differenceInY) {
        for (AbstractShape shape : model.getAll()) {
            if (shape.isSelected) {
                executeCommand(new CmdMoveShape(shape, new Point(differenceInX, differenceInY)), true);
            }
        }

    }

    public void handleMouseReleased(Point point) {
        if (state.getMouseMode() == MouseMode.MOVE) {
            commands.add(new CmdEmpty());
        }
    }

    public void handleMousePressed(Point point) {
        switch (state.getMouseMode()) {
            case DRAW: {
                unselectAllTheShapes();
                AbstractShape shape = shapeCreator.createShape(state.getShapeType(), point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                executeCommand(new CmdAddShape(model, shape), true);
                break;
            }
            case SELECT: {
                setSelectedShapes(point, false);
                break;
            }
            case MOVE: {
                currentMousePressedPoint = point;
                break;
            }


        }
        paintCanvas.repaint();
    }

    public void handleMouseDragged(Point point) {
        switch (state.getMouseMode()) {
            case DRAW: {
                AbstractShape newShape = model.getLatest().clone();
                newShape.setEndPoint(point);
                executeCommand(new CmdUpdateShape(model.getLatest(), newShape), false);
                break;
            }
            case SELECT: {
                setSelectedShapes(point, true);
                break;
            }
            case MOVE: {
                int movingShapeDifferenceX = point.x - currentMousePressedPoint.x;
                int movingShapeDifferenceY = point.y - currentMousePressedPoint.y;
                moveSelectedShapes(movingShapeDifferenceX, movingShapeDifferenceY);
                currentMousePressedPoint = point;
                break;
            }
        }
        paintCanvas.repaint();
    }


    @Override
    public void onStateChanged(State state) {
        this.state = state;
    }
}
