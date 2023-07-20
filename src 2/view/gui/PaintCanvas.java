package view.gui;

import model.MouseMode;
import model.State;
import model.action.Action;
import model.action.DrawAction;
import model.action.MoveAction;
import model.shape.*;
import model.shape.Rectangle;
import util.Util;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class PaintCanvas extends JComponent implements MouseListener, MouseMotionListener {

    public ArrayList<GeometricShape> shapes = new ArrayList<>();

    public ArrayList<Action> undoneActions = new ArrayList<>();
    public ArrayList<Action> actions = new ArrayList<>();

    private final ArrayList<GeometricShape> tempMovedShapesArraylist = new ArrayList<>();

    private State state = Util.getDefaultState();
    private Point currentMousePressedPoint;
    private Point initialPoint;

    public PaintCanvas() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;

        Iterator<GeometricShape> shapeIterator = shapes.iterator();

        while (shapeIterator.hasNext()) {
            GeometricShape shape = shapeIterator.next();
            if (shape instanceof Rectangle) {
                ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade((Rectangle) shape);
                shapeMakerFacade.drawRectangle(graphics2d);
            } else if (shape instanceof Ellipse) {
                ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade((Ellipse) shape);
                shapeMakerFacade.drawEllipse(graphics2d);
            } else if (shape instanceof Triangle) {
                ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade((Triangle) shape);
                shapeMakerFacade.drawTriangle(graphics2d);
            }
        }

    }

    private void setSelectedShapes(Point selectedPoint, boolean isDragging) {
        boolean isAnyShapeSelected = false;
        for (GeometricShape shape : shapes) {
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
        for (GeometricShape shape : shapes) {
            shape.setSelected(false);
        }
        for (Action action : undoneActions) {
            switch (action.getActionName()) {
                case DRAW:
                    DrawAction drawAction = (DrawAction) action;
                    GeometricShape shape = drawAction.getDrawnShape();
                    shape.setSelected(false);
                    break;
            }
        }
    }

    private void initMovingShapes() {
        for (GeometricShape shape : shapes) {
            if (shape.isSelected) {
                tempMovedShapesArraylist.add(shape);
            }
        }

    }

    private void moveSelectedShapes(int differenceInX, int differenceInY) {
        for (GeometricShape shape : shapes) {
            if (shape.isSelected) {
                Point startingPoint = shape.getStartPoint();
                Point endingPoint = shape.getEndPoint();
                Point newStartPoint = new Point(startingPoint.x + differenceInX, startingPoint.y + differenceInY);
                Point newEndPoint = new Point(endingPoint.x + differenceInX, endingPoint.y + differenceInY);
                shape.setStartPoint(newStartPoint);
                shape.setEndPoint(newEndPoint);
            }
        }


    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());

        switch (state.getMouseMode()) {
            case DRAW: {
                unselectAllTheShapes();
                switch (state.getShapeType()) {
                    case RECTANGLE: {
                        Rectangle rectangle = new Rectangle(point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                        shapes.add(rectangle);

                        actions.add(new DrawAction(rectangle));
                        break;
                    }
                    case ELLIPSE: {
                        Ellipse ellipse = new Ellipse(point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                        shapes.add(ellipse);

                        actions.add(new DrawAction(ellipse));
                        break;
                    }
                    case TRIANGLE: {
                        Triangle triangle = new Triangle(point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                        shapes.add(triangle);

                        actions.add(new DrawAction(triangle));
                        break;
                    }
                }
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

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());

        if (state.getMouseMode() == MouseMode.MOVE) {
            initMovingShapes();
            int diffX = point.x - initialPoint.x;
            int diffY = point.y - initialPoint.y;
            ArrayList<GeometricShape> tempShapes = new ArrayList<>();
            tempShapes.addAll(tempMovedShapesArraylist);
            MoveAction moveAction = new MoveAction(tempShapes, new Point(diffX, diffY));
            actions.add(moveAction);
            tempMovedShapesArraylist.clear();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());

        switch (state.getMouseMode()) {
            case DRAW: {
                if (shapes.size() > 0) {
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
