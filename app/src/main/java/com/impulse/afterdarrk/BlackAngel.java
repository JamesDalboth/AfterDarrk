package com.impulse.afterdarrk;

import android.content.Context;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

class BlackAngel extends Enemy{
    static int speed = 1;

    List<ActionType> actionTypeList = new ArrayList<ActionType>() {{ ///why not just new list instead of arraylist
        add(ActionType.FIRE);
        add(ActionType.FIRE);
        add(ActionType.ICE);
        add(ActionType.ICE);
    }};

    public BlackAngel(Image img, Player player, double angle) {
        super(EnemyType.ANGEL, img, speed, player, angle);
        setActions(actionTypeList);
    }
}
