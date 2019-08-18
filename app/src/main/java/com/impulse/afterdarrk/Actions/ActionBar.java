package com.impulse.afterdarrk.Actions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Main;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class ActionBar extends DisplayObj {
    public static final int MARGIN = 50;

    public ActionBar(Player player, CartesianCoords position, DisplayObj parent) {
        super(position, parent);
        sortKey = 1;

        int buttonHeight = Main.height / 6;
        int width = Main.width / 10;

        CartesianCoords size = new CartesianCoords(width, buttonHeight);

        createActionButtons(player, width, size);
        createDirectionButtons(player, width, size);
    }

    private void createActionButtons(Player player, int width, CartesianCoords size) {
        ActionButton fireButton = new ActionButton(player, size, ActionType.FIRE, new CartesianCoords(width/2, MARGIN), this);
        ActionButton iceButton = new ActionButton(player, size, ActionType.ICE,  new CartesianCoords((width * 7)/4, MARGIN), this);
        ActionButton lightningButton = new ActionButton(player, size, ActionType.LIGHTNING, new CartesianCoords(width * 3, MARGIN), this);

        addObj(fireButton);
        addObj(iceButton);
        addObj(lightningButton);
    }

    private void createDirectionButtons(Player player, int width, CartesianCoords size) {
        DirectionButton leftButton = new DirectionButton(player, size, DirectionType.LEFT, new CartesianCoords(width * 7, MARGIN), this);
        DirectionButton rightButton = new DirectionButton(player, size, DirectionType.RIGHT, new CartesianCoords((width * 17)/2, MARGIN), this);


        addObj(leftButton);
        addObj(rightButton);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);

        Paint borderPaint = new Paint();
        borderPaint.setColor(Color.YELLOW);

        CartesianCoords position = getAbsolutePosition();

        int left = (int) Math.round(position.getX());
        int top = (int) Math.round(position.getY());

        Rect background = new Rect(left, top, Main.width, Main.height);
        Rect border = new Rect(left, top - 10, Main.width, top);

        canvas.drawRect(background, backgroundPaint);
        canvas.drawRect(border, borderPaint);
    }

    // Action bar is not touchable
    @Override
    public boolean touch(View view, MotionEvent event) {
        return false;
    }
}
