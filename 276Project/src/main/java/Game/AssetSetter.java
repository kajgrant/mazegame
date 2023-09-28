package Game;

import java.util.Random;

import Tile.Tile;

import Object.DoorOpen;
import Object.Gold;
import Object.DoorClosed;
import Object.SuperObject;

import Entity.Monster;

/**
 * Class that sets monsters and objects with positions
 * 
 * @author bkh6
 * @author kgrantma
 */
public class AssetSetter {

    /**
     * {@link GameConsole} object to be referenced from
     */
    GameConsole console;

    /**
     * A 2D array mapped to the map to keep track of spawn locations
     */
    private boolean[][] spawnLocations;

    /**
     * Random object used for object and monster spawning
     */
    private Random rand = new Random();

    /**
     * Represents tile Size from {@link ScreenSettings} class
     */
    private int tileSize = ScreenSettings.tileSize();

    /**
     * Represents map Size from {@link ScreenSettings} class
     */
    private int mapSize = ScreenSettings.getMapSize();

    /**
     * Represents the objects
     */


    private static int diamondIndex;
    private static int openDoorIndex;

    private static int closedDoorIndex;

    /**
     * Represents the monster
     */
    private  final int numMoveMonster = 5;
    private  final int numStaticMonster = 10;





    private SuperObject[] obj;
    /**
     * Creates an assetSetter object
     * 
     * @param console {@link GameConsole} to reference from.
     */
    public AssetSetter(GameConsole console) {
        this.console = console;
        obj = console.gameSetter.getObjects();
        spawnLocations = new boolean[mapSize][mapSize];
    }

    /**
     * Checks if the x,y position is a wall. For object setting and monster setting
     * 
     * @param tiles The tile array
     * @param x     The x position
     * @param y     The y position
     */
    private boolean isWall(Tile[][] tiles, int x, int y) {
        boolean retVal;
        if (tiles[y][x].type == Tile.Type.WALL) {
            retVal = true;
        } else {
            retVal = false;
        }
        return retVal;
    }

    /**
     * Adds spawn protection around a given coordinate by setting the surrounding
     * spawn protection array values to true
     * 
     * @param posY The Y position of the entity/object
     * @param posX The X position of the entity/object
     * @param size The size of the spawn protection, centered around the X Y
     *             coordinate
     */
    private void setSpawnProtection(int posY, int posX, int size) {
        int topX = posX - (size - 1 / 2);
        if (topX < 0) {
            topX = 0;
        }
        int topY = posY - (size - 1 / 2);
        if (topY < 0) {
            topY = 0;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                spawnLocations[topY + j][topX + i] = true;
            }
        }
    }

    /**
     * Gets a random legal spawn position
     * 
     * @param tiles The spawnlocation tile map
     * @return a 2D array of the y,x position
     */
    public int[] getRandomPosition(Tile[][] tiles) {
        int xPos, yPos;
        do {
            xPos = rand.nextInt(29) + 1;
            yPos = rand.nextInt(29) + 1;
        } while (isWall(tiles, xPos, yPos) || spawnLocations[yPos][xPos]);

        int[] yxPos = { yPos, xPos };

        return yxPos;
    }

    /**
     * Sets diamonds in the object array with random positions
     * @param tiles Represents the tile map
     */
    public void setDiamond(Tile[][] tiles) {
        int xScale, yScale;
       diamondIndex = console.numGold + 1;
        int[] yxPos = getRandomPosition(tiles);

        yScale = yxPos[0];
        xScale = yxPos[1];

        obj[diamondIndex] = console.diamond;
        obj[diamondIndex].worldX = xScale * tileSize;
        obj[diamondIndex].worldY = yScale * tileSize;
    }

    /**
     * Sets the objects in the array with random positions
     *
     * @param tiles Represnts the tile map
     */
    public void setObject(Tile[][] tiles) {
        int xScale, yScale, doorX, doorY;
       int numGold = console.numGold;
       openDoorIndex = console.numGold + 2;
       closedDoorIndex = console.numGold + 3;
        // Set player spawn protection
        setSpawnProtection(mapSize - 1, mapSize / 2 + 2, 5);

        // Adding all the newly created objects to an object array
        obj[openDoorIndex] = new DoorClosed();
        doorX = mapSize / 2;
        doorY = 1;
        obj[openDoorIndex].worldX = doorX * tileSize;
        obj[openDoorIndex].worldY = doorY * tileSize;
        spawnLocations[doorY][doorX] = true;
        setSpawnProtection(doorY, doorX, 3);

        for (int i = 0; i < numGold; i++) {
            int[] yxPos = getRandomPosition(tiles);
            yScale = yxPos[0];
            xScale = yxPos[1];
            obj[i] = new Gold();
            obj[i].worldX = xScale * tileSize;
            obj[i].worldY = yScale * tileSize;
            spawnLocations[yScale][xScale] = true;
        }

        obj[closedDoorIndex] = new DoorOpen();
        obj[closedDoorIndex].worldX = 16 * tileSize;
        obj[closedDoorIndex].worldY = 30 * tileSize;
    }

    /**
     * Sets the monsters in the array with random positions
     * @param tiles  The spawnlocation tile map
     * @param numMoveMonster represents the number of moving monsters
     * @param numStaticMonster represents the number of static monster
     */
    public void setMonsters(Tile[][] tiles, int numMoveMonster, int numStaticMonster) {
        int xScale, yScale;
        Monster[] monsters = console.gameSetter.getMonster();

        for (int i = 0; i < numMoveMonster; i++) {
            int[] yxPos = getRandomPosition(tiles);
            yScale = yxPos[0];
            xScale = yxPos[1];
            monsters[i] = new Monster(console, true);
            monsters[i].worldPosX = xScale * tileSize;
            monsters[i].worldPosY = yScale * tileSize;
            spawnLocations[yScale][xScale] = true;
        }

        for (int i = numMoveMonster; i < numMoveMonster + numStaticMonster; i++) {
            int[] yxPos = getRandomPosition(tiles);
            yScale = yxPos[0];
            xScale = yxPos[1];
            monsters[i] = new Monster(console, false);
            monsters[i].worldPosX = xScale * tileSize;
            monsters[i].worldPosY = yScale * tileSize;
            spawnLocations[yScale][xScale] = true;
        }

    }


    /**
     * gets the number of moving monsters
     * @return number of moving monsters
     */
    public int getNumMoveMonster(){
        return numMoveMonster;
    }

    /**
     * gets the number of static monsters
     * @return number of static monster
     */
    public int getNumStaticMonster(){
        return numStaticMonster;
    }
}
