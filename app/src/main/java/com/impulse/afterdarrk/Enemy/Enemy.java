package com.impulse.afterdarrk.Enemy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.R;
import com.impulse.afterdarrk.Utils.CartesianCoords;
import com.impulse.afterdarrk.Utils.PolarCoords;

import java.util.List;

public abstract class Enemy extends DisplayObj {
    private static int INIT_DISTANCE = 1000;
    private static int ACTION_WIDTH = 50;

    public static int NUM_ENEMIES = 0;

    private EnemyType type;
    private Image img;
    private ActionList actions;
    private boolean dead;
    private final int radius;
    private int speed;
    private PolarCoords polar;
    private final Context context;

    private Bitmap fireBitmap;
    private Bitmap iceBitmap;
    private Bitmap lightningBitmap;

    protected Player player;

    public Enemy(EnemyType type, Image img, int speed, Player player,
                 double angle, ActionList actions, int size, Context context) {
        NUM_ENEMIES++;

        this.type = type;
        this.img = img;
        this.actions = actions;
        this.dead = false;
        this.speed = speed;
        this.player = player;
        this.polar = new PolarCoords(INIT_DISTANCE, angle);
        this.radius = size;
        this.context = context;

        initButtonGraphics(context);
    }

    private void initButtonGraphics(Context context) {
        fireBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire);
        iceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ice);
        lightningBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightning);

        fireBitmap = Bitmap.createScaledBitmap(fireBitmap, ACTION_WIDTH, ACTION_WIDTH, true);
        iceBitmap = Bitmap.createScaledBitmap(iceBitmap, ACTION_WIDTH, ACTION_WIDTH, true);
        lightningBitmap = Bitmap.createScaledBitmap(lightningBitmap, ACTION_WIDTH, ACTION_WIDTH, true);
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

    private CartesianCoords getPosition() {
        return polar.toCartesian().addOff(new CartesianCoords(Main.width/2, Main.height/2));
    }

    public boolean touch(View v, MotionEvent event) {
        CartesianCoords pos = new CartesianCoords(event.getX(), event.getY());

        if (pos.subOff(getPosition()).toPolar().getRadius() < radius) {
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

        canvas.drawCircle(Math.round(getPosition().getX()),
                Math.round(getPosition().getY() - (radius /2)), radius, paint);

        drawActions(canvas);
    }

    private void drawActions(Canvas canvas) {
        List<ActionType> remaining = actions.remaining();

        double width = remaining.size() * ACTION_WIDTH + (remaining.size () - 1) * ACTION_WIDTH/2;
        double left = getPosition().getX() - width/2;

        int rectTop = (int) Math.floor(getPosition().getY()) - radius - ACTION_WIDTH * 2;

        for (int i = 0; i < remaining.size(); i++) {
            int rectLeft = (int) Math.round(left + (i * ACTION_WIDTH * 3/2));

            Rect actionRect = new Rect(rectLeft, rectTop, rectLeft + ACTION_WIDTH, rectTop + ACTION_WIDTH);

            switch (remaining.get(i)) {
                case ICE:
                    canvas.drawBitmap(iceBitmap, null, actionRect, null);
                    break;
                case FIRE:
                    canvas.drawBitmap(fireBitmap, null, actionRect, null);
                    break;
                case LIGHTNING:
                    canvas.drawBitmap(lightningBitmap, null, actionRect, null);
                    break;
            }
        }
    }
}
