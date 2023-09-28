package Object;

import Game.GameConsole;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Gold object, fields inherited from {@link SuperObject} class
 * 
 * @author bkh6
 */
public class Gold extends SuperObject {
    /**
     * {@link GameConsole} object to be referenced from
     */

    /**
     * Constructs a {@link SuperObject} object with images and a name.
     */
    public Gold() {
        name = "Gold";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gold.png"));
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
