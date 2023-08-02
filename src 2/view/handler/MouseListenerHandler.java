package view.handler;

import controller.JPaintController;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListenerHandler implements MouseListener {

    private final JPaintController controller;
    public MouseListenerHandler(JPaintController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        controller.handleMousePressed(point);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        controller.handleMouseReleased(point);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
