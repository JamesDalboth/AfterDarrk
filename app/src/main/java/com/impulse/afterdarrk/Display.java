package com.impulse.afterdarrk;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Utils.CartesianCoords;
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

    @Override
    public void draw(Canvas canvas) {
        InsertionSort<DisplayObj> insertionSort = new InsertionSort<>();

        insertionSort.insertionSort(objs);

        super.draw(canvas);
        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();
            obj.draw(canvas);
        }
    }

    public void remove(Enemy enemy) {
        System.out.println("Removing enemy");
        objs.remove(enemy);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        // Need to use iterator as by touching something we could remove something from this list.
        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();

            if (obj.touch(v, event)) {
                return true;
            }
        }

        return false;
    }
}
