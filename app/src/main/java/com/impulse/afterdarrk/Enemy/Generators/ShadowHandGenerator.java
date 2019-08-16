package com.impulse.afterdarrk.Enemy.Generators;

import android.content.Context;

import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Enemy.ShadowHand;
import com.impulse.afterdarrk.Player;

public class ShadowHandGenerator extends EnemyGenerator {

    public ShadowHandGenerator(Player player, Context context) {
        this.player = player;
        this.context = context;
    }

    @Override
    protected double getProb() {
        return Math.log(enemies_generated + 2) * (5 - Enemy.NUM_ENEMIES) / 5;
    }

    @Override
    protected Enemy makeEnemy(int size) {
        System.out.println("Create shadow hand");
        double angle = Math.random() * Math.PI * 2;
        return new ShadowHand(img, player, angle, size, context);
    }
}
