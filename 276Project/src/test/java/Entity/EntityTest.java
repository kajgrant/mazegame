package Entity;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import Game.GameConsole;

public class EntityTest {

    private GameConsole testConsole;
    private Entity testEntity;

    @Before
    public void init() {
        testConsole = new GameConsole();
        testConsole.ui.muteCheck = true;
        testEntity = new Entity(testConsole);
        testConsole.setupGame();
    }

    @Test
    public void checkCalculateDrawPos() {
        int[] testxy;
        int[] expected1 = { -408, -960 };
        int[] expected2 = { 0, 0 };
        int[] expected3 = { -768, 0 };

        testxy = testEntity.calculateDrawPosition();
        assertArrayEquals(expected1, testxy);

        testConsole.player.worldPosX = 0;
        testConsole.player.worldPosY = 0;
        testxy = testEntity.calculateDrawPosition();
        assertArrayEquals(expected2, testxy);

        testConsole.player.worldPosX = 10000;
        testConsole.player.worldPosY = 0;
        testxy = testEntity.calculateDrawPosition();
        assertArrayEquals(expected3, testxy);
    }

}
