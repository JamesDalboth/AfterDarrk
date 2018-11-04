package com.impulse.afterdarrk.Enemy;

import android.media.Image;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;

public class BlackAngel extends Enemy {
    static int speed = 1;

    public BlackAngel(Image img, Player player, double angle) {
        super(EnemyType.ANGEL, img, speed, player, angle, new ActionList(new ArrayList<ActionType>() {{
            add(ActionType.FIRE);
            add(ActionType.FIRE);
            add(ActionType.ICE);
            add(ActionType.ICE);
        }}));
    }
}
