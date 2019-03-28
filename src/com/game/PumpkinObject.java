package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class PumpkinObject {

    private BufferedImage image;
    private double sizeOfObject;

    private int x_coordinate;
    private int y_coordinate;

    public PumpkinObject(BufferedImage image){

        this.image = image;
        this.sizeOfObject = Double.parseDouble(GameReader.props.getProperty("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy"));;
        Random generator = new Random();
        this.x_coordinate = generator.nextInt(Game.WIDTH);
        this.y_coordinate = generator.nextInt(Game.HEIGHT);
    }

    public void render(Graphics g){
        g.drawImage(image, x_coordinate, y_coordinate, (int) (sizeOfObject * Game.WIDTH * 0.01), (int) (sizeOfObject * Game.WIDTH * 0.01),  null );
    }

}
