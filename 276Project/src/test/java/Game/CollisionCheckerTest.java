package Game;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import Entity.Player;
import Object.Gold;

public class CollisionCheckerTest {

    private GameConsole testConsole;
    private Player testPlayer;
    private Gold testGold;
    private KeyHandler kh;
    private CollisionChecker cCheck;

    @Before
    public void init() {
        testConsole = new GameConsole();
        testConsole.ui.muteCheck = true;
        kh = new KeyHandler(testConsole);
        testPlayer = new Player(testConsole, kh);
        testGold = new Gold();
        cCheck = new CollisionChecker(testConsole);
        testConsole.setupGame();
    }

    @Test
    public void testPlayerUnabletoMove() {
        // Put the player in the wall
        testPlayer.worldPosX = ScreenSettings.tileSize() * 25;
        testPlayer.worldPosY = ScreenSettings.tileSize() * 7;
        ArrayList<String> possibleMoves = cCheck.getPossibleMoves(testPlayer);
        assertEquals(possibleMoves.size(), 0);
    }

    @Test
    public void testPlayerWallCheck() {
        Boolean testResult;

        // Put the player in the wall
        testPlayer.worldPosX = ScreenSettings.tileSize() * 25;
        testPlayer.worldPosY = ScreenSettings.tileSize() * 7;

        testResult = cCheck.detectWallCollision(testPlayer, "up");
        assertEquals(testResult, true);

        testResult = cCheck.detectWallCollision(testPlayer, "down");
        assertEquals(testResult, true);

        testResult = cCheck.detectWallCollision(testPlayer, "left");
        assertEquals(testResult, true);

        testResult = cCheck.detectWallCollision(testPlayer, "right");
        assertEquals(testResult, true);
    }

    @Test
    public void testPlayerGold() {
        Boolean testResult;

        // Set gold to the left of the player
        testPlayer.worldPosX = ScreenSettings.tileSize() * 21;
        testPlayer.worldPosY = ScreenSettings.tileSize() * 26;
        testPlayer.direction = "left";
        testGold.worldX = ScreenSettings.tileSize() * 21 - (ScreenSettings.tileSize() / 2);
        testGold.worldY = ScreenSettings.tileSize() * 26;
        CollisionInstance testCollision1 = new CollisionInstance(testGold);
        testResult = cCheck.checkInstanceCollision(testPlayer, testCollision1);
        assertEquals(testResult, true);

        // Set gold to the right of the player
        testPlayer.direction = "right";
        testGold.worldX = ScreenSettings.tileSize() * 21 + (ScreenSettings.tileSize() / 2);
        CollisionInstance testCollision2 = new CollisionInstance(testGold);
        testResult = cCheck.checkInstanceCollision(testPlayer, testCollision2);
        assertEquals(testResult, true);

        // Set gold above the player
        testPlayer.direction = "up";
        testGold.worldX = ScreenSettings.tileSize() * 21;
        testGold.worldY = ScreenSettings.tileSize() * 26 - (ScreenSettings.tileSize() / 2);
        CollisionInstance testCollision3 = new CollisionInstance(testGold);
        testResult = cCheck.checkInstanceCollision(testPlayer, testCollision3);
        assertEquals(testResult, true);

        // Set gold below the player
        testPlayer.direction = "down";
        testGold.worldY = ScreenSettings.tileSize() * 26 + (ScreenSettings.tileSize() / 2);
        CollisionInstance testCollision4 = new CollisionInstance(testGold);
        testResult = cCheck.checkInstanceCollision(testPlayer, testCollision4);
        assertEquals(testResult, true);

    }

}
