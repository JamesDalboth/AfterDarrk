package com.impulse.afterdarrk.Display;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Utils.CartesianCoords;
import com.impulse.afterdarrk.Utils.InsertionSort;
import com.impulse.afterdarrk.Utils.Sortable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract public class DisplayObj extends Sortable {

    private final List<DisplayObj> objs;
    private CartesianCoords relativePosition;
    protected final DisplayObj parent;

    protected DisplayObj(CartesianCoords relativePosition, DisplayObj parent) {
        this.objs = new ArrayList<>();
        this.relativePosition = relativePosition;
        this.parent = parent;
    }

    protected CartesianCoords getRelativePosition() {
        return relativePosition;
    }

    protected void setRelativePosition(CartesianCoords relativePosition) {
        this.relativePosition = relativePosition;
    }

    protected CartesianCoords getAbsolutePosition() {
        CartesianCoords absolutePosition = relativePosition;

        if (parent != null) {
            absolutePosition = absolutePosition.addOff(parent.getAbsolutePosition());
        }

        return absolutePosition;
    }

    protected void addObj(DisplayObj obj) {
        objs.add(obj);
    }

    protected void removeObj(DisplayObj obj) {
        objs.remove(obj);
    }

    abstract public void draw(Canvas canvas);

    void drawObject(Canvas canvas) {
        draw(canvas);

        InsertionSort<DisplayObj> insertionSort = new InsertionSort<>();

        insertionSort.insertionSort(objs);

        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();
            obj.drawObject(canvas);
        }
    }

    abstract public boolean touch(View view, MotionEvent event);

    boolean touchObject(View view, MotionEvent event) {
        if (touch(view, event)) {
            return true;
        }

        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();

            if (obj.touchObject(view, event)) {
                return true;
            }
        }

        return false;
    }
}
