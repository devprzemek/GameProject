package com.game;

import java.io.*;
import java.util.Properties;

public class GameReader {

    public static Properties props;

    public static void loadParametricFile (String fileName) {

        props = new Properties();

        try (Reader r = new BufferedReader(new FileReader("res/par.txt"))) {
            props.load(r);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nie znaleziono pliku parametrycznego");

        } catch (IOException ioe) {
            System.out.println("Wystapil blad odczytu pliku parametrycznego");
        }
    }
}