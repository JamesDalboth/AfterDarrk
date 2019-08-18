package com.impulse.afterdarrk.Actions;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Display.BitmapLoader;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class ActionButton extends DisplayObj {
    private final Player player;
    private final CartesianCoords size;
    private final ActionType actionType;
    private Bitmap bitmap;

    ActionButton(Player player, CartesianCoords size, ActionType actionType, CartesianCoords position, DisplayObj parent) {
        super(position, parent);
        this.player = player;
        this.actionType = actionType;
        this.size = size;

        switch (this.actionType) {
            case FIRE:
                bitmap = BitmapLoader.getInstance().getFireBitmap();
                break;
            case ICE:
                bitmap = BitmapLoader.getInstance().getIceBitmap();
                break;
            case LIGHTNING:
                bitmap = BitmapLoader.getInstance().getLightningBitmap();
                break;
            default:
                bitmap = null;
                break;
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.round(size.getX()), (int) Math.round(size.getY()), true);
    }

    @Override
    public void draw(Canvas canvas) {
        int left = (int) Math.round(getAbsolutePosition().getX());
        int top = (int) Math.round(getAbsolutePosition().getY());

        int width = (int) Math.round(size.getX());
        int height = (int) Math.round(size.getY());

        Rect rect = new Rect(left, top, left + width, top + height);

        canvas.drawBitmap(bitmap, null, rect, null);
    }

    @Override
    public boolean touch(View view, MotionEvent event) {
        CartesianCoords pos = new CartesianCoords(event.getX(), event.getY());
        boolean horizontalContained = pos.getX() > getAbsolutePosition().getX() && pos.getX() <= getAbsolutePosition().getX() + size.getX();
        boolean verticalContained = pos.getY() > getAbsolutePosition().getY() && pos.getY() <= getAbsolutePosition().getY() + size.getY();

        if (horizontalContained && verticalContained) {
            player.useAction(actionType);
            return true;
        }

        return false;
    }
}
