package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class PumpkinObject {

    public BufferedImage image;

    private double sizeOfObject;

    private int x_coordinate;
    private int y_coordinate;

    public PumpkinObject(BufferedImage image){

        this.image = image;
        this.sizeOfObject = Double.parseDouble(GameReader.props.getProperty("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy"));;
        Random generator = new Random();
        this.x_coordinate = generator.nextInt(Game.WIDTH); //parametr
        this.y_coordinate = generator.nextInt(Game.HEIGHT);
    }


    public static Image resize(BufferedImage img, double sizeOfObject) {
        int width = (int) (sizeOfObject * Game.WIDTH * 0.01);
        int height = (int) (sizeOfObject * Game.WIDTH * 0.01);



        Image newImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return newImage;
    }

    public double getSizeOfObject(){
        return sizeOfObject;
    }

    public int getX_coordinate(){
        return x_coordinate;
    }
    public int getY_coordinate(){
        return y_coordinate;
    }
}
