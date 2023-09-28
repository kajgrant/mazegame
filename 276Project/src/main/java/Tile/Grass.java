package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Grass tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Grass extends Tile {
    /**
     * Texture image which represents the {@link Grass} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Grass object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Grass() {
        super.type = Type.DIRT;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Grass sprite unique to the {@link Grass} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A Grass sprite generated from /tiles/grass.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Grass.class.getResourceAsStream("/tiles/grass.png")));
        else return texture;
    }
}
