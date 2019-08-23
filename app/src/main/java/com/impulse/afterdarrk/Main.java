package com.impulse.afterdarrk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.impulse.afterdarrk.Actions.ActionBar;
import com.impulse.afterdarrk.Display.BitmapLoader;
import com.impulse.afterdarrk.Display.Display;
import com.impulse.afterdarrk.Display.FontLoader;
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
    private BlackAngelGenerator blackAngelGen;
    private DarkBlobGenerator darkBlobGen;
    private ShadowHandGenerator shadowHandGenerator;
    private List<Enemy> enemyList;

    private LevelRules refGuide;

    private Player player;

    // Display object
    private Display display;

    public static int width, height;
    public static CartesianCoords center;

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

        BitmapLoader.getInstance().load(this);

        FontLoader.getInstance().load(this);

        display = new Display(this);

        refGuide = new LevelRules();

        initPlayerObj();
        initButtons();
        initEnemyGenerators();

        setContentView(display);
    }

    private void initButtons() {
        ActionBar actionBar = new ActionBar(player, new CartesianCoords(0, height * 5 / 6 - ActionBar.MARGIN * 2),null);
        display.addObj(actionBar);
    }

    private void initEnemyGenerators() {
        blackAngelGen = new BlackAngelGenerator(player, refGuide);
        darkBlobGen = new DarkBlobGenerator(player, refGuide);
        shadowHandGenerator = new ShadowHandGenerator(player, refGuide);
    }

    private void initPlayerObj() {
        player = new Player(new CartesianCoords(width/2, height/2));
        display.addObj(player);
    }

    private void calcScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        center = new CartesianCoords(width/2, height/2);
        System.out.println("Center: " + center);
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
        shadowHandGenerator.generate(enemyList, display);
        blackAngelGen.generate(enemyList, display);
        darkBlobGen.generate(enemyList, display);
    }
}
