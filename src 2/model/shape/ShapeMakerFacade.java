package model.shape;

import java.awt.*;

public class ShapeMakerFacade {
    private final AbstractShape shape;

    public ShapeMakerFacade(AbstractShape shape) {
        this.shape = shape;
    }

    public void drawShape(Graphics2D graphics2D) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            rectangle.draw(graphics2D);
        } else if (shape instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) shape;
            ellipse.draw(graphics2D);
        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            triangle.draw(graphics2D);
        }
    }
}
