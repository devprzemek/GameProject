package com.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

/**
 * Klasa obsługująca efekty dźwiękowe gry
 */

public class Sound {

    /**
     * Metoda odtwarzająca dany plik dźwiękowy
     * @param fileName nazwa pliku dżwiękowego
     */
    public static void playSound(String fileName){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Game.mainWindow.mainFrame, e);
        }
    }
}
