package com.impulse.afterdarrk.Actions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class ActionButton extends DisplayObj {

    public static final int WIDTH = 200;

    private final Player player;
    private final CartesianCoords topLeft;
    private final ActionType actionType;

    public ActionButton(Player player, CartesianCoords topLeft, ActionType actionType) {
        this.player = player;
        this.topLeft = topLeft;
        this.actionType = actionType;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        switch (actionType) {
            case FIRE:
                paint.setColor(Color.RED);
                break;
            case ICE:
                paint.setColor(Color.BLUE);
                break;
            case LIGHTNING:
                paint.setColor(Color.BLACK);
                break;
        }

        int left = (int) Math.round(topLeft.getX());
        int top = (int) Math.round(topLeft.getY());

        Rect rect = new Rect(left, top, left + WIDTH, top + WIDTH);

        canvas.drawRect(rect, paint);
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        boolean horizontalContained = pos.getX() > topLeft.getX() && pos.getX() <= topLeft.getX() + WIDTH;
        boolean verticalContained = pos.getY() > topLeft.getY() && pos.getY() <= topLeft.getY() + WIDTH;

        return horizontalContained && verticalContained;
    }

    @Override
    public void touch(View view, MotionEvent event) {
        player.useAction(actionType);
    }
}
