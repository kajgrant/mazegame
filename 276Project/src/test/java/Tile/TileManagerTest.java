package Tile;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Before;
import Tile.*;
import java.awt.Graphics2D;
import Game.ScreenSettings;
import Game.GameConsole;

public class TileManagerTest {
    ScreenSettings s;
    TileManager t;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init()
    {
        s = new ScreenSettings();
        t = new TileManager();
    }

    @Test
    public void testSetTilesValid()
    {
        String[][] map = t.readMap("valid");
        for(int i = 0; i < 9; i++)
            assert(map[0][i].equals(i + ""));

        Tile[][] result = new Tile[s.getMapSize()][s.getMapSize()];
        t.setTiles(result, map);

        Tile.Type[] types = Tile.Type.values();
        for(int i = 0; i < 9; i++) {
            Tile tile = Tile.makeTile(types[Integer.parseInt(map[0][i])]);
            assert(result[0][i].type == tile.type);
        }
    }

    @Test
    public void testSetTilesInvalid()
    {
        String[][] map = t.readMap("invalid");
        Tile[][] result = new Tile[s.getMapSize()][s.getMapSize()];
        t.setTiles(result, map);
        for(int i = 0; i < s.getMapSize(); i++)
            assert(result[0][i] == null);
    }

    @Test
    public void testReadTilesFileMissing()
    {
        String[][] map = t.readMap("file_nan");
        assert(map == null);
    }
}