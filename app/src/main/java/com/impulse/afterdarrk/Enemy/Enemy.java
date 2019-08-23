package com.impulse.afterdarrk.Enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.LevelRules;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;
import com.impulse.afterdarrk.Utils.PolarCoords;

import java.util.List;

public abstract class Enemy extends DisplayObj {
    private static int INIT_DISTANCE = 1000;

    public static int NUM_ENEMIES = 0;
    public static int DEAD_ENEMIES = 0;
    public static boolean BLOB_ALIVE = false;

    private ActionList actions;
    protected boolean dead;
    private final int radius;
    private double speed;

    protected Player player;

    public Enemy(double speed, Player player, double angle, List<ActionType> actions, int size,
                 DisplayObj parent) {
        super(new PolarCoords(INIT_DISTANCE, angle).toCartesian(Main.center), parent);

        NUM_ENEMIES++;

        this.dead = false;
        this.speed = speed;
        this.player = player;
        this.radius = size;

        this.actions = new ActionList(actions, new CartesianCoords(0, -size*2), this);

        addObj(this.actions);
    }

    public boolean attack(ActionType type) {
        boolean result = actions.match(type);
        if (actions.completed()) {
            die();
        }
        return result;
    }

    public boolean isDead() {
        return dead;
    }

    protected void die() {
        NUM_ENEMIES--;
        DEAD_ENEMIES++;
        dead = true;
    }

    private void move() {
        PolarCoords polar = getRelativePosition().toPolar(Main.center).deltaRadius(-speed);
        setRelativePosition(polar.toCartesian(Main.center));

        if (polar.getRadius() < 100) {
            System.out.println("Player killed");
            player.die();
        }
    }

    public void update() {
        move();
    }

    public boolean touch(View v, MotionEvent event) {
        CartesianCoords pos = new CartesianCoords(event.getX(), event.getY());

        if (pos.toPolar(getAbsolutePosition()).getRadius() < radius) {
            player.setEnemy(this);
            return true;
        }

        return false;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        if (player.getEnemy() == this) {
            paint.setColor(Color.BLUE);
        } else {
            paint.setColor(Color.BLACK);
        }

        canvas.drawCircle(Math.round(getAbsolutePosition().getX()),
                Math.round(getAbsolutePosition().getY() - (radius /2)), radius, paint);
    }
}
