package com.impulse.afterdarrk;

import android.content.Context;
import android.media.Image;

public abstract class EnemyGenerator {
    protected Image img;
    protected Player player;
    protected double prob;

    public Enemy generate() {
        double n = Math.random();
        if (n < prob) {
            recalculateNextProb();
            return makeEnemy();
        } else {
            return null;
        }
    }

    protected abstract Enemy makeEnemy();

    protected abstract void recalculateNextProb();
}
