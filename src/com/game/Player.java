package com.game;

/**
 * Klasa przechowująca informacje o graczu
 */

public class Player {
    private String name;
    private int points;
    private int numberOfLives;

    public void setName(String name){
        this.name = name;
    }

    public void setNumberOfLives(String gameCode){
        if(gameCode == "1111")
            this.numberOfLives = 5;
        else
            this.numberOfLives = 3;
    }

    public String getName(){
        return name;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }
}
