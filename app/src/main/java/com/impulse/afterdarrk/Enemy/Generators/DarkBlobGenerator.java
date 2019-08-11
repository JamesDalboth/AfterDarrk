package com.impulse.afterdarrk.Enemy.Generators;

import com.impulse.afterdarrk.Enemy.DarkBlob;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;

public class DarkBlobGenerator extends EnemyGenerator {

    public DarkBlobGenerator(Player player) {
        this.player = player;
        img = null;
    }

    @Override
    protected double getProb() {
        return Math.log(enemies_generated + 2) * (5 - Enemy.NUM_ENEMIES) / 5;
    }

    @Override
    protected Enemy makeEnemy(int size) {
        double angle = Math.random() * Math.PI * 2;
        Enemy enemy = new DarkBlob(img, player, angle, size);
        return enemy;
    }

}
