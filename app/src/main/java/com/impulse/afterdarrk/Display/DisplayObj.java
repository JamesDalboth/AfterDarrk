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

    protected List<DisplayObj> objs = new ArrayList<>();
    private CartesianCoords position;

    protected void addObj(DisplayObj obj) {
        objs.add(obj);
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
