package com.impulse.afterdarrk;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Utils.CartesianCoords;

abstract public class DisplayObj {

    abstract public void draw(Canvas canvas);

    abstract public boolean isHit(CartesianCoords pos);

    abstract public void touch(View view, MotionEvent event);
}
