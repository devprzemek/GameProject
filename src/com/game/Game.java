package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Collections;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

/**
 * Główna klasa obsługująca mechanizm gry
 */

public class Game extends Canvas implements Runnable {

    public static int WIDTH;
    public static int HEIGHT;
    public static String TITLE;
    public static String backGroundColor;

    public static long timeOfGame;
    public static int numberOfLevels;
    public static int numberOfDifficultyLevels;
    public static int difficultyRate;
    public static int numberOfObjects;

    public static TreeMap<Integer, String> bestResults = new TreeMap<>(Collections.reverseOrder());

    private boolean running = false;
    public static Thread thread;

    private Menu menu;
    public static Level level = new Level();
    public static Player player;
    public static MainWindow mainWindow;
    public static GameResult gameResults;
    public static char[] backgroundColorTable;

    public enum GAME_STATE {
        MENU,
        GAME
    }
    public static GAME_STATE state = GAME_STATE.MENU;

    /**
     * Inicjalizacja zmiennych w klasie Game wartościami z pliku parametrycznego
     */

    public Game() {
        GameReader.loadParametricFile("res/par.txt");
        this.WIDTH = parseInt(GameReader.props.getProperty("początkowaSzerokośćPlanszy"));
        this.HEIGHT = parseInt(GameReader.props.getProperty("początkowaWysokośćPlanszy"));
        this.TITLE = GameReader.props.getProperty("nazwaGry");
        this.numberOfLevels = parseInt(GameReader.props.getProperty("liczbaPoziomów"));
        this.numberOfDifficultyLevels = parseInt(GameReader.props.getProperty("liczbaStopniTrudności"));
        this.difficultyRate = parseInt(GameReader.props.getProperty("zmianaStopniaTrudności")) / 100;
        this.backGroundColor = GameReader.props.getProperty("klorTła");
        backgroundColorTable = backGroundColor.toCharArray();
        numberOfObjects = 4;
    }

    public void init(){
        menu = new Menu();
        this.addMouseListener(new MouseInput());
    }

    public synchronized void start(){
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void run(){
        init();
        while(running){
            render();

        }
        stop();
    }

    private void render(){

        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bufferStrategy.getDrawGraphics();

        if (state == GAME_STATE.GAME){
            mainWindow.drawPumpkinObjects();

        }
        else if (state == GAME_STATE.MENU){
            menu.render(g);
        }

        g.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
        Game game = new Game();

        game.setPreferredSize(new Dimension(Menu.WIDTH , Menu.HEIGHT ));
        game.setMaximumSize(new Dimension(Menu.WIDTH , Menu.HEIGHT));
        game.setMinimumSize(new Dimension(Menu.WIDTH , Menu.HEIGHT ));

        JFrame frame0 = new JFrame("MENU");
        frame0.getContentPane().setBackground(Color.DARK_GRAY);
        frame0.add(game);
        frame0.pack();
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setResizable(true);
        frame0.setLocationRelativeTo(null);
        frame0.setVisible(true);

        game.start();
        });
    }
}