package com.game;

import java.awt.image.BufferedImage;

public class DuckObject {

    private BufferedImage image;

    public DuckObject(BufferedImage image){
        this.image = image;
    }

//    public BufferedImage grabImage(int column, int row, int width, int height){
//        BufferedImage img = image.getSubimage((column * 32) - 32, (row * 32) - 32, width, height);
//        return img;
//    }
}
