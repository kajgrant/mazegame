package Game;

import org.junit.Before;
import org.junit.Test;

import javax.swing.JFrame;
import java.awt.Graphics2D;

public class UITest {
    GameConsole gc;
    Graphics2D g2;

    @Before
    public void setUp() {
        gc = new GameConsole();
        JFrame frame = new JFrame();
        frame.add(gc);
        frame.pack();
        g2 = (Graphics2D) gc.getGraphics();
    }

    @Test
    public void testDrawAllScreens() {
        gc.state = GameConsole.gameState.TITLE_STATE;
        gc.ui.draw(g2);

        // test the drawing of the sound icon when mute and unmute
        gc.ui.menuNum = -1;
        gc.ui.muteCheck = false;
        gc.ui.draw(g2);

        gc.ui.muteCheck = true;
        gc.ui.draw(g2);

        gc.state = GameConsole.gameState.CONTROLS_STATE;
        gc.ui.draw(g2);

        gc.state = GameConsole.gameState.PLAY_STATE;
        gc.ui.draw(g2);

        gc.state = GameConsole.gameState.PAUSE_STATE;
        gc.ui.draw(g2);

        gc.state = GameConsole.gameState.GAME_OVER_STATE;
        gc.ui.draw(g2);

        gc.state = GameConsole.gameState.WON_STATE;
        // test drawTime() with different Time taken values
        gc.ui.time = 1000;
        gc.ui.draw(g2);

        gc.ui.time = 50;
        gc.ui.draw(g2);
    }

    @Test
    public void testMessage() {
        gc.state = GameConsole.gameState.PLAY_STATE;

        gc.ui.addMessage("test0",0);
        gc.ui.addMessage("test1",1);
        gc.ui.addMessage("test2",2);
        gc.ui.addMessage("test3",3);

        gc.ui.draw(g2);

        // test if message disappears after 120 frames
        gc.ui.messageCounter.set(0, 120);
        gc.ui.draw(g2);
    }
}