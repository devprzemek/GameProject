package com.game;

/**
 * Klasa przechowująca informacje o graczu
 */

public class Player {
    public String name;
    public int points = 0;
    public int numberOfLives;

    public void setName(String name){
        this.name = name;
    }

    public void setNumberOfLives(String gameCode){

        if(gameCode.equals("1111"))
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
