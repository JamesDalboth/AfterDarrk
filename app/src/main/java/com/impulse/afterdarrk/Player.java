package com.impulse.afterdarrk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.view.View;

class Player extends DisplayObj {
    private Enemy target;
    private Image image;
    private boolean alive;
    private int radius = 100;

    public Player(Image image) {
        this.target = null;
        this.image = image;
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

    public void setEnemy(Enemy set_target) {
        this.target = set_target;
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
        canvas.drawCircle((Main.width / 2) , (Main.height / 2) - (radius/2), 100, paint);
    }
}
