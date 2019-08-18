package com.impulse.afterdarrk.Enemy.Generators;

import android.content.Context;
import android.media.Image;

import com.impulse.afterdarrk.Display.Display;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;

import java.util.List;

public abstract class EnemyGenerator {
    protected Context context;
    protected Player player;

    Image img;
    int enemies_generated;

    public void generate(List<Enemy> enemyList, Display display) {
        double n = Math.random();
        if (n < getProb()) {
            enemies_generated++;
            Enemy enemy = makeEnemy(player.radius);
            display.addObj(enemy);
            enemyList.add(enemy);
        }
    }

    protected abstract double getProb();

    protected abstract Enemy makeEnemy(int size);
}
