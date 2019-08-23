package com.impulse.afterdarrk.Enemy.Generators;

import com.impulse.afterdarrk.Display.Display;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.LevelRules;
import com.impulse.afterdarrk.Player;

import java.util.List;

public abstract class EnemyGenerator {
    protected Player player;
    protected LevelRules refGuide;

    int enemies_generated;

    public void generate(List<Enemy> enemyList, Display display) {
        double n = Math.random()*7*Enemy.NUM_ENEMIES;
        if (n < getProb()) {
            Enemy enemy = makeEnemy(player.radius);
            if(enemy != null) {
                enemies_generated++;
                display.addObj(enemy);
                enemyList.add(enemy);
            }
        }
    }

    protected abstract double getProb();

    protected abstract Enemy makeEnemy(int size);
}
