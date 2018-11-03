package com.impulse.afterdarrk;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Enemy.Generators.BlackAngelGenerator;
import com.impulse.afterdarrk.Enemy.Generators.DarkBlobGenerator;
import com.impulse.afterdarrk.Enemy.Generators.ShadowHandGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {
    public static int width, height; // Width and height of screen

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    List<Enemy> enemyList; // List of enemies alive

    Player player; // Player control object

    // Generators for different enemies
    private BlackAngelGenerator blackAngleGen;
    private DarkBlobGenerator darkBlobGen;
    private ShadowHandGenerator shadowHandGenerator;

    // Display object
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Code
        //To get width and height from the screen on the device
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        // Init player obj
        Image playerImg = null;
        player = new Player(playerImg);

        // Init enemy list
        enemyList = new ArrayList<>();

        // Init dislay obj
        display = new Display(this);
        display.addObj(player);
        setContentView(display);

        // Init enemy generators
        blackAngleGen = new BlackAngelGenerator(player);
        darkBlobGen = new DarkBlobGenerator(player);
        shadowHandGenerator = new ShadowHandGenerator(player);

        // Run game
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameLoop();
            }
        }, 10, 10);
    }

    void gameLoop() {
        if (!player.isAlive()) {
            System.exit(0);
        }
        update();
        display.invalidate();

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

        // Generate enemies
        blackAngleGen.generate(enemyList, display);
        darkBlobGen.generate(enemyList, display);
        shadowHandGenerator.generate(enemyList, display);
    }
}
