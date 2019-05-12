package com.game;

import javax.swing.*;

import static java.lang.Integer.parseInt;

/**
 * Klasa obsługująca i tworząca poziomy gry
 */

public class Level {
    private int currentLevel = 0;
    int numberOfPumpkinObjects;
    public int timeForShooting;

    //czas wyświetlania okna dialogowego
    public long pauseTime = 0;

    /**
     * Konstruktor klasy Level ładujący dane z odpowiedniego pliku poziomu
     */
    public Level(){
        GameReader.loadParametricFile("res/poziom0.txt");
        this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
        this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
    }

    /**
     * Metoda tworząca poziom gry uzależniony od liczby punktów
     * @param points Liczba punktów gracza
     */
    public void createLevel(int points){
        if(points == 0){
            GameReader.loadParametricFile("res/poziom0.txt");
            this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
            this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
        }
        else if(points == 1000){
            GameReader.loadParametricFile("res/poziom1.txt");
            this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
            this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
        }
        else if(points == 2000){
            GameReader.loadParametricFile("res/poziom2.txt");
            this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
            this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
        }
    }

    /**
     * Metoda zwiększająca poziom gry oraz wyświetlająca komunikat
     * o odblokowaniu nowego poziomu
     * @param points Liczba punktów gracza
     */
    public void increaseLevel(int points){
        if(points >= 0 && points < 1000){
            this.currentLevel = 0;
        }

        else if(points >= 1000 && points < 2000){
            this.currentLevel = 1;
            if(points == 1000){
                Game.mainWindow.pause(false);
                long startTime = System.currentTimeMillis();
                Sound.playSound("res/sound/nowypoziom.wav");
                for (int i = 0; i < Game.mainWindow.tableOfPumpkins.length; i++) {
                    if(Game.mainWindow.buttonsTable[i] != null){
                        Game.mainWindow.panel_01.remove(Game.mainWindow.buttonsTable[i]);
                        Game.mainWindow.buttonsTable[i] = null;
                        Game.mainWindow.panel_01.revalidate();
                        Game.mainWindow.panel_01.repaint();
                    }
                }
                JOptionPane.showMessageDialog(Game.mainWindow.mainFrame, "Odblokowałeś nowy poziom", "Etap gry", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("res/minka.png"));
                long endTime = System.currentTimeMillis();
                this.pauseTime = endTime - startTime;
                Game.mainWindow.pause(true);
            }

        }

        else{
            this.currentLevel = 2;
            if(points == 2000){
                Game.mainWindow.pause(false);
                long startTime = System.currentTimeMillis();
                Sound.playSound("res/sound/nowypoziom.wav");
                for (int i = 0; i < Game.mainWindow.tableOfPumpkins.length; i++) {
                    if(Game.mainWindow.buttonsTable[i] != null){
                        Game.mainWindow.panel_01.remove(Game.mainWindow.buttonsTable[i]);
                        Game.mainWindow.buttonsTable[i] = null;
                        Game.mainWindow.panel_01.revalidate();
                        Game.mainWindow.panel_01.repaint();
                    }
                }
                JOptionPane.showMessageDialog(Game.mainWindow.mainFrame, "Odblokowałeś nowy poziom!", "Etap gry", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("res/minka.png"));
                long endTime = System.currentTimeMillis();
                this.pauseTime += endTime - startTime;
                Game.mainWindow.pause(true);
            }
        }

    }


    /**
     * Metoda zwracająca obecny poziom
     * @return  obecny poziom
     */
    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getNumberOfPumpkinObjects(){
        return numberOfPumpkinObjects;
    }
}
