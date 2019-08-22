package com.impulse.afterdarrk.Display;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.impulse.afterdarrk.R;

public class FontLoader {
    private Paint customFont;

    private static final FontLoader instance = new FontLoader();

    private FontLoader() {}

    public static FontLoader getInstance() {
        return instance;
    }

    public void load(Context context) {
        Typeface tf =Typeface.createFromAsset(context.getAssets(),"fonts/score.ttf");
        customFont = new Paint();
        customFont.setTypeface(tf);
        customFont.setColor(Color.WHITE);
    }

    public Paint getCustomFont() {
        return customFont;
    }


}
