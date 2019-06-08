package com.game;

import java.io.*;
import java.util.Properties;

/**
 * Klasa ładująca plik parametryczny gry
 */
public class GameReader {

    public static Properties props;

    public static void loadParametricFile (String fileName) {

        props = new Properties();

        try (Reader r = new BufferedReader(new FileReader(fileName))) {
            props.load(r);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nie znaleziono pliku parametrycznego");

        } catch (IOException ioe) {
            System.out.println("Wystapil blad odczytu pliku parametrycznego");
        }
    }
}
