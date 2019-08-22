package com.impulse.afterdarrk.Actions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Display.FontLoader;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;
import com.impulse.afterdarrk.Utils.CartesianCoords;

public class Score extends DisplayObj {
    private final Player player;
    private final CartesianCoords size;
    private Paint textScore;

    Score(Player player, CartesianCoords size, CartesianCoords position, DisplayObj parent){
        super(position, parent);
        this.player = player;
        this.size = size;
        textScore = FontLoader.getInstance().getCustomFont();
        textScore.setTextSize((float)(size.getY()*1.5));
    }
    @Override
    public void draw(Canvas canvas) {
        int left = (int) Math.round(getAbsolutePosition().getX());
        int top = (int) Math.round(getAbsolutePosition().getY());
        int height = (int) Math.round(size.getY());
        String scoreString = Integer.toString(Enemy.DEAD_ENEMIES);
        canvas.drawText(scoreString, left, top + height, textScore);
    }

    // Score is not touchable
    @Override
    public boolean touch(View view, MotionEvent event) {
        return false;
    }
}
