package com.impulse.afterdarrk;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.impulse.afterdarrk.Actions.Action;
import com.impulse.afterdarrk.Actions.ActionBar;
import com.impulse.afterdarrk.Actions.ActionButton;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Enemy.Generators.BlackAngelGenerator;
import com.impulse.afterdarrk.Enemy.Generators.DarkBlobGenerator;
import com.impulse.afterdarrk.Enemy.Generators.ShadowHandGenerator;
import com.impulse.afterdarrk.Utils.CartesianCoords;

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

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Code
        // To get width and height from the screen on the device
        calcScreenSize();

        context = this;

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
        int playerSize = width/20;

        initPlayerObj(playerSize);
        enemyList = new ArrayList<>();
        ActionBar actionBar = new ActionBar(player, context);
        initDisplayObj(actionBar);
        initEnemyGenerators();
    }

    private void initEnemyGenerators() {
        blackAngleGen = new BlackAngelGenerator(player);
        darkBlobGen = new DarkBlobGenerator(player);
        shadowHandGenerator = new ShadowHandGenerator(player);
    }

    private void initDisplayObj(ActionBar actionBar) {
        display = new Display(this);
        display.addObj(player);
        display.addObj(actionBar);
        setContentView(display);
    }

    private void initPlayerObj(int playerSize) {
        Image playerImg = null;
        player = new Player(playerImg, playerSize);
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
                display.remove(enemy);
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
