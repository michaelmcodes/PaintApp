package util;

import model.*;

import java.awt.*;

public class Util {
    public static Color getColorFromShapeColor(ShapeColor shapeColor) {
        switch (shapeColor) {
            case BLUE: {
                return Color.BLUE;
            }
            case GREEN: {
                return Color.GREEN;
            }
            case MAGENTA: {
                return Color.MAGENTA;
            }
            case GRAY: {
                return Color.GRAY;
            }
            case YELLOW: {
                return Color.YELLOW;
            }
            case ORANGE: {
                return Color.ORANGE;
            }
            case CYAN: {
                return Color.CYAN;
            }
            case PINK: {
                return Color.PINK;
            }
            case LIGHT_GRAY: {
                return Color.LIGHT_GRAY;
            }
            case WHITE: {
                return Color.WHITE;
            }
            case DARK_GRAY: {
                return Color.DARK_GRAY;
            }
            case RED: {
                return Color.RED;
            }
            default: {
                return Color.BLACK;
            }
        }
    }

    public static State getDefaultState() {
        State state = new State();
        state.setShapeType(ShapeType.RECTANGLE);
        state.setShapePrimaryColor(ShapeColor.BLUE);
        state.setShapeSecondaryColor(ShapeColor.GREEN);
        state.setShapeShadingType(ShapeShadingType.FILLED_IN);
        state.setMouseMode(MouseMode.DRAW);
        return state;
    }
}
