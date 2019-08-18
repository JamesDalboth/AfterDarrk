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

public class DirectionButton extends DisplayObj {
    private final Player player;
    private final CartesianCoords size;
    private final DirectionType directionType;
    private Bitmap bitmap;

    public DirectionButton(Player player, CartesianCoords size, DirectionType direction, CartesianCoords position, DisplayObj parent) {
        super(position, parent);

        this.player = player;
        this.directionType = direction;
        this.size = size;
        switch (directionType) {
            case LEFT:
                bitmap = BitmapLoader.getInstance().getLeftBitmap();
                break;
            case RIGHT:
                bitmap = BitmapLoader.getInstance().getRightBitmap();
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
        return false;
    }
}
