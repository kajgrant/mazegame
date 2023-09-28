package Entity;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import Game.GameConsole;

public class MonsterTest {

    private GameConsole testConsole;
    private Monster staticMonster;
    private Monster moveMonster;

    @Before
    public void init() {
        testConsole = new GameConsole();
        testConsole.ui.muteCheck = true;
        staticMonster = new Monster(testConsole, false);
        moveMonster = new Monster(testConsole, true);
        testConsole.setupGame();
    }

    @Test
    public void defaultCheck() {
        assertEquals(moveMonster.speed, 0);
        assertEquals(moveMonster.worldPosX, moveMonster.tileSize * 25);
        assertEquals(moveMonster.isMove, true);

        assertEquals(staticMonster.speed, 0);
        assertEquals(staticMonster.worldPosX, staticMonster.tileSize * 25);
        assertEquals(staticMonster.isMove, false);
    }

    @Test
    public void updateCheck() {
        moveMonster.update();
        assertEquals(moveMonster.direction, "down");

        moveMonster.direction = "up";
        moveMonster.update();
        assertEquals(moveMonster.direction, "up");

        moveMonster.direction = "left";
        moveMonster.update();
        assertEquals(moveMonster.direction, "left");

        moveMonster.direction = "right";
        moveMonster.update();
        assertEquals(moveMonster.direction, "right");

        moveMonster.worldPosX = 0;
        moveMonster.worldPosY = 0;
        moveMonster.update();
        assertEquals(moveMonster.collisionOn, true);

        // Reset to default position
        moveMonster.worldPosX = moveMonster.tileSize * 25;
        moveMonster.worldPosY = moveMonster.tileSize * 21;
        moveMonster.spriteCount = 13;
        moveMonster.update();
        assertEquals(moveMonster.spriteNum, 2);

        moveMonster.spriteCount = 13;
        moveMonster.update();
        assertEquals(moveMonster.spriteNum, 1);

        staticMonster.update();
        staticMonster.spriteCount = 13;
        staticMonster.update();
        assertEquals(staticMonster.spriteNum, 2);
    }

    @Test
    public void testDraw() {
        // Draw on screen
        moveMonster.direction = "left";
        moveMonster.spriteNum = 1;
        moveMonster.draw(moveMonster.leftStatic.createGraphics());
        moveMonster.spriteNum = 2;
        moveMonster.draw(moveMonster.leftWalk.createGraphics());

        moveMonster.direction = "right";
        moveMonster.spriteNum = 1;
        moveMonster.draw(moveMonster.rightStatic.createGraphics());
        moveMonster.spriteNum = 2;
        moveMonster.draw(moveMonster.rightWalk.createGraphics());

        // Draw off screen
        testConsole.player.worldPosX = 10000;
        testConsole.player.worldPosX = 10000;
        moveMonster.draw(moveMonster.leftWalk.createGraphics());
    }

}
