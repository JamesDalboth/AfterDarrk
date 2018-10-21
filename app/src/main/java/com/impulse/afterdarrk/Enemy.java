package com.impulse.afterdarrk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;

public abstract class Enemy extends DisplayObj implements View.OnTouchListener {
    private EnemyType type;
    private Image img;
    private List<ActionType> actions;
    private boolean dead;
    private int speed;
    static int initialDistance = 1000;
    private Point position;
    private double angle;
    private int distance;
    protected Player player;
    private int actions_track;
    private int radius = 100;

    public Enemy(EnemyType type, Image img, int speed, Player player,
                 double angle) {
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

    boolean attack(ActionType type) {                                           //attack enemy: Compare attack with next action in combo
        if(actions.get(actions_track) == type) {                                //If correct, remove action from list, and if no more actions
            if(actions.size() == actions_track + 1){
                die();
            }
            actions_track++;
            return true;
        }
        else {                                                                  // else if attack is not correct, return false
            actions_track = 0;
            return false;
        }
    }

    boolean isDead() {                                                     // is enemy dead?
        return dead;
    }

    private void die() {                                                        // enemy is dead
        dead = true;
    }

    private void move(){
        distance -= speed;
        System.out.println(distance);
        translatePosition();
        if (distance < 1) {
            player.die();
        }
    }

    private void translatePosition(){
        position.x = (int) round(distance*cos(angle) + Main.width/2);
        position.y = (int) round(distance*sin(angle) + Main.height/2);
    }

    public void update() {
        move();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.setEnemy(this);
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(position.x , position.y - (radius/2), 100, paint);
    }
}
