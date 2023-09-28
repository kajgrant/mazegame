package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The utility class is a class that has methods that are universally used by many classes
 * @author bkh6
 */
public class Utility {

    /**
     * Creates a scaled image of the sprite
     * @param original Buffered image that is taken from the resource folder, original 16x16 pixels
     * @param width The width of the image, uses the defined tileSize
     * @param height The height of the image, uses the defined tileSize
     * @return A scaled image
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0,0,width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
