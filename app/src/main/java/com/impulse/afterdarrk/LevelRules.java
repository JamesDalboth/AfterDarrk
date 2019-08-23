package com.impulse.afterdarrk;

import com.impulse.afterdarrk.Actions.ActionList;
import com.impulse.afterdarrk.Actions.ActionType;

import java.util.ArrayList;

public class LevelRules {
    final private int levelOneMax = 5;
    final private int levelTwoMax = 15;
    final private int levelThreeMax = 30;
    final private int levelFourMax = 40;
    final private double initialShadowSpeed = 1;
    final private double initialBlobSpeed = 4;
    final private double initialAngelSpeed = 1;

    LevelRules() { }

    public double getBlobSpeed(int score){
        return initialBlobSpeed;
    }

    public double getAngelSpeed(int score){
        return initialAngelSpeed;
    }

    public double getShadowSpeed(int score){
        return initialShadowSpeed;
    }

    public ArrayList<ActionType> getLevelActions(int deadEnemies){
        final int score = deadEnemies;
        return new ArrayList<ActionType>() {{
            add(ActionType.FIRE);
            if(score > levelThreeMax){
                add(ActionType.LIGHTNING);
                add(ActionType.ICE);
            } else if(score > levelOneMax){
                add(ActionType.ICE);
            }
        }};
    }

    public int shadowComboTotal(int score){
        if(score > levelThreeMax){
            return 4;
        }
        return 3;
    }

    public int blobComboTotal(int score){
        if(score > levelFourMax){
            return 3;
        }
        return 2;
    }

    public int angelComboTotal(int score){
        if(score > levelFourMax){
            return 5;
        }
        return 4;
    }

    public double getBlobProbability(int score){
        if(score < levelTwoMax){
            return 0;
        } else if(score < levelThreeMax){
            return Math.random() * 0.01;
        }
        return Math.random() * 0.02;
    }

    public double getAngelProbability(int score){
        if(score < levelThreeMax){
            return Math.random() * 0.001;
        }
        return Math.random() * 0.002;
    }

    public double getShadowProbability(){
        return Math.random() * 0.1;
    }

}
