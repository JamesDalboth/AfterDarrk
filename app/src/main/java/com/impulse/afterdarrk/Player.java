package com.impulse.afterdarrk;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class Player extends DisplayObj {
    private Enemy target;
    private Image image;
    private boolean alive;

    public final int radius;

    public Player(Image image, int radius) {
        this.target = null;
        this.image = image;
        this.radius = radius;

        alive = true;
    }

    public void useAction(ActionType actionType) {
        if (target == null) {
            return;
        }
        target.attack(actionType);
        if (target.isDead()) {
            target = null;
        }
    }

    public void setEnemy(Enemy setTarget) {
        this.target = setTarget;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle((Main.width / 2) , (Main.height / 2) - (radius/2), radius, paint);
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        return false;
    }

    @Override
    public void touch(View view, MotionEvent event) {

    }

    public Enemy getEnemy() {
        return target;
    }
}
