package Game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameConsoleTest {

    GameConsole g;

    @Before
    public void init(){
        g = new GameConsole();
    }

    @Test
    public void GameConsoleT(){
        GameConsole gt = new GameConsole();
        assert(gt instanceof GameConsole);

    }

    @Test
    public void getGameT(){
        assertEquals(g.getGameTime(), 0, .00);

    }

    @Test
    public void gameThreadT(){
        g.startGameThread();
    }

    @Test
    public void setupT(){
        g.setupGame();
    }

    @Test
    public void restartT(){
        g.restart();
    }

    @Test
    public void updateT(){
        g.state = GameConsole.gameState.PLAY_STATE;
        g.setupGame();
        g.update();
    }






}
