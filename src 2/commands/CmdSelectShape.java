package commands;

import model.DrawingModel;
import model.shape.AbstractShape;
import model.shape.Shape;

public class CmdSelectShape implements Command {

    private Shape shape;
    private boolean selectedState;

    public CmdSelectShape(Shape shape, boolean selectedState) {
        this.shape = shape;
        this.selectedState = selectedState;
    }

    @Override
    public void execute() {
        shape.setSelected(selectedState);
    }

    @Override
    public void unexecute() {
        if (shape.isSelected()) shape.setSelected(false);
        else shape.setSelected(true);
    }
}
