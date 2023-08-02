package view.gui;

import model.DrawingModel;
import model.shape.AbstractShape;
import model.shape.ShapeMakerFacade;

import javax.swing.*;
import java.awt.*;
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
