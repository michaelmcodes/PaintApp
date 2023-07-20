package view.gui;

import model.State;
import model.action.Action;
import model.action.DrawAction;
import model.shape.GeometricShape;
import model.shape.Rectangle;
import model.shape.ShapeMakerFacade;
import util.Util;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class PaintCanvas extends JComponent implements MouseListener, MouseMotionListener {

    private State state = Util.getDefaultState();

    public ArrayList<GeometricShape> shapes = new ArrayList<>();
    public ArrayList<Action> undoneActions = new ArrayList<>();
    public ArrayList<Action> actions = new ArrayList<>();

    public PaintCanvas() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;

        Iterator<GeometricShape> shapeIterator = shapes.iterator();

        while (shapeIterator.hasNext()) {
            GeometricShape shape = shapeIterator.next();
            if (shape instanceof model.shape.Rectangle) {
                ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade((Rectangle) shape);
                shapeMakerFacade.drawRectangle(graphics2D);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = new Point();
        point.setLocation(e.getX(), e.getY());

        switch (state.getMouseMode()) {
            case DRAW: {
                switch (state.getShapeType()) {
                    case RECTANGLE: {
                        Rectangle rectangle = new Rectangle(point, point, state.getShapePrimaryColor(), state.getShapeSecondaryColor(), state.getShapeShadingType(), false);
                        shapes.add(rectangle);
                        actions.add(new DrawAction(rectangle));
                        break;
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = new Point();
        point.setLocation(e.getX(), e.getY());

        switch (state.getMouseMode()) {
            case DRAW: {
                if (shapes.size() > 0) {
                    shapes.get(shapes.size() - 1).setEndPoint(point);
                }
                break;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}