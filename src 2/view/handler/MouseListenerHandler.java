package view.handler;

import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListenerHandler implements MouseListener {

    private final PaintCanvas paintCanvas;
    public MouseListenerHandler(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        paintCanvas.handleMousePressed(point);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        paintCanvas.handleMouseReleased(point);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
