package view.handler;

import controller.JPaintController;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseMotionListenerHandler implements MouseMotionListener {

    private final JPaintController controller;
    public MouseMotionListenerHandler(JPaintController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        controller.handleMouseDragged(point);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
