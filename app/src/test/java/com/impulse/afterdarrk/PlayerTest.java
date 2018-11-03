package com.impulse.afterdarrk;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerTest {
    final Player player = new Player(null);

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