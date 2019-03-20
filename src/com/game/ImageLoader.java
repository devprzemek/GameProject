package com.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path) {
        try{
           image = ImageIO.read(getClass().getResourceAsStream(path));
           return image;
        }

        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        return image;
    }
}