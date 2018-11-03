package com.impulse.afterdarrk;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Enemy.Enemy;

import static com.impulse.afterdarrk.Enemy.Enemy.RADIUS;

public class Player extends DisplayObj {
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
        canvas.drawCircle((Main.width / 2) , (Main.height / 2) - (radius/2), 100, paint);
        if (target == null) {
            return;
        }
        paint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(target.getPosition().x - (RADIUS/2),
                                 target.getPosition().y - (RADIUS/2),
                                      RADIUS,
                                      RADIUS),
                        paint);
    }
}
