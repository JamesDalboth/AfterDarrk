package com.impulse.afterdarrk;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.impulse.afterdarrk.Actions.ActionBar;
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
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    // Generators for different enemies
    private BlackAngelGenerator blackAngleGen;
    private DarkBlobGenerator darkBlobGen;
    private ShadowHandGenerator shadowHandGenerator;
    private List<Enemy> enemyList;

    private Player player;

    // Display object
    private Display display;

    public static int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Code
        // To get width and height from the screen on the device
        calcScreenSize();

        init();

        // Run game
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameLoop();
            }
        }, 10, 10);
    }

    private void init() {
        enemyList = new ArrayList<>();

        display = new Display(this);

        initPlayerObj();
        initButtons();
        initEnemyGenerators();

        setContentView(display);
    }

    private void initButtons() {
        ActionBar actionBar = new ActionBar(player, this);
        display.addObj(actionBar);
    }

    private void initEnemyGenerators() {
        blackAngleGen = new BlackAngelGenerator(player, this);
        darkBlobGen = new DarkBlobGenerator(player, this);
        shadowHandGenerator = new ShadowHandGenerator(player, this);
    }

    private void initPlayerObj() {
        int playerSize = width/20;

        Image playerImg = null;
        player = new Player(playerImg, playerSize);
        display.addObj(player);
    }

    private void calcScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
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
                display.removeObj(enemy);
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
