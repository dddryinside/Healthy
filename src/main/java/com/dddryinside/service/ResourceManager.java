package com.dddryinside.service;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceManager {
    public static InputStream loadResource(String uri) {
        return ResourceManager.class.getResourceAsStream(uri);
    }

/*    public static void saveAvatar(File image) {
        Path source = Paths.get(image.getAbsolutePath());
        Path target = Paths.get("mental-files/avatars", SecurityManager.getUser().getUsername() + ".png");
        try {
            Files.createDirectories(target.getParent());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void saveImageToResources(Image image, String fileName) throws IOException {
        // Validate file name
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        // Get the path to the resources/img directory
        String resourcePath = "src/main/resources/img/";

        // Create the directory if it does not exist
        File directory = new File(resourcePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create the file in the resources/img directory
        File outputFile = new File(directory, fileName);

        // Convert the JavaFX Image to a BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        // Write the BufferedImage to the file in PNG format
        ImageIO.write(bufferedImage, "png", outputFile);
    }
}
