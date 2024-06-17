package renderer;
import org.junit.jupiter.api.Test;
import primitives.Color;


public class ImageWriterTests {


    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % 50 == 0 || j % 50 == 0) { // every 50 pixels
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.cyan));
                }
                else {
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.LIGHT_GRAY));
                }
            }
        }
        imageWriter.writeToImage();

    }
}
