package com.impulse.afterdarrk.Actions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.R;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class ActionButton extends DisplayObj {
    private final Player player;
    private final CartesianCoords position;
    private final CartesianCoords size;
    private final ActionType actionType;
    private  Bitmap bitmap;


    ActionButton(Player player, CartesianCoords position, CartesianCoords size, ActionType actionType, Context context) {
        this.player = player;
        this.position = position;
        this.actionType = actionType;
        this.size = size;

        switch (this.actionType) {
            case FIRE:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire);
                break;
            case ICE:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ice);
                break;
            case LIGHTNING:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightning);
                break;
            default:
                bitmap = null;
                break;
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.round(size.getX()), (int) Math.round(size.getY()), true);

    }

    @Override
    public void draw(Canvas canvas) {
        int left = (int) Math.round(position.getX());
        int top = (int) Math.round(position.getY());

        int width = (int) Math.round(size.getX());
        int height = (int) Math.round(size.getY());

        Rect rect = new Rect(left, top, left + width, top + height);

        canvas.drawBitmap(bitmap, null, rect, null);
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
