package com.impulse.afterdarrk;

import android.content.Context;

public class BlackAngelGenerator extends EnemyGenerator {

    public BlackAngelGenerator(Player player) {
        this.player = player;
        this.prob = 0.02;
    }

    @Override
    protected Enemy makeEnemy() {
        double angle = Math.random() * Math.PI * 2;
        return new BlackAngel(img, player, angle);
    }

    @Override
    protected void recalculateNextProb() {
        prob += 0.005;
    }
}
