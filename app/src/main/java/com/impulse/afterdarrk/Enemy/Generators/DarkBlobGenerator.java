package com.impulse.afterdarrk.Enemy.Generators;

import com.impulse.afterdarrk.Enemy.DarkBlob;
import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Player;

public class DarkBlobGenerator extends EnemyGenerator {

    public DarkBlobGenerator(Player player) {
        this.player = player;
    }

    @Override
    protected double getProb() {
        int boost = 1;
        if(Enemy.DEAD_ENEMIES != 0){
            boost = Enemy.DEAD_ENEMIES;
        }
        return (0.00001/(double)(Enemy.NUM_ENEMIES))*boost;
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
        if (!Enemy.BLOB_ALIVE) {
            Enemy.BLOB_ALIVE = true;
            return new DarkBlob(player, angle, size, null);
        }
        return null;
    }

}
