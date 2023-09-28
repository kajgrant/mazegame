package Game;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class KeyHandlerTest {
    GameConsole gc;
    KeyHandler kh;

    @Before
    public void setUp() {
        gc = new GameConsole();
        kh = new KeyHandler(gc);
    }

    @Test
    public void testTitleScreen(){
        // check if initial screen is title screen
        assert(gc.state == GameConsole.gameState.TITLE_STATE);

        // check if selecting PLAY sets PLAY_STATE
        gc.ui.menuNum = 0;
        kh.titleState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.PLAY_STATE);

        // check if selecting CONTROLS sets CONTROLS_STATE
        gc.ui.menuNum = 1;
        kh.titleState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.CONTROLS_STATE);

        // check if selecting sound icon mutes/unmutes correctly
        gc.ui.menuNum = -1;
        PlaySound.playMusic(5);
        kh.titleState(KeyEvent.VK_ENTER);
        assertTrue(gc.ui.muteCheck);

        kh.titleState(KeyEvent.VK_ENTER);
        assertFalse(gc.ui.muteCheck);

        // test menu option movement
        gc.ui.menuNum = 3;
        for (int i = 2; i >= -1; i--) {
            kh.titleState(KeyEvent.VK_W);
            assert(gc.ui.menuNum == i);
        }
        kh.titleState(KeyEvent.VK_W);
        assert(gc.ui.menuNum == 2);

        gc.ui.menuNum = -2;
        for (int i = -1; i <= 2; i++) {
            kh.titleState(KeyEvent.VK_S);
            assert(gc.ui.menuNum == i);
        }
        kh.titleState(KeyEvent.VK_S);
        assert(gc.ui.menuNum == -1);
    }

    @Test
    public void testControlsScreen(){
        gc.state = GameConsole.gameState.CONTROLS_STATE;

        // check if selecting BACK sets TITLE_STATE
        kh.controlsState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.TITLE_STATE);
    }

    @Test
    public void testPauseScreen(){
        gc.state = GameConsole.gameState.PAUSE_STATE;

        // check if pressing ESC sets PLAY_STATE
        kh.pauseState(KeyEvent.VK_ESCAPE);
        assert(gc.state == GameConsole.gameState.PLAY_STATE);
    }

    @Test
    public void testPlayScreen(){
        gc.state = GameConsole.gameState.PLAY_STATE;

        // check if pressing ESC sets PAUSE_STATE
        kh.playState(KeyEvent.VK_ESCAPE);
        assert(gc.state == GameConsole.gameState.PAUSE_STATE);

        // check if pressing W moves player up
        kh.playState(KeyEvent.VK_W);
        assertTrue(kh.upPress);

        // check if pressing A moves player left
        kh.playState(KeyEvent.VK_A);
        assertTrue(kh.leftPress);

        // check if pressing S moves player down
        kh.playState(KeyEvent.VK_S);
        assertTrue(kh.downPress);

        // check if pressing D moves player right
        kh.playState(KeyEvent.VK_D);
        assertTrue(kh.rightPress);
    }

    @Test
    public void testGameOverScreen(){
        gc.state = GameConsole.gameState.GAME_OVER_STATE;

        // check if selecting NEW GAME sets PLAY_STATE
        PlaySound.playMusic(5);
        kh.gameOverState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.PLAY_STATE);

        // check if selecting RETURN TO MAIN MENU sets TITLE_STATE
        gc.ui.menuNum++;
        PlaySound.playMusic(5);
        kh.gameOverState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.TITLE_STATE);

        // test menu option movement
        gc.ui.menuNum = 1;
        kh.gameOverState(KeyEvent.VK_W);
        assert(gc.ui.menuNum == 0);
        kh.gameOverState(KeyEvent.VK_W);
        assert(gc.ui.menuNum == 1);

        gc.ui.menuNum = 0;
        kh.gameOverState(KeyEvent.VK_S);
        assert(gc.ui.menuNum == 1);
        kh.gameOverState(KeyEvent.VK_S);
        assert(gc.ui.menuNum == 0);
    }

    @Test
    public void testWonScreen(){
        gc.state = GameConsole.gameState.WON_STATE;

        // check if selecting RETURN TO MAIN MENU sets TITLE_STATE
        PlaySound.playMusic(5);
        kh.wonState(KeyEvent.VK_ENTER);
        assert(gc.state == GameConsole.gameState.TITLE_STATE);
    }
}