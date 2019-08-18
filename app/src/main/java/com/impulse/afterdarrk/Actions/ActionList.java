package com.impulse.afterdarrk.Actions;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Utils.CartesianCoords;

import java.util.ArrayList;
import java.util.List;

import static com.impulse.afterdarrk.Actions.ActionIcon.ACTION_WIDTH;

public class ActionList extends DisplayObj {
    private final List<ActionIcon> actions;
    private int actionPos;

    public ActionList(List<ActionType> actions, CartesianCoords position, DisplayObj parent) {
        super(position, parent);

        actionPos = 0;
        this.actions = new ArrayList<>();

        double width = actions.size() * ACTION_WIDTH + (actions.size () - 1) * ACTION_WIDTH/2;
        double left = position.getX() - width/2;

        for (int i = 0; i < actions.size(); i++) {
            ActionType action = actions.get(i);
            int rectLeft = (int) Math.round(left + (i * ACTION_WIDTH * 3/2));

            CartesianCoords actionPos = new CartesianCoords(rectLeft, 0);

            ActionIcon actionIcon = new ActionIcon(action, actionPos, this);

            addObj(actionIcon);
            this.actions.add(actionIcon);
        }
    }

    public boolean match(ActionType actionType) {
        if (actions.get(actionPos).getActionType() == actionType) {
            removeObj(actions.get(actionPos));
            actionPos++;

            return true;
        }

        for (int i = 0; i < actionPos; i++) {
            addObj(actions.get(i));
        }

        actionPos = 0;
        return false;
    }

    public boolean completed() {
        return (actionPos == actions.size());
    }

    // ActionList does not get drawn
    @Override
    public void draw(Canvas canvas) {}

    // ActionList does not get touched
    @Override
    public boolean touch(View view, MotionEvent event) {
        return false;
    }
}
