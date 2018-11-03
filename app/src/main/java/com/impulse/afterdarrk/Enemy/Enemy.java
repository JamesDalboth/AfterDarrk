package com.impulse.afterdarrk.Enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;


import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;

public abstract class Enemy extends DisplayObj implements View.OnTouchListener {
    static int initialDistance = 1000;

    public static int NUM_ENEMIES = 0;
    public static int RADIUS = 100;

    private EnemyType type;
    private Image img;
    private List<ActionType> actions;
    private boolean dead;
    private int speed;
    private Point position;
    private double angle;
    private int distance;
    protected Player player;
    private int actions_track;


    public Enemy(EnemyType type, Image img, int speed, Player player,
                 double angle) {
        NUM_ENEMIES++;
        this.type = type;
        this.img = img;
        this.actions = null;
        this.dead = false;
        this.distance = initialDistance;
        this.speed = speed;
        this.player = player;
        this.angle = angle;
        this.actions_track = 0;
        position = new Point(0,0);
        translatePosition();
    }

    public void setActions(List<ActionType> actions) {
        this.actions = actions;
    }

    public boolean attack(ActionType type) {
        if(actions.get(actions_track) == type) {
            if(actions.size() == actions_track + 1){
                die();
            }
            actions_track++;
            return true;
        } else {
            actions_track = 0;
            return false;
        }
    }

    public boolean isDead() {                                                     // is enemy dead?
        return dead;
    }

    private void die() {
        NUM_ENEMIES--;
        dead = true;
    }

    private void move(){
        distance -= speed;
        System.out.println(distance);
        translatePosition();
        if (distance < 100) {
            player.die();
        }
    }

    private void translatePosition(){
        position.x = (int) Math.round(distance*cos(angle) + Main.width/2);
        position.y = (int) round(distance*sin(angle) + Main.height/2);
    }

    public Point getPosition() {
        return position;
    }

    public void update() {
        move();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.setEnemy(this);
        }
        return false;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(position.x , position.y - (RADIUS/2), RADIUS, paint);
    }
}
