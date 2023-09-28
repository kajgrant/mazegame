package Game;

import Tile.Tile;
import Tile.TileManager;
import Object.SuperObject;
import org.junit.Before;
import org.junit.Test;

public class GameSetterTest {

    GameSetter s;
    @Before
    public void init()
    {
        s = new GameSetter();
    }

    @Test
    public void tileCheck(){

        Tile[][] t = s.createTiles();
        assert(t instanceof Tile[][]);

    }

    @Test
    public void tileManagerCheck(){
        TileManager t = s.createTileManager();
        assert(t instanceof TileManager);
    }

    @Test
    public void objectCheck(){
        SuperObject[] t = s.createObject(7);
        assert(t instanceof SuperObject[]);
    }
}
