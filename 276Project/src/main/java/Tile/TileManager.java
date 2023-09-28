package Tile;

import Game.ScreenSettings;
import Tile.Tile.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * The TileManager class is the access point for creating the background tiling
 * of the game. {@link Tile} objects are
 * meant to be created exclusively through this class.
 * 
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class TileManager {

    /**
     * Represents map Size from {@link ScreenSettings} class
     */
    private int mapSize = ScreenSettings.getMapSize();

    /**
     * Creates an instance of TileManager, there is no instance specific behavior.
     */
    public TileManager()
    {
        // Does nothing in particular
    }

    /**
     * Initializes a {@link Tile Tile[][]} array with Tile objects
     * 
     * @param tiles The array to be filled
     * @param map   The array to read from
     */
    public void setTiles(Tile[][] tiles, String[][] map) {
        int tileSize = ScreenSettings.tileSize();
        Type[] type = Type.values();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int idx = Integer.parseInt(map[i][j]);
                if (idx >= 0 && idx <= 8) {
                    tiles[i][j] = Tile.makeTile(type[idx]);
                    tiles[i][j].worldX = tileSize * j;
                    tiles[i][j].worldY = tileSize * i;
                } else
                    tiles[i][j] = null;
            }
        }
    }

    /**
     * Reads a .csv map from /res/ and returns a {@link String
     * String[][]} consisting of the data.
     * Intended to be fed into {@link #setTiles}. This method can accept maps of any
     * size; however, maps provided
     * are 32x32.
     * 
     * @param map The name of the map file
     * @see #setTiles
     * @return A 2D String array populated with integers which can be converted using {@link TileManager#setTiles}.
     */
    public String[][] readMap(String map) {
        String[][] result = new String[mapSize][mapSize];
        try {

            InputStream i = Tile.class.getResourceAsStream("/maps/" + map + ".csv");
            if(i == null)
                return null;
            BufferedReader buf = new BufferedReader(new InputStreamReader(i));
            try {
                result = buf
                        .lines()
                        .map(row -> row.split(","))
                        .toArray(String[][]::new);
            } finally {
                buf.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
