package Object;

import Game.AssetSetter;
import Game.GameConsole;
import Game.GameSetter;
import Game.UI;
import Tile.Tile;
import org.junit.Before;
import org.junit.Test;

public class DiamondTest {

    GameConsole g;
    AssetSetter a;
    GameSetter gs;
    Tile[][] tile;
    UI ui;

    @Before
    public void init(){
        g = new GameConsole();
        a = new AssetSetter(g);
        gs = new GameSetter();
        tile = gs.createTiles();
        ui = new UI(g);



    }

    @Test
    public void createDiamond(){
        Diamond d = new Diamond(g);
        assert(d instanceof Diamond);

    }

    @Test
    public void checkUpdateT(){
        Diamond d = new Diamond(g);
        ui.time = 50;
        d.diamondSpawnTime = 50;
        d.update(a, tile);
        assert(d.isHidden == true);
    }

    @Test
    public void checkUpdateF(){
        Diamond d = new Diamond(g);
        ui.time = 0;
        d.diamondSpawnTime = 0;
        d.update(a, tile);
        assert(d.isHidden == false);
    }


}
