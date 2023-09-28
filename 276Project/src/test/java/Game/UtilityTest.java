package Game;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;

public class UtilityTest {

    @Test
    public void scaledCheck(){
        BufferedImage img = null;
        Utility uTool = new Utility();
        try {
            img = ImageIO.read(getClass().getResource( "/player/backLeft.png"));
            img = uTool.scaleImage(img, 48,48);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assert(img.getHeight() == 48 && img.getWidth() == 48);
    }
}
