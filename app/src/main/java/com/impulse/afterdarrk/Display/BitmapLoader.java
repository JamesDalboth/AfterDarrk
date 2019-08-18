package com.impulse.afterdarrk.Display;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.impulse.afterdarrk.R;

public class BitmapLoader {

    private Bitmap fireBitmap;
    private Bitmap iceBitmap;
    private Bitmap lightningBitmap;

    private Bitmap leftBitmap;
    private Bitmap rightBitmap;

    private static final BitmapLoader instance = new BitmapLoader();

    private BitmapLoader() {};

    public static BitmapLoader getInstance() {
        return instance;
    }

    public void load(Context context) {
        fireBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire);
        iceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ice);
        lightningBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightning);

        leftBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.left);
        rightBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.right);
    }

    public Bitmap getFireBitmap() {
        return fireBitmap;
    }

    public Bitmap getIceBitmap() {
        return iceBitmap;
    }

    public Bitmap getLightningBitmap() {
        return lightningBitmap;
    }

    public Bitmap getLeftBitmap() {
        return leftBitmap;
    }

    public Bitmap getRightBitmap() {
        return rightBitmap;
    }
}
