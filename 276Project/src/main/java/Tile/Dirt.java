package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Dirt tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Dirt extends Tile {
    /**
     * Texture image which represents the {@link Dirt} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Dirt object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Dirt() {
        super.type = Type.DIRT;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage dirt sprite unique to the {@link Dirt} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A dirt sprite generated from /tiles/dirt.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Dirt.class.getResourceAsStream("/tiles/dirt.png")));
        else return texture;
    }
}
