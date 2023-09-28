package Entity;

import Game.*;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The entity class is the superclass of all entities
 */
public class Entity {

    /**
     * {@link GameConsole} object to be referenced from
     */
    GameConsole console;
    /**
     * Represents the coordinates of the entity dependent of their position in the
     * world
     */
    public int worldPosX, worldPosY;

    /**
     * Represents collision checker
     */
    protected CollisionChecker cChecker;

    /**
     * Represents the speed of the entity
     */
    public int speed;

    /**
     * Represents the direction of the entity
     */
    public String direction;

    /**
     * Represents the frames the image is at, used to update every 10 frames
     */
    public int spriteCount = 0;
    /**
     * Represents the image the current sprite is on, used to mimic movement
     */
    public int spriteNum = 1;

    /**
     * Represents the area a collision occurs
     */
    int xSolidArea = 12;
    int ySolidArea = 8;
    int widthAndHeight = 32;
    public Rectangle solidArea = new Rectangle(xSolidArea, ySolidArea, widthAndHeight, widthAndHeight);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;

    /**
     * Represents tile Size from {@link ScreenSettings} class
     */
    protected int tileSize = ScreenSettings.tileSize();

    /**
     * Represents whether a collision occurs or not on contact
     */
    public boolean collisionOn = false;

    /**
     * Constructs an Entity
     * 
     * @param console {@link GameConsole} to reference from.
     */
    public Entity(GameConsole console) {
        this.console = console;
        cChecker = new CollisionChecker(console);
    }

    /**
     * Calculates the position that the Entity needs to be drawn with respect to
     * the player's position and the world position
     * Uses the screen sizing from {@link ScreenSettings} class
     */
    protected int[] calculateDrawPosition() {

        int screenX = worldPosX - console.player.worldPosX + console.player.screenX;
        int screenY = worldPosY - console.player.worldPosY + console.player.screenY;

        int screenWidth = ScreenSettings.screenWidth();
        int screenHeight = ScreenSettings.screenHeight();

        int worldWidth = ScreenSettings.worldWidth();
        int worldHeight = ScreenSettings.worldHeight();

        if (console.player.worldPosX < console.player.screenX) {
            screenX = worldPosX;
        }
        if (console.player.worldPosY < console.player.screenY) {
            screenY = worldPosY;
        }
        int rightOffset = screenWidth - console.player.screenX;
        if (rightOffset > worldWidth - console.player.worldPosX) {
            screenX = screenWidth - (worldWidth - worldPosX);
        }
        int bottomOffset = screenHeight - console.player.screenY;
        if (bottomOffset > worldHeight - console.player.worldPosY) {
            screenY = screenHeight - (worldHeight - worldPosY);
        }

        int[] arr = { screenX, screenY };
        return arr;

    }

    /**
     * Sets up the entity image from the res folder and scales it accordingly
     * 
     * @param imagePath the image-path in the res folder
     * @return newly created image
     */
    protected BufferedImage setup(String imagePath) {
        Utility uTool = new Utility();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(imagePath + ".png"));
            image = uTool.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return image;
    }

}
