package Game;

import Entity.Player;
import Entity.Monster;
import Object.Diamond;
import Object.SuperObject;
import Tile.Tile;
import Tile.TileManager;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Class handles all the elements that are added to the window. Called by
 * {@link Main}
 * 
 * @author bkh6
 * @author smc26
 * @author kgrantma
 * @author ava47
 */
public class GameConsole extends JPanel implements Runnable {

    /**
     * Represents screen settings
     */
    ScreenSettings creator = new ScreenSettings();

    public GameSetter gameSetter = new GameSetter();

    /**
     * Represents frames per seconds
     */
    int FPS = 60;

    /**
     * Represents time in game for updates
     */
    Thread gameThread;

    /**
     * Key handler to handle inputs
     */
    KeyHandler keyInput = new KeyHandler(this);

    /**
     * UI and Game States
     */
    public UI ui = new UI(this);
    public gameState state;

    /**
     * Represents the various states the game may be in. elements of this enum are passed/checked in other methods in
     * the {@link GameConsole} class.
     */
    public enum gameState {
        /**
         * Represents the title screen state
         */
        TITLE_STATE,
        /**
         * Represents the gameplay loop state
         */
        PLAY_STATE,
        /**
         * Represents the pause/paused screen state
         */
        PAUSE_STATE,
        /**
         * Represents the game over screen state
         */
        GAME_OVER_STATE,
        /**
         * Represents the game won state
         */
        WON_STATE,
        /**
         * Represents the controls/help screen state
         */
        CONTROLS_STATE
    };

    /**
     * Objects
     */
    public static int numGold = 12;

    private SuperObject[] obj = gameSetter.createObject(numGold);

    public AssetSetter aSetter = new AssetSetter(this);

    public Diamond diamond = new Diamond(this);

    /**
     * Tiles
     */
    private Tile[][] tiles = gameSetter.createTiles();
    private TileManager manager = gameSetter.createTileManager();

    /**
     * Entities
     */

    private int numMoveMonster = aSetter.getNumMoveMonster();
    private int numStaticMonster = aSetter.getNumStaticMonster();

    private Monster[] monsters = gameSetter.createMonster(numMoveMonster, numStaticMonster);

    public Player player = new Player(this, keyInput);

    /**
     * Constructs a GameConsole object
     */
    public GameConsole() {
        int screenWidth = creator.screenWidth(); // 768 pixels
        int screenHeight = creator.screenHeight(); // 576 pixels
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
        this.state = gameState.TITLE_STATE;
    }

    /**
     * Get method for the in game time
     * 
     * @return the current game time
     */
    public double getGameTime() {
        return ui.time;
    }

    /**
     * Method to start a thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Method to set up the game before the gameConsole is added to the window
     */
    public void setupGame() {
        manager.setTiles(tiles, manager.readMap("l1"));
        aSetter.setObject(tiles);
        aSetter.setDiamond(tiles);
        aSetter.setMonsters(tiles, numMoveMonster, numStaticMonster);
        if (ui.muteCheck == false) {
            PlaySound.playMusic(5);
        }
    }

    /**
     * Method for restarting the game
     */
    public void restart() {
        PlaySound.stopMusic();
        player.setDefaultPosition();
        player.restorePlayer();
        ui.time = 0.00;
        setupGame();
        startGameThread();
    }

    /**
     * Creates a game loop which repaints based on delta time
     */
    @Override
    public void run() {
        double interval = 1000000000 / FPS; // 0.01666 seconds
        double rateOfChange = 0;
        long endTime = System.nanoTime();
        long currentTime;

        // Game Loop
        while (gameThread != null) {
            currentTime = System.nanoTime();
            rateOfChange += (currentTime - endTime) / interval;
            endTime = currentTime;

            if (rateOfChange >= 1) {
                update();
                repaint();
                rateOfChange--;
            }
        }

    }

    /**
     * Updates the player and monster images based on game states
     */
    public void update() {

        if (state == gameState.PLAY_STATE) {
            player.update();
            for (int i = 0; i < numMoveMonster + numStaticMonster; i++) {
                monsters[i].update();
            }
            diamond.update(aSetter, tiles);
        }
    }

    /**
     * Draws objects to the window
     * 
     * @param graphic the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        // Type-casting to Graphics2D
        Graphics2D graphic2D = (Graphics2D) graphic;

        // Tile Drawing
        for (Tile[] t_arr : tiles) {
            for (Tile t : t_arr) {
                t.draw(graphic2D, this);
            }
        }

        // Object Drawing
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && !obj[i].isHidden) {
                obj[i].draw(graphic2D, this);
            }
        }

        player.draw(graphic2D);
        for (int i = 0; i < numMoveMonster + numStaticMonster; i++) {
            monsters[i].draw(graphic2D);
        }
        ui.draw(graphic2D);

        graphic2D.dispose();
    }

}
