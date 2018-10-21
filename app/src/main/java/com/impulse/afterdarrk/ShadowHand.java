package com.impulse.afterdarrk;

import android.content.Context;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

class ShadowHand extends Enemy {
    static int speed = 1;

    List<ActionType> actionTypeList = new ArrayList<ActionType>() {{ ///why not just new list instead of arraylist
        add(ActionType.FIRE);
        add(ActionType.LIGHTNING);
        add(ActionType.LIGHTNING);
        add(ActionType.ICE);
    }};

    public ShadowHand(Image img, Player player, double angle) {
        super(EnemyType.HAND, img, speed, player, angle);
        setActions(actionTypeList);
    }
}
