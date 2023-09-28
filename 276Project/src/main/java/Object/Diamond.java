package Object;

import Game.GameConsole;
import Game.AssetSetter;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

/**
 * Diamond object, fields inherited from {@link SuperObject} class
 * 
 * @author bkh6
 * @author kgrantma
 */
public class Diamond extends SuperObject {

    /**
     * {@link GameConsole} object to be referenced from
     */
    GameConsole console;

    /**
     * Represents time that the diamond randomly appears on screen
     */
    public double diamondSpawnTime;
    private double timeOnScreen = 5.0;

    private Random rand = new Random();

    /**
     * Constructs a {@link SuperObject} object with images and a name.
     * 
     * //@param console {@link GameConsole} to reference from
     */
    public Diamond(GameConsole console) {
        this.console = console;
        name = "Diamond";
        diamondSpawnTime = getRandomTime();
        isHidden = true;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/diamond.png"));
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Updates the diamond to spawn/despawn and choose a new location
     * 
     * @param aSetter The asset setter object to get a random legal position
     * @param tiles   The tiles map object for generating a legal position
     */
    public void update(AssetSetter aSetter, Tile[][] tiles) {
        double currentTime = console.getGameTime();
        if (currentTime > (diamondSpawnTime + timeOnScreen)) {
            if (!isHidden) {
                console.ui.addMessage("Diamond is gone :(", 3);
            }
            isHidden = true;
            diamondSpawnTime = getRandomTime();
            int[] yxPos = aSetter.getRandomPosition(tiles);
            worldY = yxPos[0] * tileSize;
            worldX = yxPos[1] * tileSize;
        } else if (currentTime >= diamondSpawnTime && currentTime < diamondSpawnTime + 1) {
            // SHOW MESSAGE THAT DIAMOND IS AVAILABLE
            isHidden = false;
            console.ui.addMessage("Diamond is available at x: " + worldX / tileSize + " y: "
                    + worldY / tileSize + "!", 3);
        }
    }

    /**
     * Method to get random times
     * 
     * @return Returns random time
     */
    public double getRandomTime() {
        double time = console.getGameTime() + rand.nextInt(10) + 5;
        return time;
    }

}
