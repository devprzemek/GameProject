package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 440;
    public static final int HEIGHT = 406;
    public static final String TITLE = "Strzelanie";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

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
                System.out.println(updates + " " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();

    }

    private void tick(){
    }

    private void render(){

        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bufferStrategy.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        g.dispose();
        bufferStrategy.show();

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
