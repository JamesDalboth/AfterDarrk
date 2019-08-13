package com.impulse.afterdarrk.Enemy.Generators;

import com.impulse.afterdarrk.Enemy.BlackAngel;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;

public class BlackAngelGenerator extends EnemyGenerator {

    public BlackAngelGenerator(Player player) {
        this.player = player;
    }

    @Override
    protected double getProb() {
        return Math.log(enemies_generated + 2) * (5 - Enemy.NUM_ENEMIES) / 5;
    }

    @Override
    protected Enemy makeEnemy(int size) {
        double angle = Math.random() * Math.PI * 2;
        return new BlackAngel(img, player, angle, size);
    }
}
