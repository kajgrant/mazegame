package Object;

import Game.GameConsole;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Closed Door object, fields inherited from {@link SuperObject} class
 * 
 * @author bkh6
 */
public class DoorClosed extends SuperObject {


    /**
     * Constructs a {@link SuperObject} object with images and a name.
     *
     */
    public DoorClosed() {
        name = "DoorClosed";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorClosed.png"));
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = true;
    }
}
