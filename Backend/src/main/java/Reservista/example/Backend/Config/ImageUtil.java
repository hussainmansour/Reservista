package Reservista.example.Backend.Config;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Component
public class ImageUtil {

    public static byte[] convertImageUrlToBytes(String imageUrl) {
        URI uri = URI.create(imageUrl);
        try (InputStream in = uri.toURL().openStream()) {
            return IOUtils.toByteArray(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
