package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Sand tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Sand extends Tile {
    /**
     * Texture image which represents the {@link Sand} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Sand object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Sand() {
        super.type = Type.SAND;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Sand sprite unique to the {@link Sand} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A Sand sprite generated from /tiles/sand.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Sand.class.getResourceAsStream("/tiles/sand.png")));
        else return texture;
    }
}
