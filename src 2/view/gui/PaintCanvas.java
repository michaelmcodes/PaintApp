package view.gui;

import model.DrawingModel;
import model.shape.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class PaintCanvas extends JComponent {


    private DrawingModel model;

    public void setModel(DrawingModel model) { this.model = model; }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        model.draw(graphics2d);

    }
}
