package Game;

import Entity.Monster;
import Tile.Tile;
import Tile.TileManager;
import Object.SuperObject;

/**
 * GameSetter class which creates the items necessary for the game
 *   @author bkh6
 */

public class GameSetter {


    /**
     * Represents an array of objects
     */
    private SuperObject[] obj;

    /**
     * Represents the map
     */
    private static int mapSize = ScreenSettings.getMapSize();

    /**
     * Represents the tiles
     */
    private Tile[][] tiles;


    /**
     * Represents Tile Manager
     */
    private TileManager tileManager;

    /**
     * Represents monsters
     */
    private Monster[] monsters;

    /**
     * Creates a monster array
     * @param numMoveMonster number of moving monsters
     * @param numStaticMonster number of static monster
     * @return newly created monster array
     */
    public Monster[] createMonster(int numMoveMonster, int numStaticMonster){
        monsters = new Monster[numMoveMonster + numStaticMonster];
        return monsters;

    }

    /**
     * Creates 2d tile array
     * @return newly created tiles
     */
    public Tile[][] createTiles(){
        tiles = new Tile[mapSize][mapSize];
        return tiles;
    }

    /**
     * getter for tiles
     * @return returns tiles
     */
    public Tile[][] getTile(){
        return tiles;
    }


    /**
     * Creates a tile manager
     * @return newly created tile manager
     */
    public TileManager createTileManager(){
        tileManager = new TileManager();
        return tileManager;
    }

    /**
     * Gets the monster
     * @return monster array
     */
    public Monster[] getMonster(){
        return monsters;
    }

    /**
     * creates the object
     * @param numGold  the number of gold (for indexing)
     * @return newly created super object array
     */
    public SuperObject[] createObject(int numGold){
        obj = new SuperObject[numGold + 4];
        return obj;

    }

    /**
     * gets the objects
     * @return super object array
     */
    public SuperObject[] getObjects(){
        return obj;
    }



}
