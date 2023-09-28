package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Earth tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Earth extends Tile {
    /**
     * Texture image which represents the {@link Earth} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Earth object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Earth() {
        super.type = Type.EARTH;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Earth sprite unique to the {@link Earth} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     An Earth sprite generated from /tiles/earth.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Earth.class.getResourceAsStream("/tiles/earth.png")));
        else return texture;
    }
}
