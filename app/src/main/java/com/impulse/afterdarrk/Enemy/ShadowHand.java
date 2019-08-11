package com.impulse.afterdarrk.Enemy;

import android.media.Image;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;

public class ShadowHand extends Enemy {
    static int speed = 1;

    public ShadowHand(Image img, Player player, double angle, int size) {
        super(EnemyType.HAND, img, speed, player, angle, new ActionList(new ArrayList<ActionType>() {{
            add(ActionType.FIRE);
            add(ActionType.LIGHTNING);
            add(ActionType.LIGHTNING);
            add(ActionType.ICE);
        }}), size);
    }
}
