package com.impulse.afterdarrk;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private final Player player = new Player(null);

    @Test
    public void isAliveWhenCreated() {
        assertTrue(player.isAlive());
    }

    @Test
    public void isDeadWhenKilled() {
        player.die();
        assertFalse(player.isAlive());
    }
}