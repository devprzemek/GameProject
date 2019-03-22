package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DuckObject {

    private BufferedImage image;

    private int x_coordinate;
    private int y_coordinate;

    public DuckObject(BufferedImage image){

        this.image = image;
        Random generator = new Random();
        this.x_coordinate = generator.nextInt(800);
        this.y_coordinate = generator.nextInt(800);
    }


    public void render(Graphics g){
        g.drawImage(image, x_coordinate, y_coordinate, 50, 50,  null );
    }

}
