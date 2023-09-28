package Tile;

import Game.ScreenSettings;
import Game.Utility;
import Game.GameConsole;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The Tile class is the base object for all tile objects, that is, objects that
 * function as the background.
 * Objects of the Tile class may not be directly instantiated, instead must be
 * created through the {@link #makeTile(Type) makeTile()}
 * method.
 * 
 * @author smc26
 * @see #makeTile(Type)
 * @since 1.0
 */
public abstract class Tile {

    /**
     * Enum representing the types of tiles that may be created. Tiles created
     * through {@link TileManager} CSV files correspond to the ordinal of the enum.
     * i.e., 0 = 'DIRT', 1 = 'WALL', etc.
     */
    public enum Type {
        /**
         * Represents the Dirt class
         */
        DIRT,
        /**
         * Represents the Wall class
         */
        WALL,
        /**
         * Represents the Brick class
         */
        BRICK,
        /**
         * Represents the Brown Dirt class
         */
        BROWN_DIRT,
        /**
         * Represents the Floor class
         */
        FLOOR,
        /**
         * Represents the Sand class
         */
        SAND,
        /**
         * Represents the Wood class
         */
        WOOD,
        /**
         * Represents the Earth class
         */
        EARTH,
        /**
         * Represents the Grass class
         */
        GRASS
    }

    /**
     * Represents the type of the tile. Can only have values from {@link Type Type}.
     * 
     * @see Type
     */
    public Type type;

    /**
     * Represents the coordinates of the tile dependent on their position in the
     * world.
     */
    public int worldX, worldY;

    /**
     * Represents the sprite image of the Tile.
     * 
     * @see BufferedImage
     */
    public BufferedImage image;

    /**
     * Represents tile Size from {@link ScreenSettings} class
     *
     */
    protected int tileSize = ScreenSettings.tileSize();

    /**
     * Instance of {@link Utility} which exists for subclasses of {@link Tile} to
     * access.
     * 
     * @see Utility
     */
    protected Utility util = new Utility();

    /**
     * Constructs a new Tile object. This class is abstract and objects of this type
     * cannot be directly created.
     * 
     * @see #makeTile(Type) makeTile
     */
    protected Tile() {
        // Intentionally empty
    }

    /**
     * Creates a tile based on a given {@link Type} t. Valid entries must come from
     * {@link Type}.
     * 
     * @param t   Type t, from {@link Type}.
     * @return A Tile object that extends specialized behavior.
     * @see Type
     */
    protected static Tile makeTile(Type t) {
        switch (t) {
            case DIRT:
                return new Dirt();
            case WALL:
                return new Wall();
            case BRICK:
                return new Brick();
            case BROWN_DIRT:
                return new BrownDirt();
            case EARTH:
                return new Earth();
            case SAND:
                return new Sand();
            case WOOD:
                return new Wood();
            case GRASS:
                return new Grass();
            case FLOOR:
                return new Floor();

            default:
                return null;
        }
    }

    /**
     * Draws the Tile object on the screen based on its position (x, y).
     * Appropriately handles relative position according to {@link GameConsole}.
     * 
     * @param g2      {@link Graphics2D} object to be drawn onto
     * @param console {@link GameConsole} object to be read from
     */
    public void draw(Graphics2D g2, GameConsole console) {
        int screenX = worldX - console.player.worldPosX + console.player.screenX;
        int screenY = worldY - console.player.worldPosY + console.player.screenY;


        int screenWidth = ScreenSettings.screenWidth();
        int screenHeight = ScreenSettings.screenHeight();
        int worldWidth = ScreenSettings.worldWidth();
        int worldHeight = ScreenSettings.worldHeight();

        // Stop moving the camera at the edge

        if (console.player.screenX > console.player.worldPosX) {
            screenX = worldX;
        }
        if (console.player.screenY > console.player.worldPosY) {
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
        } else if (console.player.screenX > console.player.worldPosX ||
                console.player.screenY > console.player.worldPosY ||
                rightOffset > worldWidth - console.player.worldPosX ||
                bottomOffset > worldHeight - console.player.worldPosY) {
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }

}
