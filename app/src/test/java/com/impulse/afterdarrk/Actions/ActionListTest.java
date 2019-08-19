package com.impulse.afterdarrk.Actions;

import com.impulse.afterdarrk.Utils.CartesianCoords;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActionListTest {

    List<ActionType> actions = new ArrayList<ActionType>(){{
        add(ActionType.FIRE);
        add(ActionType.ICE);
        add(ActionType.LIGHTNING);
    }};

    CartesianCoords position = new CartesianCoords(0, 0);

    @Test
    public void correctSeriesOfMatchesCompletesList() {
        ActionList actionList = new ActionList(actions, position, null);

        actionList.match(ActionType.FIRE);
        actionList.match(ActionType.ICE);
        actionList.match(ActionType.LIGHTNING);

        assertTrue(actionList.completed());
    }

    @Test
    public void incorrectSeriesOfMatchesDoesNotCompleteList() {
        ActionList actionList = new ActionList(actions, position, null);

        actionList.match(ActionType.FIRE);
        actionList.match(ActionType.FIRE);
        actionList.match(ActionType.LIGHTNING);

        assertFalse(actionList.completed());
    }

    @Test
    public void mistakeResetsList() {
        ActionList actionList = new ActionList(actions, position, null);

        actionList.match(ActionType.FIRE);
        actionList.match(ActionType.ICE);
        actionList.match(ActionType.ICE);

        assertFalse(actionList.completed());

        actionList.match(ActionType.LIGHTNING);

        assertFalse(actionList.completed());

        actionList.match(ActionType.FIRE);
        actionList.match(ActionType.ICE);
        actionList.match(ActionType.LIGHTNING);

        assertTrue(actionList.completed());
    }
}