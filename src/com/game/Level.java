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
    public int currentDifficultyLevel = 0;

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
            Game.numberOfObjects = 4;
            currentDifficultyLevel = 0;
            GameReader.loadParametricFile("res/poziom0.txt");
            this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
            this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
        }
        if(points == 2200){
            GameReader.loadParametricFile("res/poziom1.txt");
            this.numberOfPumpkinObjects = parseInt(GameReader.props.getProperty("liczbaObiektówDoZestrzelenia"));
            this.timeForShooting = parseInt(GameReader.props.getProperty("czasNaZestrzelenieObiektu"));
        }
        if(points == 4400){
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
        if(points >= 0 && points < 2200){
            this.currentLevel = 0;
        }

        else if(points >= 2200 && points < 4400){
            this.currentLevel = 1;
            if(points == 2200){
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
            if(points == 4400){
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

    public void increaseDifficultyLevel(int points){
        if(points == 0 || points == 2200 || points == 4400){
            Game.numberOfObjects = 4;
            currentDifficultyLevel = 0;
        }
        else if(points == 400 || points == 2600 || points == 4800){
            Game.numberOfObjects = 5;
            currentDifficultyLevel = 1;
        }
        else if(points == 900 || points == 3100 || points == 5300){
            Game.numberOfObjects = 6;
            currentDifficultyLevel = 2;
        }
        else if(points == 1500 || points == 3700 || points == 5900){
            Game.numberOfObjects = 7;
            currentDifficultyLevel = 3;
        }
    }

    /**
     * Metoda zwracająca obecny poziom
     * @return  obecny poziom
     */
    public int getCurrentLevel(){
        return currentLevel;
    }

}
