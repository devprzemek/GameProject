package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import static java.lang.Integer.parseInt;

/**
 * Główna klasa obsługująca mechanizm gry
 */

public class Game extends Canvas implements Runnable {

    public static int WIDTH;
    public static int HEIGHT;
    public static String TITLE;
    public static int numberOfLevels;
    public static String backGroundColor;

    private boolean running = false;
    private Thread thread;

    private Menu menu;
    public static Player player = new Player();
    public static MainWindow mainWindow;
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
        GameReader.loadParametricFile("par.txt");
        this.WIDTH = parseInt(GameReader.props.getProperty("początkowaSzerokośćPlanszy"));
        this.HEIGHT = parseInt(GameReader.props.getProperty("początkowaWysokośćPlanszy"));
        this.TITLE = GameReader.props.getProperty("nazwaGry");
        this.numberOfLevels = parseInt(GameReader.props.getProperty("liczbaPoziomów"));
        this.backGroundColor = GameReader.props.getProperty("klorTła");
        backgroundColorTable = backGroundColor.toCharArray();
    }

    public void init(){
        menu = new Menu();
        this.addMouseListener(new MouseInput());
    }

    private synchronized void start(){
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
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
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta > 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();

    }

    private void tick(){
        if (state == GAME_STATE.GAME) {

        }
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

    //test

    public static void main(String[] args){

        Game game = new Game();

        game.setPreferredSize(new Dimension(Menu.WIDTH , Menu.HEIGHT ));
        game.setMaximumSize(new Dimension(Menu.WIDTH , Menu.HEIGHT));
        game.setMinimumSize(new Dimension(Menu.WIDTH , Menu.HEIGHT ));

        JFrame frame0 = new JFrame("MENU");
        frame0.add(game);
        frame0.pack();
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setResizable(true);
        frame0.setLocationRelativeTo(null);
        frame0.setVisible(true);

        game.start();

    }
}