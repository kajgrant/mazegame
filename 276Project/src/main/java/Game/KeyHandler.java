package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class handles key input from user
 * @author bkh6
 * @author ava47
 */
public class KeyHandler implements KeyListener {

    /**
     * {@link GameConsole} object to be referenced from
     */
    GameConsole console;
    /**
     * Represents the key pressed by user
     */
    public boolean upPress, downPress, leftPress, rightPress;

    /**
     * Constructs a KeyHandler to take user input
     * @param console {@link GameConsole} to reference from
     */
    public KeyHandler(GameConsole console){
        this.console = console;
    }

    /**
     * Takes types key input by user (intentionally empty)
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Changes booleans based on pressed key input, changes based on game states
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        switch(console.state)
        {
            case TITLE_STATE:
                titleState(keyCode);
                break;
            case CONTROLS_STATE:
                controlsState(keyCode);
                break;
            case PLAY_STATE:
                playState(keyCode);
                break;
            case PAUSE_STATE:
                pauseState(keyCode);
                break;
            case GAME_OVER_STATE:
                gameOverState(keyCode);
                break;
            case WON_STATE:
                wonState(keyCode);
                break;
        }
    }

    /**
     * Method represents title view in UI with menu and adds functionality to key events
     * @param keyCode the event being processed
     */
    void titleState(int keyCode){
        if(keyCode == KeyEvent.VK_W) {
            console.ui.menuNum--;
            if(console.ui.menuNum < -1) {console.ui.menuNum = 2;}
        }
        if(keyCode == KeyEvent.VK_S) {
            console.ui.menuNum++;
            if(console.ui.menuNum > 2) {console.ui.menuNum = -1;}
        }
        if(keyCode == KeyEvent.VK_ENTER) {
            if(console.ui.menuNum == -1) {
                if (!console.ui.muteCheck) {
                    console.ui.muteCheck = true;
                    PlaySound.stopMusic();
                }
                else {
                    console.ui.muteCheck = false;
                    PlaySound.playMusic(5);
                }
            }
            if(console.ui.menuNum == 0) {console.state = GameConsole.gameState.PLAY_STATE;}
            if(console.ui.menuNum == 1) {console.state = GameConsole.gameState.CONTROLS_STATE;}
            if(console.ui.menuNum == 2) {System.exit(0);}
        }
    }

    /**
     * The method represents the game over state and adds functionality to the menu from key events
     * @param keyCode the event being processed
     */
    void gameOverState(int keyCode){
        if(keyCode == KeyEvent.VK_W) {
            console.ui.menuNum--;
            if(console.ui.menuNum < 0) {console.ui.menuNum = 1;}
        }
        if(keyCode == KeyEvent.VK_S) {
            console.ui.menuNum++;
            if(console.ui.menuNum > 1) {console.ui.menuNum = 0;}
        }
        if(keyCode == KeyEvent.VK_ENTER) {
            if (console.ui.menuNum == 0) {
                console.state = GameConsole.gameState.PLAY_STATE;
                console.gameThread = null;
                console.restart();
            }
            if (console.ui.menuNum == 1) {
                console.state = GameConsole.gameState.TITLE_STATE;
                console.gameThread = null;
                console.restart();
            }
        }
    }

    /**
     * Adds functionality to the player's movement when keys are pressed
     * @param keyCode the event being processed
     */
    void playState(int keyCode){
        if(keyCode == KeyEvent.VK_W){
            upPress = true;
        }
        if(keyCode == KeyEvent.VK_S){
            downPress = true;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPress = true;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPress = true;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            console.state = GameConsole.gameState.PAUSE_STATE;
        }
    }

    /**
     * Adds functionality to the pause screen ui when key events occur
     * @param keyCode the event being processed
     */
    void pauseState(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE){
            console.state = GameConsole.gameState.PLAY_STATE;
        }
    }

    /**
     * Adds functionality to the won screen UI when key event occurs
     * @param keyCode the event being processed
     */
    void wonState(int keyCode){
        if(keyCode == KeyEvent.VK_ENTER){
            console.state = GameConsole.gameState.TITLE_STATE;
            console.gameThread = null;
            console.restart();
        }
    }

    /**
     * Adds functionaity to the controls UI when key event occurs
     * @param keyCode the event being processed
     */
    void controlsState(int keyCode) {
        if(keyCode == KeyEvent.VK_ENTER){
            console.state = GameConsole.gameState.TITLE_STATE;
        }
    }

    /**
     * Changes booleans based on keys released
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            upPress = false;
        }
        if(keyCode == KeyEvent.VK_S){
            downPress = false;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPress = false;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPress = false;
        }

    }
}
