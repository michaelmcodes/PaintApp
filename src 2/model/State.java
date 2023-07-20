package model;

public class State {
    private ShapeType shapeType;
    private ShapeColor shapePrimaryColor;
    private ShapeColor shapeSecondaryColor;
    private ShapeShadingType shapeShadingType;
    private MouseMode mouseMode;

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public void setShapePrimaryColor(ShapeColor shapePrimaryColor) {
        this.shapePrimaryColor = shapePrimaryColor;
    }

    public void setShapeSecondaryColor(ShapeColor shapeSecondaryColor) {
        this.shapeSecondaryColor = shapeSecondaryColor;
    }

    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    public void setMouseMode(MouseMode mouseMode) {
        this.mouseMode = mouseMode;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public ShapeColor getShapePrimaryColor() {
        return shapePrimaryColor;
    }

    public ShapeColor getShapeSecondaryColor() {
        return shapeSecondaryColor;
    }

    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    public MouseMode getMouseMode() {
        return mouseMode;
    }
}
