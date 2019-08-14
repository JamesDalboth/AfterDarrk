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
    private final Player player;
    private final CartesianCoords position;
    private final CartesianCoords size;
    private final ActionType actionType;

    ActionButton(Player player, CartesianCoords position, CartesianCoords size, ActionType actionType) {
        this.player = player;
        this.position = position;
        this.actionType = actionType;
        this.size = size;
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

        int left = (int) Math.round(position.getX());
        int top = (int) Math.round(position.getY());

        int width = (int) Math.round(size.getX());
        int height = (int) Math.round(size.getY());

        Rect rect = new Rect(left, top, left + width, top + height);

        canvas.drawRect(rect, paint);
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        boolean horizontalContained = pos.getX() > position.getX() && pos.getX() <= position.getX() + size.getX();
        boolean verticalContained = pos.getY() > position.getY() && pos.getY() <= position.getY() + size.getY();

        return horizontalContained && verticalContained;
    }

    @Override
    public void touch(View view, MotionEvent event) {
        player.useAction(actionType);
    }
}
