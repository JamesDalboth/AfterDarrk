package com.impulse.afterdarrk;

import android.content.Context;

public class DarkBlobGenerator extends EnemyGenerator {

    public DarkBlobGenerator(Player player) {
        this.player = player;
        img = null;
        prob = 0.01;
    }

    @Override
    protected Enemy makeEnemy() {
        double angle = Math.random() * Math.PI * 2;
        Enemy enemy = new DarkBlob(img, player, angle);
        return enemy;
    }

    @Override
    protected void recalculateNextProb() {
        prob += 0.01;
    }
}
