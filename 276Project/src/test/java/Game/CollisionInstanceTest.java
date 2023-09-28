package Game;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import Entity.Monster;
import Entity.Player;
import Object.Gold;
import Object.Diamond;

public class CollisionInstanceTest {

    private GameConsole testConsole;
    private Monster testMonster;
    private Player testPlayer;
    private Gold testGold;
    private Diamond testDiamond;
    private KeyHandler kh;

    @Before
    public void init() {
        testConsole = new GameConsole();
        testConsole.ui.muteCheck = true;
        kh = new KeyHandler(testConsole);
        testMonster = new Monster(testConsole, true);
        testPlayer = new Player(testConsole, kh);
        testGold = new Gold();
        testDiamond = new Diamond(testConsole);
        testConsole.setupGame();
    }

    @Test
    public void entityCheck() {
        CollisionInstance monsterCollision = new CollisionInstance(testMonster);
        assertEquals(testMonster.solidArea, monsterCollision.solidArea);
        assertEquals(testMonster.solidAreaDefaultX, monsterCollision.solidAreaDefaultX);
        assertEquals(testMonster.solidAreaDefaultY, monsterCollision.solidAreaDefaultY);
        assertEquals(testMonster.worldPosX, monsterCollision.worldPosX);
        assertEquals(testMonster.worldPosY, monsterCollision.worldPosY);

        CollisionInstance playerCollision = new CollisionInstance(testPlayer);
        assertEquals(testPlayer.solidArea, playerCollision.solidArea);
        assertEquals(testPlayer.solidAreaDefaultX, playerCollision.solidAreaDefaultX);
        assertEquals(testPlayer.solidAreaDefaultY, playerCollision.solidAreaDefaultY);
        assertEquals(testPlayer.worldPosX, playerCollision.worldPosX);
        assertEquals(testPlayer.worldPosY, playerCollision.worldPosY);
    }

    @Test
    public void objectCheck() {
        CollisionInstance goldCollision = new CollisionInstance(testGold);
        assertEquals(testGold.solidArea, goldCollision.solidArea);
        assertEquals(testGold.solidAreaDefaultX, goldCollision.solidAreaDefaultX);
        assertEquals(testGold.solidAreaDefaultY, goldCollision.solidAreaDefaultY);
        assertEquals(testGold.worldX, goldCollision.worldPosX);
        assertEquals(testGold.worldY, goldCollision.worldPosY);

        CollisionInstance diamondCollision = new CollisionInstance(testDiamond);
        assertEquals(testDiamond.solidArea, diamondCollision.solidArea);
        assertEquals(testDiamond.solidAreaDefaultX, diamondCollision.solidAreaDefaultX);
        assertEquals(testDiamond.solidAreaDefaultY, diamondCollision.solidAreaDefaultY);
        assertEquals(testDiamond.worldX, diamondCollision.worldPosX);
        assertEquals(testDiamond.worldY, diamondCollision.worldPosY);
    }

}
