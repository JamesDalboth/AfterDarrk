package com.impulse.afterdarrk.Actions;

import android.content.Context;
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
    private List<DirectionButton> directionButtons;

    private final int top;
    private final int margin;

    public ActionBar(Player player, Context context) {
        sortKey = 1;

        int buttonHeight = Main.height / 6;

        margin = buttonHeight / 6;
        top = Main.height - buttonHeight - margin * 2;
        int width = Main.width / 10;

        System.out.println("Button height: " + buttonHeight);
        System.out.println("Margin: " + margin);
        System.out.println("Top: " + top);
        System.out.println("Main.Height: " + Main.height);

        CartesianCoords size = new CartesianCoords(width, buttonHeight);

        createActionButtons(player, context, width, size);
        createDirectionButtons(player, context, width, size);
    }

    private void createActionButtons(Player player, Context activityContext, int width, CartesianCoords size) {
        int buttonTop = top + margin;

        ActionButton fireButton = new ActionButton(player, new CartesianCoords(width/2, buttonTop), size, ActionType.FIRE, activityContext);
        ActionButton iceButton = new ActionButton(player, new CartesianCoords((width * 7)/4, buttonTop), size, ActionType.ICE, activityContext);
        ActionButton lightningButton = new ActionButton(player, new CartesianCoords(width * 3, buttonTop), size, ActionType.LIGHTNING, activityContext);

        actionButtons = new ArrayList<>();

        actionButtons.add(fireButton);
        actionButtons.add(iceButton);
        actionButtons.add(lightningButton);
    }

    private void createDirectionButtons(Player player, Context activityContext, int width, CartesianCoords size) {
        int buttonTop = top + margin;

        DirectionButton leftButton = new DirectionButton(player, new CartesianCoords(width * 7, buttonTop), size, DirectionType.LEFT, activityContext);
        DirectionButton rightButton = new DirectionButton(player, new CartesianCoords((width * 17)/2, buttonTop), size, DirectionType.RIGHT, activityContext);

        directionButtons = new ArrayList<>();

        directionButtons.add(leftButton);
        directionButtons.add(rightButton);
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

        for (ActionButton actionButton : actionButtons) {
            actionButton.draw(canvas);
        }

        for (DirectionButton directionButton : directionButtons) {
            directionButton.draw(canvas);
        }
    }

    @Override
    public boolean touch(View view, MotionEvent event) {
        for (ActionButton actionButton : actionButtons) {
            if (actionButton.touch(view, event)) {
                return true;
            }
        }

        return false;
    }
}
