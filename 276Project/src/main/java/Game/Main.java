package Game;

import javax.swing.JFrame;

/**
 * Entry point for the Miner's Adventure game
 * @author bkh6
 * @since 1.0
 */
public class Main {
    /**
     * Main entry point
     * @param args      No special parameters are needed or taken in.
     */
    public static void main(String[] args) {
        //Adding window to game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Miner's Adventure");

        //Adding Game to window
        GameConsole gameConsole = new GameConsole();
        //Setup game objects
        gameConsole.setupGame();

        window.add(gameConsole);
        window.pack();
        //Window will be set to the center of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //Starts time for the game
        gameConsole.startGameThread();

    }
}

