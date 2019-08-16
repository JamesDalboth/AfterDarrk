package com.impulse.afterdarrk.Enemy.Generators;

import android.content.Context;

import com.impulse.afterdarrk.Enemy.DarkBlob;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;

public class DarkBlobGenerator extends EnemyGenerator {

    public DarkBlobGenerator(Player player, Context context) {
        this.player = player;
        img = null;
        this.context = context;
    }

    @Override
    protected double getProb() {
        return Math.log(enemies_generated + 2) * (5 - Enemy.NUM_ENEMIES) / 5;
    }

    @Override
    protected Enemy makeEnemy(int size) {
        System.out.println("Create Dark Blob");
        double angle = Math.random() * Math.PI * 2;
        Enemy enemy = new DarkBlob(img, player, angle, size, context);
        return enemy;
    }

}
