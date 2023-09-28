package Game;

import Tile.Tile;
import Tile.TileManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssetSetterTest {

    GameConsole g;
    AssetSetter a;
    Tile[][] tiles;
    GameSetter gs;
    TileManager t;
    @Before
    public void init(){
        g = new GameConsole();
        a = new AssetSetter(g);
        gs = new GameSetter();
        tiles = gs.createTiles();
        t = gs.createTileManager();
        t.setTiles(tiles, t.readMap("l1"));


    }

    @Test
    public void aSet(){
        AssetSetter at = new AssetSetter(g);
        assert(at instanceof AssetSetter);
    }

    @Test
    public void diamondT(){
        a.setDiamond(tiles);

    }

    @Test
    public void objectT(){
        a.setObject(tiles);

    }
    @Test
    public void monsterT(){
        a.setMonsters(tiles, 10,5);

    }

    @Test
    public void moveMonGetT(){
        int n = 5;
        assertEquals(a.getNumMoveMonster(), n);

    }

    @Test

    public void staticMonGetT(){
        int n = 10;
        assertEquals(a.getNumStaticMonster(), n);

    }

}
