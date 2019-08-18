package com.impulse.afterdarrk.Actions;

import android.content.Context;
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
    private final int top;
    private final int margin;

    public ActionBar(Player player, Context context) {
        sortKey = 1;

        int buttonHeight = Main.height / 6;

        margin = buttonHeight / 6;
        top = Main.height - buttonHeight - margin * 2;
        int width = Main.width / 10;

        CartesianCoords size = new CartesianCoords(width, buttonHeight);

        createActionButtons(player, context, width, size);
        createDirectionButtons(player, context, width, size);
    }

    private void createActionButtons(Player player, Context activityContext, int width, CartesianCoords size) {
        int buttonTop = top + margin;

        ActionButton fireButton = new ActionButton(player, new CartesianCoords(width/2, buttonTop), size, ActionType.FIRE, activityContext);
        ActionButton iceButton = new ActionButton(player, new CartesianCoords((width * 7)/4, buttonTop), size, ActionType.ICE, activityContext);
        ActionButton lightningButton = new ActionButton(player, new CartesianCoords(width * 3, buttonTop), size, ActionType.LIGHTNING, activityContext);

        addObj(fireButton);
        addObj(iceButton);
        addObj(lightningButton);
    }

    private void createDirectionButtons(Player player, Context activityContext, int width, CartesianCoords size) {
        int buttonTop = top + margin;

        DirectionButton leftButton = new DirectionButton(player, new CartesianCoords(width * 7, buttonTop), size, DirectionType.LEFT, activityContext);
        DirectionButton rightButton = new DirectionButton(player, new CartesianCoords((width * 17)/2, buttonTop), size, DirectionType.RIGHT, activityContext);

        addObj(leftButton);
        addObj(rightButton);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);

        Paint borderPaint = new Paint();
        borderPaint.setColor(Color.YELLOW);

        Rect background = new Rect(0, top, Main.width, Main.height);
        Rect border = new Rect(0, top - 10, Main.width, top);

        canvas.drawRect(background, backgroundPaint);
        canvas.drawRect(border, borderPaint);
    }

    // Action bar is not touchable
    @Override
    public boolean touch(View view, MotionEvent event) {
        return false;
    }
}
