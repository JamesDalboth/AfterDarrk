package com.impulse.afterdarrk;

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

    public void draw(Canvas canvas) {
        InsertionSort<DisplayObj> insertionSort = new InsertionSort<>();

        insertionSort.insertionSort(objs);

        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();
            obj.draw(canvas);
        }
    }

    public boolean touch(View view, MotionEvent event) {
        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();

            if (obj.touch(view, event)) {
                return true;
            }
        }

        return false;
    }
}
