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
        double boost = 1;
        if(Enemy.DEAD_ENEMIES != 0){
            boost = Enemy.DEAD_ENEMIES;
        }
        return (0.000001/((double)(Enemy.NUM_ENEMIES)))*boost;
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
        return new BlackAngel(player, angle, size, null);
    }
}
