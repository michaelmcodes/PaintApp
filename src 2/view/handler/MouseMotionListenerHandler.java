package view.handler;

import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseMotionListenerHandler implements MouseMotionListener {

    private final PaintCanvas paintCanvas;
    public MouseMotionListenerHandler(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point point = new Point();
        point.setLocation(mouseEvent.getX(), mouseEvent.getY());
        paintCanvas.handleMouseDragged(point);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
