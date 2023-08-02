package commands;

import model.shape.AbstractShape;

public class CmdSelectShape implements Command {

    private AbstractShape shape;
    private boolean selectedState;

    public CmdSelectShape(AbstractShape shape, boolean selectedState) {
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
