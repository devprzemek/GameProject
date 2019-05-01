package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Klasa ładująca plik graficzny do zestrzelenia
 */

public class PumpkinObject {

    public BufferedImage image;

    private double sizeOfObject;
    private int x_coordinate;
    private int y_coordinate;

    public PumpkinObject(BufferedImage image){

        this.image = image;
        this.sizeOfObject = Double.parseDouble(GameReader.props.getProperty("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy"));;
        Random generator = new Random();

        this.x_coordinate = generator.nextInt(Game.mainWindow.mainFrame.getSize().width - 150); //parametr
        this.y_coordinate = generator.nextInt(Game.mainWindow.mainFrame.getSize().height - 100);
    }

    public static Image resize(BufferedImage img, double sizeOfObject) {
        int width = (int) (sizeOfObject * (Game.WIDTH + (Game.mainWindow.mainFrame.getSize().width - Game.WIDTH)) * 0.01);
        int height = (int) (sizeOfObject * (Game.HEIGHT + (Game.mainWindow.mainFrame.getSize().height - Game.HEIGHT)) * 0.01);

        Image newImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return newImage;
    }

    public double getSizeOfObject(){
        return sizeOfObject;
    }

    public void setX_coordinate(int x){
        x_coordinate = x;
    }

    public void setY_coordinate(int y){
        y_coordinate = y;
    }

    public int getX_coordinate(){
        return x_coordinate;
    }
    public int getY_coordinate(){
        return y_coordinate;
    }
}
