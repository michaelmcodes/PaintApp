package model.shape;

import java.awt.*;

public class ShapeMakerFacade {
    private Rectangle rectangle;
    private Triangle triangle;
    private Ellipse ellipse;

    public ShapeMakerFacade(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public ShapeMakerFacade(Triangle triangle) {
        this.triangle = triangle;
    }
    public ShapeMakerFacade(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    public void drawRectangle(Graphics2D graphics2D) {
        rectangle.draw(graphics2D);
    }
    public void drawTriangle(Graphics2D graphics2D) {
        triangle.draw(graphics2D);
    }
    public void drawEllipse(Graphics2D graphics2D) {
        ellipse.draw(graphics2D);
    }
}
