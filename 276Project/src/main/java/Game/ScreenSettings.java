package Game;

import Tile.Tile;

/**
 * ScreenSettings class that creates the default screen settings
 *  @author bkh6
 *  *
 */
public class ScreenSettings {


    /**
     * Represents the map size
     */
    static final int mapSize = 32;

    /**
     * Creates the default tile size
     * @return tile size
     */
    public static int tileSize(){
        final int defaultTileSize = 16; // 16x16 tile
        final int scale = 3;
        final int tileSize = defaultTileSize * scale; // 48x48 tile
        return tileSize;
    }


    /**
     * Creates the screen width
     * @return the screen width
     */
    public static int screenWidth(){
        int tileSize = tileSize();
        final int screenCol = 16;
        final int screenWidth = tileSize * screenCol; // 768 pixels
        return screenWidth;

    }

    /**
     * Creates the screen height
     * @return the screen height
     */
    public static int screenHeight(){
        int tileSize = tileSize();
        final int screenRow = 12;
        final int screenHeight = tileSize * screenRow; // 576 pixels
        return screenHeight;
    }

    /**
     * Creates the world width
     * @return the world width
     */

    public static int worldWidth(){
        int tileSize = tileSize();
        final int worldWidth = tileSize * mapSize;
        return worldWidth;
    }

    /**
     * Creates the world height
     * @return the world height
     */
    public static int worldHeight(){
        int tileSize = tileSize();
        final int worldHeight = tileSize * mapSize;
        return worldHeight;

    }

    /**
     * gets the map size
     * @return the mapsize
     */
    public static int getMapSize(){
        return mapSize;
    }









}

