package com.impulse.afterdarrk.Actions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.R;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class LookButton extends DisplayObj {
    private final Player player;
    private final CartesianCoords position;
    private final CartesianCoords size;
    private final DirectionType directionType;
    private final Bitmap bitmap;

    public LookButton(Player player, CartesianCoords position, CartesianCoords size, DirectionType direction) {
        this.player = player;
        this.position = position;
        this.directionType = direction;
        this.size = size;
        switch (directionType) {
            case LEFT:
                bitmap = BitmapFactory.decodeResource(Main.context.getResources(), R.drawable.fire);
                break;
            case RIGHT:
                bitmap = BitmapFactory.decodeResource(Main.context.getResources(), R.drawable.fire);
                break;
            default:
                bitmap = null;
                break;
        }
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
      /*  boolean horizontalContained = pos.getX() > position.getX() && pos.getX() <= position.getX() + size.getX();
        boolean verticalContained = pos.getY() > position.getY() && pos.getY() <= position.getY() + size.getY();

        return horizontalContained && verticalContained;*/
      return true;
    }

    @Override
    public void touch(View view, MotionEvent event) {
        /*player.useAction(directionType);*/
    }

}
