package com.impulse.afterdarrk.Actions;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Display.BitmapLoader;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class ActionIcon extends DisplayObj {
    static int ACTION_WIDTH = 50;

    private final ActionType actionType;
    private Bitmap image;

    ActionIcon(ActionType actionType, CartesianCoords position, DisplayObj parent) {
        super(position, parent);
        this.actionType = actionType;

        image = null;

        switch(this.actionType) {
            case FIRE:
                image = BitmapLoader.getInstance().getFireBitmap();
                break;
            case ICE:
                image = BitmapLoader.getInstance().getIceBitmap();
                break;
            case LIGHTNING:
                image = BitmapLoader.getInstance().getLightningBitmap();
                break;
        }

        image = Bitmap.createScaledBitmap(image, ACTION_WIDTH, ACTION_WIDTH, true);
    }

    ActionType getActionType() {
        return actionType;
    }

    @Override
    public void draw(Canvas canvas) {
        int left = (int) Math.round(getAbsolutePosition().getX());
        int top = (int) Math.round(getAbsolutePosition().getY());

        Rect rect = new Rect(left, top, ACTION_WIDTH + left, ACTION_WIDTH + top);

        canvas.drawBitmap(image, null, rect, null);
    }

    @Override
    public boolean touch(View view, MotionEvent event) {
        return false;
    }

}
