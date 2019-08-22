package com.impulse.afterdarrk.Enemy;

import android.support.annotation.NonNull;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackAngel extends Enemy {
    static double speed = 0.5;

    public BlackAngel(Player player, double angle, int size, DisplayObj parent) {
        super(speed, player, angle, createActions(), size, parent);
    }

    @NonNull
    private static List<ActionType> createActions() {
        final int fireChoice = 0;
        final int iceChoice = 1;
        return new ArrayList<ActionType>() {{
            for(int i = 0; i < 5; i++){
                int choice = (int)(Math.random() * 3);
                if(choice == fireChoice) {
                    add(ActionType.FIRE);
                } else if(choice == iceChoice) {
                    add(ActionType.ICE);
                } else {
                    add(ActionType.LIGHTNING);
                }
            }
        }};
    }
}
