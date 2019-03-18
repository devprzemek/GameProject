package com.game;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 440;
    public static final int HEIGHT = 406;
    public static final String TITLE = "Strzelanie";

    private boolean running = false;
    private Thread thread;

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

        System.exit(1);
    }

    public void run(){
        while(running){
            System.out.println("Working");
        }
        stop();
    }

    public static void main(String[] args){
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH , HEIGHT ));
        game.setMaximumSize(new Dimension(WIDTH , HEIGHT ));
        game.setMinimumSize(new Dimension(WIDTH , HEIGHT ));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }


}
