package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  BrownDirt tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class BrownDirt extends Tile {
    /**
     * Texture image which represents the {@link BrownDirt} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new BrownDirt object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected BrownDirt() {
        super.type = Type.BROWN_DIRT;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Brown Dirt sprite unique to the {@link BrownDirt} class.
     *   If the image does not already exist, this method will generate it.
     *  @return     A Brown Dirt sprite generated from /tiles/dirt_brown.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(BrownDirt.class.getResourceAsStream("/tiles/dirt_brown.png")));
        else return texture;
    }
}
