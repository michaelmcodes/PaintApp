package view.gui;

import model.DrawingModel;
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

    private DrawingModel model;

    public void setModel(DrawingModel model) { this.model = model; }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;

        Iterator<AbstractShape> shapeIterator = model.getAll().iterator();

        while (shapeIterator.hasNext()) {
            AbstractShape shape = shapeIterator.next();
            ShapeMakerFacade shapeMakerFacade = new ShapeMakerFacade(shape);
            shapeMakerFacade.drawShape(graphics2d);
        }

    }
}
