package Object;

import Game.GameConsole;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Open Door object, fields inherited from {@link SuperObject} class
 * 
 * @author bkh6
 */

public class DoorOpen extends SuperObject {


    /**
     * Constructs a {@link SuperObject} object with images and a name.
     */
    public DoorOpen() {

        name = "DoorOpen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpen.png"));
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
