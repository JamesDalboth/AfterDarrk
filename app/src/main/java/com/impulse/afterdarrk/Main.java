package com.impulse.afterdarrk;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.lang.Math.E;
import static java.lang.Math.round;
import static java.lang.StrictMath.max;

public class Main extends AppCompatActivity {
    static int width, height;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    List<Enemy> enemyList;
    Player player;
    double beginBlob;
    double beginHand;
    double beginAngel;
    Image darkBlobImg;
    Image shadowHandImg;
    Image blackAngelImg;
    private BlackAngelGenerator blackAngleGen;
    private DarkBlobGenerator darkBlobGen;
    private ShadowHandGenerator shadowHandGenerator;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Code
        DisplayMetrics displayMetrics = new DisplayMetrics(); //To get width and height from the screen on the device
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        Image playerImg = null;
        enemyList = new ArrayList<>();
        player = new Player(playerImg);

        display = new Display(this);
        display.addObj(player);
        setContentView(display);

        blackAngleGen = new BlackAngelGenerator(player);
        darkBlobGen = new DarkBlobGenerator(player);
        shadowHandGenerator = new ShadowHandGenerator(player);

        gameLoop();
    }

    void gameLoop() {
        System.out.println("H");
        while (player.isAlive()) {
            double startTime = System.currentTimeMillis();
            update();
            display.invalidate();
            double endTime = System.currentTimeMillis();
            try {
                Thread.sleep(max((long) ((1000/64) -  (endTime - startTime)), 0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        for (Iterator<Enemy> iterator = enemyList.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            if (enemy.isDead()) {
                iterator.remove();
                continue;
            }
            enemy.update();
        }

        Enemy baEnemy = blackAngleGen.generate();
        if (baEnemy != null) {
            display.addObj(baEnemy);
            enemyList.add(baEnemy);
        }

        Enemy dbEnemy = darkBlobGen.generate();
        if (dbEnemy != null) {
            display.addObj(dbEnemy);
            enemyList.add(dbEnemy);
        }

        Enemy shEnemy = shadowHandGenerator.generate();
        if (shEnemy != null) {
            display.addObj(shEnemy);
            enemyList.add(shEnemy);
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
