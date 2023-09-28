package Object;

import Game.GameConsole;
import org.junit.Test;

public class SuperObjectTest {

    @Test
    public void testDraw(){
        GameConsole gc = new GameConsole();
        SuperObject s = new Gold();
        s.draw(s.image.createGraphics(), gc);

        gc.player.worldPosX = 10000;
        s.draw(s.image.createGraphics(), gc);

    }
}
