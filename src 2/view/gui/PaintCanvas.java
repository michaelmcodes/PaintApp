package view.gui;

import model.MouseMode;
import model.ShapeCreator;
import model.State;
import model.action.Action;
import model.action.DrawAction;
import model.action.MoveAction;
import model.shape.*;
import util.Util;
import view.handler.MouseListenerHandler;
import view.handler.MouseMotionListenerHandler;

import javax.swing.JComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PaintCanvas extends JComponent {

    private final ShapeCreator shapeCreator = new ShapeCreator();

    public ArrayList<AbstractShape> shapes = new ArrayList<>();
    public ArrayList<AbstractShape> copiedShapes = new ArrayList<>();

    public ArrayList<Action> redoActions = new ArrayList<>();
    public ArrayList<Action> actions = new ArrayList<>();

    private final ArrayList<AbstractShape> tempMovedShapesArraylist = new ArrayList<>();

    private State state = Util.getDefaultState();
    private Point currentMousePressedPoint;
    private Point initialPoint;

    public PaintCanvas() {
        addMouseListener(new MouseListenerHandler(this));
        addMouseMotionListener(new MouseMotionListenerHandler(this));
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;

        Iterator<AbstractShape> shapeIterator = shapes.iterator();

        while (shapeIterator.hasNext()) {
            AbstractShape shape = shapeIterator.next();
            ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade(shape);
            shapeMakerFacade.drawShape(graphics2d);
        }

    }

    private void setSelectedShapes(Point selectedPoint, boolean isDragging) {
        boolean isAnyShapeSelected = false;
        for (AbstractShape shape : shapes) {
            if (shape.select(selectedPoint)) {
                shape.setSelected(true);
                isAnyShapeSelected = true;
            }
        }
        if (!isDragging)
            if (!isAnyShapeSelected) {
                unselectAllTheShapes();
            }
    }

    private void unselectAllTheShapes() {
        for (AbstractShape shape : shapes) {
            shape.setSelected(false);
        }
        for (Action action : redoActions) {
            switch (action.getActionName()) {
                case DRAW:
                    DrawAction drawAction = (DrawAction) action;
                    AbstractShape shape = drawAction.getDrawnShape();
                    shape.setSelected(false);
                    break;
            }
        }
    }

    private void initMovingShapes() {
        for (AbstractShape shape : shapes) {
            if (shape.isSelected) {
                tempMovedShapesArraylist.add(shape);
            }
        }

    }

    private void moveSelectedShapes(int differenceInX, int differenceInY) {
        for (AbstractShape shape : shapes) {
            if (shape.isSelected) {
                shape.move(new Point(differenceInX, differenceInY));
            }
        }

    }

    public void handleMouseReleased(Point point) {

        if (state.getMouseMode() == MouseMode.MOVE) {
            initMovingShapes();
            int diffX = point.x - initialPoint.x;
            int diffY = point.y - initialPoint.y;
            ArrayList<AbstractShape> tempShapes = new ArrayList<>();
            tempShapes.addAll(tempMovedShapesArraylist);
            MoveAction moveAction = new MoveAction(tempShapes, new Point(diffX, diffY));
            actions.add(moveAction);
            tempMovedShapesArraylist.clear();
        }
    }

    public void handleMousePressed(Point point) {
        switch (state.getMouseMode()) {
            case DRAW: {
                unselectAllTheShapes();
                AbstractShape shape = shapeCreator.createShape(state.getShapeType(), point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                shapes.add(shape);
                actions.add(new DrawAction(shape));
                break;
            }
            case SELECT: {
                setSelectedShapes(point, false);
                break;
            }
            case MOVE: {
                initialPoint = point;
                currentMousePressedPoint = point;
                break;
            }


        }
        repaint();
    }

    public void handleMouseDragged(Point point) {
        switch (state.getMouseMode()) {
            case DRAW: {
                if (!shapes.isEmpty()) {
                    shapes.get(shapes.size() - 1).setEndPoint(point);
                }
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
        repaint();
    }
}
