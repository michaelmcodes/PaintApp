package model.shape;

import java.awt.*;

public class ShapeMakerFacade {
    private Rectangle rectangle;

    public ShapeMakerFacade(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void drawRectangle(Graphics2D graphics2D) {
        rectangle.draw(graphics2D);
    }
}
