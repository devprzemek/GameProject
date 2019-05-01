package com.game;

import javax.swing.*;

import java.sql.Time;

import static java.lang.Integer.parseInt;

public class Level {
    private int currentLevel = 0;
    private int numberOfPumpkinObjects;
    public int timeForShooting;

    //czas wyświetlania okna dialogowego
    public long pauseTime = 0;

    public Level(){
        GameReader.loadParametricFile("res/poziom0.txt");
        this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
        this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
    }

    public void createLevel(int currentLevel){
        if(currentLevel == 1){
            //GameReader.loadParametricFile("poziom0.txt");
        }
        else{
            //GameReader.loadParametricFile("poziom0.txt");
        }
    }

    public void increaseLevel(int points){
        if(points >= 0 && points < 1000){
            currentLevel = 0;
        }

        else if(points >= 1000 && points < 2000){
            currentLevel = 1;
            if(points == 1000){
                long startTime = System.currentTimeMillis();
                Game.mainWindow.pause(false);
                JOptionPane.showMessageDialog(Game.mainWindow.mainFrame, "Odblokowałeś nowy poziom", "Etap gry", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("res/minka.png"));
                long endTime = System.currentTimeMillis();
                this.pauseTime = endTime - startTime;
                Game.mainWindow.pause(true);
            }

        }

        else{
            currentLevel = 2;
            if(points == 2000){
                long startTime = System.currentTimeMillis();
                Game.mainWindow.pause(false);
                JOptionPane.showMessageDialog(Game.mainWindow.mainFrame, "Odblokowałeś nowy poziom", "Etap gry", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("res/minka.png"));
                long endTime = System.currentTimeMillis();
                this.pauseTime += endTime - startTime;
                Game.mainWindow.pause(true);
            }
        }


    }

    public int getCurrentLevel(){
        return currentLevel;
    }
}
