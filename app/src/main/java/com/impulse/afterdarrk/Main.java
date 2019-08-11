package com.impulse.afterdarrk;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.impulse.afterdarrk.Actions.Action;
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

        ActionButton fireButton = new ActionButton(player, new CartesianCoords(100, height - ActionButton.WIDTH - 50), ActionType.FIRE);
        ActionButton iceButton = new ActionButton(player, new CartesianCoords(200 + ActionButton.WIDTH, height - ActionButton.WIDTH - 50), ActionType.ICE);
        ActionButton lightningButton = new ActionButton(player, new CartesianCoords(300 + ActionButton.WIDTH * 2, height - ActionButton.WIDTH - 50), ActionType.LIGHTNING);

        // Init display obj
        display = new Display(this);
        display.addObj(player);
        display.addObj(fireButton);
        display.addObj(iceButton);
        display.addObj(lightningButton);

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
