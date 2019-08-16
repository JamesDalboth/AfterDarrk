package com.impulse.afterdarrk.Actions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

import java.util.ArrayList;
import java.util.List;

public class ActionBar extends DisplayObj {

    private List<ActionButton> actionButtons;
    private List<DirectionButton> directionButtons;

    private final int top;
    private final int height;

    private Context activityContext;

    public ActionBar(Player player, Context context) {

        activityContext = context;

        sortKey = 1;

        height = Main.height / 6;

        top = Main.height - (height/5)*6;

        System.out.println(top);

        int width = Main.width / 10;

        CartesianCoords size = new CartesianCoords(width, height);

        ActionButton fireButton = new ActionButton(player, new CartesianCoords(width/2, top), size, ActionType.FIRE, activityContext);
        ActionButton iceButton = new ActionButton(player, new CartesianCoords((width * 7)/4, top), size, ActionType.ICE, activityContext);
        ActionButton lightningButton = new ActionButton(player, new CartesianCoords(width * 3, top), size, ActionType.LIGHTNING, activityContext);

        actionButtons = new ArrayList<>();

        actionButtons.add(fireButton);
        actionButtons.add(iceButton);
        actionButtons.add(lightningButton);

        DirectionButton leftButton = new DirectionButton(player, new CartesianCoords(width * 7, top), size, DirectionType.LEFT, activityContext);
        DirectionButton rightButton = new DirectionButton(player, new CartesianCoords((width * 17)/2, top), size, DirectionType.RIGHT, activityContext);

        directionButtons = new ArrayList<>();

        directionButtons.add(leftButton);
        directionButtons.add(rightButton);

    }

    @Override
    public void draw(Canvas canvas) {

        for (ActionButton actionButton : actionButtons) {
            actionButton.draw(canvas);
        }

        for (DirectionButton directionButton : directionButtons) {
            directionButton.draw(canvas);
        }
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        if (pos.getY() < top) {
            return false;
        }
        for (ActionButton actionButton : actionButtons) {
            if (actionButton.isHit(pos)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void touch(View view, MotionEvent event) {
        for (ActionButton actionButton : actionButtons) {
            if (actionButton.isHit(new CartesianCoords(event.getX(), event.getY()))) {
                actionButton.touch(view, event);
                return;
            }
        }
    }
}
