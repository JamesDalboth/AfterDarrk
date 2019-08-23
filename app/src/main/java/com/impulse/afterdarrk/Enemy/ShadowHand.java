package com.impulse.afterdarrk.Enemy;

import android.support.annotation.NonNull;

import com.impulse.afterdarrk.Actions.ActionType;
import com.impulse.afterdarrk.Display.DisplayObj;
import com.impulse.afterdarrk.LevelRules;
import com.impulse.afterdarrk.Player;

import java.util.ArrayList;
import java.util.List;

public class ShadowHand extends Enemy {

    public ShadowHand(Player player, double angle, int size, DisplayObj parent, LevelRules refGuide) {
        super(refGuide.getShadowSpeed(Enemy.DEAD_ENEMIES), player, angle, createActions(refGuide), size, parent);
    }

    @NonNull
    private static List<ActionType> createActions(LevelRules refGuide) {
        final ArrayList<ActionType> choices = refGuide.getLevelActions(Enemy.DEAD_ENEMIES);
        final int numActions = refGuide.shadowComboTotal(Enemy.DEAD_ENEMIES);
        return new ArrayList<ActionType>() {{
            for(int i = 0; i < numActions; i++){
                int select = (int)(Math.random() * choices.size());
                add(choices.get(select));
            }
        }};
    }
}
