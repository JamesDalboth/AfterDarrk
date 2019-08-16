package com.impulse.afterdarrk.Enemy;

import android.content.Context;
import android.media.Image;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;

public class DarkBlob extends Enemy {

    static int speed = 1;

    public DarkBlob(Image img, Player player, double angle, int size, Context context) {
        super(EnemyType.BLOB, img, speed, player, angle, new ActionList(new ArrayList<ActionType>() {{
            add(ActionType.FIRE);
            add(ActionType.ICE);
        }}), size, context);
    }

}
