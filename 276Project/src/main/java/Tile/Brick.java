package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Brick tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Brick extends Tile {
    /**
     * Texture image which represents the {@link Brick} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Brick object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Brick() {
        super.type = Type.BRICK;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage Brick sprite unique to the {@link Brick} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A Brick sprite generated from /tiles/Brick.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Brick.class.getResourceAsStream("/tiles/brick.png")));
        else return texture;
    }
}
