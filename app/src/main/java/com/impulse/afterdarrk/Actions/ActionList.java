package com.impulse.afterdarrk.Actions;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
    private final List<ActionType> actions;
    private int actionPos;

    public ActionList(List<ActionType> actions) {
        this.actions = actions;
        actionPos = 0;
    }

    public boolean match(ActionType actionType) {
        if (actions.get(actionPos) == actionType) {
            actionPos++;
            return true;
        }
        actionPos = 0;
        return false;
    }

    public boolean completed() {
        return (actionPos == actions.size());
    }

    public List<ActionType> remaining() {
        List<ActionType> remaining = new ArrayList<>();

        for (int i = actionPos; i < actions.size(); i++) {
            remaining.add(actions.get(i));
        }

        return remaining;
    }
}
