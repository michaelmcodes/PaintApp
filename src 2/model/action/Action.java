package model.action;

public class Action {
    private ActionName actionName;

    public Action(ActionName actionName) {
        this.actionName = actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public ActionName getActionName() {
        return actionName;
    }
}
