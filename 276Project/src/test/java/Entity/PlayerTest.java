package Entity;

import Game.GameConsole;
import Game.KeyHandler;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    GameConsole gc;
    KeyHandler kh;
    Player p;

    @Before
    public void init(){
        gc = new GameConsole();
        kh = new KeyHandler(gc);
        p = new Player(gc,kh);
        gc.setupGame();
    }

    @Test
    public void createPlayer(){
        Player pt = new Player(gc, kh);
        assert(pt instanceof Player);
    }
    @Test
    public void updateT(){
        p.direction = "down";
        p.update();
        assertEquals(p.direction, "down");

        p.direction = "up";
        p.update();
        assertEquals(p.direction, "up");

        p.direction = "left";
        p.update();
        assertEquals(p.direction, "left");

        p.direction = "right";
        p.update();
        assertEquals(p.direction, "right");

        p.worldPosX = 0;
        p.worldPosY = 0;
        p.update();
        assertEquals(p.isInvincible, false);

        // Reset to default position
        p.worldPosX = p.tileSize * 25;
        p.worldPosY = p.tileSize * 21;
        p.spriteCount = 13;
        p.update();
        assertEquals(p.spriteNum, 1);

        p.spriteCount = 13;
        p.spriteNum = 2;
        p.update();
        assertEquals(p.spriteNum, 2);

    }

    @Test
    public void drawT(){
        p.direction = "left";
        p.spriteNum = 1;
        p.draw(p.leftStatic.createGraphics());
        p.spriteNum = 2;
        p.draw(p.leftWalk.createGraphics());

        p.direction = "right";
        p.spriteNum = 1;
        p.draw(p.rightStatic.createGraphics());
        p.spriteNum = 2;
        p.draw(p.rightWalk.createGraphics());


        gc.player.worldPosX = 10000;
        p.worldPosX = 10000;
        p.draw(p.leftWalk.createGraphics());

    }

    @Test
    public void defaultPositionCheck(){
        p.setDefaultPosition();
        assert (p.worldPosX == p.tileSize * 16) &&
         (p.worldPosY == p.tileSize * 30) &&
         (p.speed == 4) && (p.direction == "up");

    }

    @Test
    public void restoreCheck(){
        p.restorePlayer();
        assert (p.normalGold == 0) &&
                (p.score == 0) &&
                (p.isInvincible == false) &&
                (p.collisionOn == false);
    }

    @Test
    public void normalGoldCheck(){
        int n = 0;
        assertEquals(p.normalGold, n);

    }

    @Test
    public void scoreCheck(){
        int s = 0;
        assertEquals(p.score, s);

    }

}
