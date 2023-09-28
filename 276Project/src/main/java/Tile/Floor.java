package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Floor tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Floor extends Tile {
    /**
     * Texture image which represents the {@link Floor} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Floor object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Floor() {
        super.type = Type.DIRT;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Floor sprite unique to the {@link Floor} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A Floor sprite generated from /tiles/floor.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Floor.class.getResourceAsStream("/tiles/floor.png")));
        else return texture;
    }
}
