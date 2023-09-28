package Object;

import Game.GameConsole;
import Game.ScreenSettings;
import Game.Utility;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * The SuperObject class is the base object for all objects
 * 
 * @author bkh6
 * @author kgrantma
 */

public class SuperObject {
    /**
     * Represents the sprite image of the object
     */
    public BufferedImage image;

    /**
     * Represents the name of the object
     */
    public String name;
    /**
     * Represents the coordinates of the object dependent of their position in the
     * world
     */
    public int worldX, worldY;

    /**
     * Represents whether object has a collision or not
     */
    public boolean collision = false;

    /**
     * Represents the area a collision occurs
     */
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    /**
     * Boolean to determine if the current object is hidden from player view
     */
    public boolean isHidden = false;

    /**
     * Instance of {@link Utility} which exists for subclasses of SuperObject to
     * access.
     * 
     * @see Utility
     */
    Utility util = new Utility();

    /**
     * Represents tile Size from {@link ScreenSettings} class
     */

    protected int tileSize = ScreenSettings.tileSize();

    /**
     * Represents screen width and height from {@link ScreenSettings} class
     */
    private int screenHeight = ScreenSettings.screenHeight();
    private int screenWidth = ScreenSettings.screenWidth();


    /**
     * Draws the object on the screen based on its position (x, y).
     * Appropriately handles relative position according to {@link GameConsole}.
     * 
     * @param g2      {@link Graphics2D} object to be drawn onto
     * @param console {@link GameConsole} object to be read from
     */
    public void draw(Graphics2D g2, GameConsole console) {
        if (isHidden) {
            return;
        }

        int screenX = worldX - console.player.worldPosX + console.player.screenX;
        int screenY = worldY - console.player.worldPosY + console.player.screenY;

        int worldWidth = ScreenSettings.worldWidth();
        int worldHeight = ScreenSettings.worldHeight();

        if (console.player.worldPosX < console.player.screenX) {
            screenX = worldX;
        }
        if (console.player.worldPosY < console.player.screenY) {
            screenY = worldY;
        }
        int rightOffset = screenWidth - console.player.screenX;
        if (rightOffset > worldWidth - console.player.worldPosX) {
            screenX = screenWidth - (worldWidth - worldX);
        }
        int bottomOffset = screenHeight - console.player.screenY;
        if (bottomOffset > worldHeight - console.player.worldPosY) {
            screenY = screenHeight - (worldHeight - worldY);
        }

        if (worldX + tileSize > console.player.worldPosX - console.player.screenX &&
                worldX - tileSize < console.player.worldPosX + console.player.screenX &&
                worldY + tileSize > console.player.worldPosY - console.player.screenY &&
                worldY - tileSize < console.player.worldPosY + console.player.screenY) {
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);

        }
        // If player is around the edge, draw everything
        else if (console.player.worldPosX < console.player.screenX ||
                console.player.worldPosY < console.player.screenY ||
                rightOffset > worldWidth - console.player.worldPosX ||
                bottomOffset > worldHeight - console.player.worldPosY) {
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }

}
