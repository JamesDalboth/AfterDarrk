package com.impulse.afterdarrk.Display;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Utils.InsertionSort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Display extends View implements View.OnTouchListener {

    private List<DisplayObj> objs;

    public Display(Context context) {
        super(context);
        objs = new ArrayList<>();
        setOnTouchListener(this);
    }

    public void addObj(DisplayObj obj) {
        objs.add(obj);
    }

    public void removeObj(DisplayObj obj) {
        objs.remove(obj);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        InsertionSort<DisplayObj> insertionSort = new InsertionSort<>();

        insertionSort.insertionSort(objs);

        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();
            obj.drawObject(canvas);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        // Need to use iterator as by touching something we could removeObj something from this list.
        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();

            if (obj.touchObject(v, event)) {
                return true;
            }
        }

        return false;
    }
}
