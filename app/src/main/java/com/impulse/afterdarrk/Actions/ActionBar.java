package com.impulse.afterdarrk.Actions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

import java.util.ArrayList;
import java.util.List;

public class ActionBar extends DisplayObj {

    private List<ActionButton> actionButtons;

    private final int top;
    private final int height;

    public ActionBar(Player player) {
        height = Main.height / 6;

        top = Main.height - (height/5)*6;

        System.out.println(top);

        int width = Main.width / 10;

        CartesianCoords size = new CartesianCoords(width, height);

        ActionButton fireButton = new ActionButton(player, new CartesianCoords(width/2, top), size, ActionType.FIRE);
        ActionButton iceButton = new ActionButton(player, new CartesianCoords((width * 7)/4, top), size, ActionType.ICE);
        ActionButton lightningButton = new ActionButton(player, new CartesianCoords(width * 3, top), size, ActionType.LIGHTNING);

        actionButtons = new ArrayList<>();

        actionButtons.add(fireButton);
        actionButtons.add(iceButton);
        actionButtons.add(lightningButton);
    }

    @Override
    public void draw(Canvas canvas) {
    /*    Rect rect = new Rect(Main.width/7, top, (Main.width/7)*6, height + top);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawRect(rect, paint);*/

        for (ActionButton actionButton : actionButtons) {
            actionButton.draw(canvas);
        }
    }

    @Override
    public boolean isHit(CartesianCoords pos) {
        if (pos.getY() < top) {
            return false;
        }

        for (ActionButton actionButton : actionButtons) {
            if (actionButton.isHit(pos)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void touch(View view, MotionEvent event) {
        for (ActionButton actionButton : actionButtons) {
            if (actionButton.isHit(new CartesianCoords(event.getX(), event.getY()))) {
                actionButton.touch(view, event);
                return;
            }
        }
    }
}
