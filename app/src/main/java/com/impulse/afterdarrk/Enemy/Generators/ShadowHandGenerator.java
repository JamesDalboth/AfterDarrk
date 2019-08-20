package com.impulse.afterdarrk.Enemy.Generators;

import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Enemy.ShadowHand;
import com.impulse.afterdarrk.Player;

public class ShadowHandGenerator extends EnemyGenerator {

    public ShadowHandGenerator(Player player) {
        this.player = player;
    }

    @Override
    protected double getProb() {
        return Math.log(enemies_generated + 2) * (5 - Enemy.NUM_ENEMIES) / 5;
    }

    @Override
    protected Enemy makeEnemy(int size) {
        double direction = Math.random();
        double angle;
        if (direction > 0.5) {
            angle = Math.random() * (((Math.PI * 19)/16) - (Math.PI * 55)/64) + (Math.PI * 55)/64;
        } else{
            angle = Math.random() * (((Math.PI * 133)/64) - (Math.PI * 29)/16) + (Math.PI * 29)/16;
        }
        return new ShadowHand(player, angle, size, null);
    }
}
