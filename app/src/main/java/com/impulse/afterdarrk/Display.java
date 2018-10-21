package com.impulse.afterdarrk;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Display extends View {

    private List<DisplayObj> objs;

    public Display(Context context) {
        super(context);
        objs = new ArrayList<>();
    }

    public void addObj(DisplayObj obj) {
        objs.add(obj);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (DisplayObj obj : objs) {
            obj.draw(canvas);
        }
    }


}
