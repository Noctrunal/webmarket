package com.webmarket.application.util;

import com.webmarket.application.controller.exception.ImageUploadException;
import com.webmarket.application.controller.exception.ResourceUploadException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AmazonStorageUtil {
    private AmazonStorageUtil() {
    }

    public static void validationImage(MultipartFile image) {
        try {
            if (!image.getContentType().equals("image/jpeg")) {
                throw new ImageUploadException("Only JPG images accepted");
            }

            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if (width != 400 && height != 250) {
                throw new ImageUploadException("Image size must be 400 x 250 pixels");
            }

        } catch (IOException e) {
            throw new ResourceUploadException("Fail to upload image");
        }
    }
}
