package com.impulse.afterdarrk.Actions;

import android.content.Context;
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

public class DirectionButton extends DisplayObj {
    private final Player player;
    private final CartesianCoords position;
    private final CartesianCoords size;
    private final DirectionType directionType;
    private final Bitmap bitmap;
    private Context activityContext;

    public DirectionButton(Player player, CartesianCoords position, CartesianCoords size, DirectionType direction, Context context) {
        this.player = player;
        this.position = position;
        this.directionType = direction;
        this.size = size;
        this.activityContext = context;
        switch (directionType) {
            case LEFT:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activityContext.getResources(), R.drawable.left), (int) Math.round(size.getX()), (int) Math.round(size.getY()), true);
                break;
            case RIGHT:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activityContext.getResources(), R.drawable.right), (int) Math.round(size.getX()), (int) Math.round(size.getY()), true);
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
      return true;
    }

    @Override
    public void touch(View view, MotionEvent event) {
    }

}
