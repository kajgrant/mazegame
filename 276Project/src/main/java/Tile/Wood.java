package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Wood tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Wood extends Tile {
    /**
     * Texture image which represents the {@link Wood} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Wood object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Wood() {
        super.type = Type.WOOD;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Wood sprite unique to the {@link Wood} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A Wood sprite generated from /tiles/wood.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Wood.class.getResourceAsStream("/tiles/wood.png")));
        else return texture;
    }
}
