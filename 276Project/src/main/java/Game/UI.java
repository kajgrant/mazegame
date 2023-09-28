package Game;

import Entity.Player;
import Object.Gold;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class that handles all the UI components such as the main menu and score display
 *
 * @author ava47
 */

public class UI {

    /**
     * {@link GameConsole} object to be referenced from
     */
    GameConsole console;

    /**
     * Represents the tileSize from {@link ScreenSettings class}
     */
    private int tileSize = ScreenSettings.tileSize();

    /**
     * Represents the screen height and screen width
     */
    private int screenHeight = ScreenSettings.screenHeight();
    private int screenWidth = ScreenSettings.screenWidth();

    /**
     * Graphics object that draws images, strings and backgrounds
     */
    Graphics2D g2;

    /**
     * The <code>Font</code> that is going to be used throughout the program
     */
    Font rockwell;

    /**
     * Handle menu navigation in {@link UI#titleScreen()} and {@link UI#gameOverScreen()}
     */
    public int menuNum = 0;

    /**
     * Lists to store timed messages that show up on the play screen.
     * Storing the message's value, time on screen and type
     */
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    ArrayList<Integer> messageFlag = new ArrayList<>();

    /**
     * To display time on play screen with formatting
     */
    public double time;
    DecimalFormat formatTime;

    /**
     * Define a faded background color to be used on different screens
     */
    Color fadedBlack;

    /**
     * Represents all the {@link UI} images/sprites
     */
    BufferedImage wasd, enter, esc, gold, mute, unmute, muteSelected, unmuteSelected;
    Utility util;

    /**
     * Boolean to control the mute and unmute setting
     */
    public boolean muteCheck = false;

    /**
     * Constructs a UI object and instantiates other required objects
     * @param console {@link GameConsole} to reference from.
     * @throws RuntimeException if image(s) not found
     */
    public UI(GameConsole console) {
        this.console = console;
        rockwell = new Font("Rockwell", Font.BOLD, 80);
        formatTime = new DecimalFormat("#0.00");

        fadedBlack = new Color(0, 0, 0, 150);

        util = new Utility();
        try {
            wasd = ImageIO.read(getClass().getResourceAsStream("/ui/wasd.png"));
            enter = ImageIO.read(getClass().getResourceAsStream("/ui/enter.png"));
            esc = ImageIO.read(getClass().getResourceAsStream("/ui/esc.png"));

            mute = ImageIO.read(getClass().getResourceAsStream("/ui/mute.png"));
            unmute = ImageIO.read(getClass().getResourceAsStream("/ui/unmute.png"));
            muteSelected = ImageIO.read(getClass().getResourceAsStream("/ui/muteSelected.png"));
            unmuteSelected = ImageIO.read(getClass().getResourceAsStream("/ui/unmuteSelected.png"));

            wasd = util.scaleImage(wasd, tileSize * 6, tileSize * 4);
            enter = util.scaleImage(enter, tileSize * 3, tileSize * 2);
            esc = util.scaleImage(esc, tileSize * 2, tileSize * 2);

            mute = util.scaleImage(mute, tileSize, tileSize);
            unmute = util.scaleImage(unmute, tileSize, tileSize);
            muteSelected = util.scaleImage(muteSelected, tileSize, tileSize);
            unmuteSelected = util.scaleImage(unmuteSelected,tileSize, tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gold golObj = new Gold();
        gold = golObj.image;
        gold = util.scaleImage(gold, tileSize, tileSize);
    }

    /**
     * Main UI draw method called from {@link GameConsole#paintComponent}.
     * Handles displaying screens for different Game States
     * @see GameConsole#state
     * @param g2 the <code>Graphics2D</code> object to draw with
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(rockwell);
        g2.setColor(Color.white);

        switch(console.state)
        {
            case TITLE_STATE:
                titleScreen();
                break;
            case PLAY_STATE:
                playScreen();
                break;
            case PAUSE_STATE:
                pauseScreen();
                break;
            case GAME_OVER_STATE:
                gameOverScreen();
                break;
            case WON_STATE:
                wonScreen();
                break;
            case CONTROLS_STATE:
                controlsScreen();
                break;
        }
    }

    /**
     * Draws the Title Screen and its components, title and menu
     */
    void titleScreen() {
        blackBackground();

        // Title
        drawCenteredTextWithShadow("MINER'S", (int) (tileSize * 3.5));
        drawCenteredTextWithShadow("ADVENTURE", tileSize* 5);

        // Draw menu
        String[] menuOptions = {"PLAY", "CONTROLS", "QUIT"};
        drawMenu(menuOptions);

        // Draw mute
        int x = (int) (screenWidth - tileSize * 1.5);
        int y = tileSize / 2;

        if (muteCheck) {g2.drawImage(mute, x, y, null);}
        else {g2.drawImage(unmute, x, y, null);}

        // Mute navigation
        if (menuNum == -1) {
            if (muteCheck) {g2.drawImage(muteSelected, x, y, null);}
            else {g2.drawImage(unmuteSelected, x, y, null);}
        }
    }

    /**
     * Draws the Play Screen components, {@link UI#drawScore()}, {@link UI#message()} and {@link UI#drawTime()}
     */
    void playScreen() {
        drawScore();
        message();
        drawTime();
        drawLocation();
    }

    /**
     * Draws the player's score on the screen
     */
    void drawScore() {
        int scored = Player.getScore();
        int normalGold = Player.getNormalGold();
        int y = (int) (tileSize / 1.5);
        int x = (int) (screenWidth - (tileSize * 2.5));

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        g2.setColor(Color.white);
        g2.drawString("Score: " + scored, x, y);

        x = tileSize / 2;
        int numGold = GameConsole.numGold;

        g2.drawImage(gold, x, 0, null);
        String progress = ": " + normalGold + " / " + numGold;
        g2.drawString(progress, x + tileSize, y);
    }

    /**
     * Draws the player's location on the screen
     */
    void drawLocation() {
        String xCoordinate = "x: " + (console.player.worldPosX / tileSize);
        int y = (int) (tileSize * 11.5);
        int x = (int) (screenWidth - (tileSize * 2.5));

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        g2.setColor(Color.white);
        g2.drawString(xCoordinate, x, y);

        String yCoordinate = "y: " + (console.player.worldPosY / tileSize);
        x = (int) (screenWidth - (tileSize * 1.25));
        g2.drawString(yCoordinate, x, y);

    }

    /**
     * Draws the time on the screen
     */
    void drawTime() {
        time += (double) 1 / 60;

        String text = "Time: " + formatTime.format(time);
        int y = (int) (tileSize / 1.5);
        int x = centeredText_X(text);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    /**
     * Adds messages to the ArrayLists and sets counter to 0
     * @param text adds the message text to the {@link UI#message} ArrayList
     * @param flag adds type of message to the {@link UI#messageFlag} ArrayList
     */
    public void addMessage(String text, int flag) {
        message.add(text);
        messageCounter.add(0);
        messageFlag.add(flag);
    }

    /**
     * Displays the messages on the play screen for selected amount of time.
     * Changes message's color and position based on {@link UI#messageFlag}
     */
    void message() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18));

        // position for score messages
        int scoreX = (int) (screenWidth - (tileSize*1.5));
        int scoreY = tileSize+10;

        // position for centre messages
        int centreX;
        int centreY = tileSize*2;


        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                // Message for score + 10
                if (messageFlag.get(i) == 0) {
                    g2.setColor(Color.yellow);
                    g2.drawString(message.get(i), scoreX, scoreY);
                }
                // Message for score - 10
                else if (messageFlag.get(i) == 1) {
                    g2.setColor(Color.red);
                    g2.drawString(message.get(i), scoreX, scoreY);
                }
                // Message for collecting diamond (score + 50)
                else if (messageFlag.get(i) == 2) {
                    g2.setColor(Color.cyan);
                    g2.drawString(message.get(i), scoreX, scoreY);
                }
                // Message for door opened and diamond available/gone
                else if (messageFlag.get(i) == 3) {
                    centreX = centeredText_X(message.get(i));
                    g2.setColor(Color.WHITE);
                    g2.drawString(message.get(i), centreX, centreY);
                }
                // Timed messages (add to counter until 2s)
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);

                // Increase both y for scrolling message
                if (messageFlag.get(i) < 3) {scoreY += 20;}

                // Delete message after 120 frames (2s)
                if (messageCounter.get(i) > 120) {
                    message.remove(i);
                    messageCounter.remove(i);
                    messageFlag.remove(i);
                }
            }
        }
    }

    /**
     * Draws the Pause Screen
     */
    void pauseScreen() {
        fadedBackground();
        drawCenteredTextWithShadow("PAUSED", screenHeight / 2);
    }

    /**
     * Draws the Game Over Screen and its components
     */
    void gameOverScreen() {
        fadedBackground();
        drawCenteredTextWithShadow("GAME OVER", tileSize * 4);

        // MENU
        String[] menuOptions = {"NEW GAME", "BACK TO MAIN MENU"};
        drawMenu(menuOptions);
    }

    /**
     * Draws the Won Screen and its components
     */
    void wonScreen() {
        fadedBackground();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60));

        int y = tileSize*3;
        drawCenteredTextWithShadow("CONGRATULATIONS", y);
        y+= tileSize*1.5;
        drawCenteredTextWithShadow("YOU WON!", y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25));
        // Draw time
        String timeDisplay;
        if (time < 60) {
            if (time < 10) {timeDisplay = "With a time of 0:0" + (int) time;}
            else {timeDisplay = "With a time of 0:" + (int) time;}
        }
        else {
            int min = (int) (time / 60);
            int sec = (int) (time % 60);
            if (sec < 10) {timeDisplay = "With a time of " + min + ":0" + sec;}
            else {timeDisplay = "With a time of " + min + ":" + sec;}
        }
        y += tileSize*2;
        drawCenteredTextWithShadow(timeDisplay, y);

        // Draw score
        y += tileSize;
        drawCenteredTextWithShadow("Score: " + Player.score, y);

        drawMenuOption("RETURN TO MAIN MENU", (screenHeight - tileSize));
    }

    /**
     * Draws the Controls Screen and its components
     */
    void controlsScreen() {
        blackBackground();

        g2.setFont(g2.getFont().deriveFont(70F));
        drawCenteredTextWithShadow("CONTROLS", tileSize * 2);

        int x = tileSize * 2;
        int y = (int) (tileSize * 4.5);
        g2.drawImage(wasd, x, y, null);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("Move", x + tileSize * 2, (int) (y + tileSize * 4.5));

        x += tileSize * 8.5;
        y -= tileSize;
        g2.drawImage(enter, x, y, null);
        g2.drawString("Select", x + tileSize / 2, (int) (y + tileSize * 2.5));

        y += tileSize * 3.5;
        g2.drawImage(esc, x + tileSize / 2, y, null);
        g2.drawString("Pause", x + tileSize / 2, (int) (y + tileSize * 2.5));

        drawMenuOption("BACK", (screenHeight - tileSize));
    }

    // Helper functions to reduce clutter

    /**
     *  Draws a string in the center with a shadow
     * @param text Represents string of text that needs to be draw
     * @param y Represents the y coordinate
     */
    void drawCenteredTextWithShadow(String text, int y) {
        int x = centeredText_X(text);
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    /**
     * Draws red text for screens with only one menu option
     * @param text Represents string of text that needs to be drawn
     * @param y Represents the y coordinate
     */
    void drawMenuOption(String text, int y) {
        int x = centeredText_X(text);
        g2.setFont(g2.getFont().deriveFont(25F));
        g2.setColor(Color.RED);
        g2.drawString(text, x, y);
    }

    /**
     * Draws all the menu options provided in array.
     * Also handles navigation by making the option you're on red
     * @param menuOptions Array containing all menu options
     */
    void drawMenu(String[] menuOptions) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));

        int y = tileSize*8;

        for (String text : menuOptions) {
            int x = centeredText_X(text);
            g2.drawString(text, x, y);
            y += tileSize;
        }
        if (menuNum>=0 && menuNum<menuOptions.length) {
            int x = centeredText_X(menuOptions[menuNum]);
            g2.setColor(Color.RED);
            g2.drawString(menuOptions[menuNum], x, tileSize*(menuNum+8));
        }
    }

    /**
     * Fades the background screen to a faded black colour
     */
    void fadedBackground() {
        g2.setColor(fadedBlack);
        g2.fillRect(0, 0, screenWidth, screenHeight);
    }

    /**
     * Draws a black background on the screen
     */
    void blackBackground() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, screenWidth, screenHeight);
    }

    /**
     * Method to compute what x coordinate is needed to center the text
     * @param text Represents the string of text than needs to be centered
     * @return returns the x coordinate
     */
    int centeredText_X(String text) {
        // Find middle of screen relative to length of text
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return screenWidth / 2 - length / 2;
    }
}