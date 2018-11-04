package com.impulse.afterdarrk.Enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;
import com.impulse.afterdarrk.Utils.PolarCoords;

public abstract class Enemy extends DisplayObj {
    // Initial Distance for enemies to spawn at
    static int INIT_DISTANCE = 1000;

    //Number of enemies spawned in at the moment
    public static int NUM_ENEMIES = 0;
    // Radius of enemy to draw
    public static int RADIUS = 100;

    protected Player player;
    private EnemyType type;
    private Image img;
    private ActionList actions;
    private boolean dead;

    private int speed;
    private PolarCoords polar;


    public Enemy(EnemyType type, Image img, int speed, Player player,
                 double angle, ActionList actions) {
        NUM_ENEMIES++;

        this.type = type;
        this.img = img;

        this.actions = actions;

        this.dead = false;
        this.speed = speed;
        this.player = player;
        this.polar = new PolarCoords(INIT_DISTANCE, angle);
    }

    public boolean attack(ActionType type) {
        boolean result = actions.match(type);
        if (actions.completed()) {
            die();
        }
        return result;
    }

    public boolean isDead() {                                                     // is enemy dead?
        return dead;
    }

    private void die() {
        NUM_ENEMIES--;
        dead = true;
    }

    private void move(){
        polar = polar.deltaRadius(-speed);
        if (polar.getRadius() < 100) {
            player.die();
        }
    }

    public void update() {
        move();
    }

    public void touch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.setEnemy(this);
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if (player.getEnemy() == this) {
            paint.setColor(Color.BLUE);
        } else {
            paint.setColor(Color.BLACK);
        }
        canvas.drawCircle(Math.round(getPosition().getX()) ,
                                     Math.round(getPosition().getY() - (RADIUS/2)), RADIUS, paint);
    }

    public CartesianCoords getPosition() {
        return polar.toCatesian().addOff(new CartesianCoords(Main.width/2, Main.height/2));
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        return (pos.subOff(getPosition()).toPolar().getRadius() < RADIUS);
    }
}
