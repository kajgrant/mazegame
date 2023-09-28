package Tile;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import Tile.*;
import java.awt.Graphics2D;
import Game.GameConsole;
import org.junit.rules.ExpectedException;

public class TileTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void createBrick()
    {
        Tile t = Tile.makeTile(Tile.Type.BRICK);
        assert(t instanceof Brick);
    }

    @Test
    public void createBrownDirt()
    {
        Tile t = Tile.makeTile(Tile.Type.BROWN_DIRT);
        assert(t instanceof BrownDirt);
    }

    @Test
    public void createDirt()
    {
        Tile t = Tile.makeTile(Tile.Type.DIRT);
        assert(t instanceof Dirt);
    }

    @Test
    public void createEarth()
    {
        Tile t = Tile.makeTile(Tile.Type.EARTH);
        assert(t instanceof Earth);
    }

    @Test
    public void createFloor()
    {
        Tile t = Tile.makeTile(Tile.Type.FLOOR);
        assert(t instanceof Floor);
    }

    @Test
    public void createGrass()
    {
        Tile t = Tile.makeTile(Tile.Type.GRASS);
        assert(t instanceof Grass);
    }

    @Test
    public void createSand()
    {
        Tile t = Tile.makeTile(Tile.Type.SAND);
        assert(t instanceof Sand);
    }

    @Test
    public void createWall()
    {
        Tile t = Tile.makeTile(Tile.Type.WALL);
        assert(t instanceof Wall);
    }

    @Test
    public void createWood()
    {
        Tile t = Tile.makeTile(Tile.Type.WOOD);
        assert(t instanceof Wood);
    }

    @Test
    public void testMakeTileNull()
    {
        exception.expect(NullPointerException.class);
        Tile t = Tile.makeTile(null);
        assert(t == null);
    }

    @Test
    public void testDraw()
    {
        GameConsole gc = new GameConsole();
        Tile t = Tile.makeTile(Tile.Type.WALL);
        t.draw(t.image.createGraphics(), gc);

        gc.player.worldPosX = 10000;

        gc.player.worldPosX = 10000;
        t.draw(t.image.createGraphics(), gc);
    }
}