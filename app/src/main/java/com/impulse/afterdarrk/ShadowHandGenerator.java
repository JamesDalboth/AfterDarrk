package com.impulse.afterdarrk;

import android.content.Context;

public class ShadowHandGenerator extends EnemyGenerator {

    public ShadowHandGenerator(Player player) {
        this.player = player;
        this.prob = 0.005;
    }

    @Override
    protected Enemy makeEnemy() {
        double angle = Math.random() * Math.PI * 2;
        return new ShadowHand(img, player, angle);
    }

    @Override
    protected void recalculateNextProb() {
        this.prob += 0.1;
    }
}
