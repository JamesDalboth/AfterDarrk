package com.impulse.afterdarrk.Enemy;

import android.support.annotation.NonNull;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;
import java.util.List;

public class ShadowHand extends Enemy {
    static int speed = 1;

    public ShadowHand(Player player, double angle, int size, DisplayObj parent) {
        super(speed, player, angle, createActions(), size, parent);
    }

    @NonNull
    private static List<ActionType> createActions() {
        return new ArrayList<ActionType>() {{
            add(ActionType.FIRE);
            add(ActionType.LIGHTNING);
            add(ActionType.LIGHTNING);
            add(ActionType.ICE);
        }};
    }
}
