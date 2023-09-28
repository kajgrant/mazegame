package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  Wall tile, incorporates singleton pattern to reduce load time when reusing assets.
 * @author smc26
 * @see Tile
 * @since 1.0
 */
public class Wall extends Tile {
    /**
     * Texture image which represents the {@link Wall} class.
     */
    private static BufferedImage texture = null;

    /**
     *  Constructs a new Wall object. Can only be created through {@link #makeTile(Type)}.
     *  @see #makeTile(Type)
     */
    protected Wall() {
        super.type = Type.WALL;

        try {
            image = getTexture();
            util.scaleImage(image, tileSize, tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *  Singleton pattern which returns a BufferedImage wall sprite unique to the {@link Wall} class.
     *  If the image does not already exist, this method will generate it.
     *  @return     A wall sprite generated from /tiles/wall.png.
     *  @throws     IOException If the sprite image is missing or unable to be opened
     */
    public static BufferedImage getTexture() throws IOException {
        if(texture == null)
            return (texture = ImageIO.read(Wall.class.getResourceAsStream("/tiles/wall.png")));
        else return texture;
    }
}
