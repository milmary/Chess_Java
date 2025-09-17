package main.cz.cuni.milanenm.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Files {
    /**
     * Loads an image from the given path.
     *
     * @param path The path to the image file.
     * @return The loaded BufferedImage, or null if an error occurred.
     */
    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.err.println("An error while downloading an image");
        }
        return image;
    }
}
