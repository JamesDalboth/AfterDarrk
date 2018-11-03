package com.impulse.afterdarrk.Enemy;

import android.media.Image;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackAngel extends Enemy {
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
