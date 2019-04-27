package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;


/**
 * Klasa obsługująca główne okno gry
 */

public class MainWindow extends JFrame {

    private PumpkinObject[] tableOfPumpkins = new PumpkinObject[5];
    private ImageLoader imageLoader = new ImageLoader();
    private JButton[] buttonsTable = new JButton[5];

    private JFrame mainFrame;
    private JPanel panel_01 = new JPanel();
    public JPanel panel_02 = new JPanel();

    private int click = 1;
    private String text = "<html>Liczba żyć: <br/><br/><br/><br/><br/><br/><br/>Czas gry: <br/><br/><br/><br/><br/><br/><br/>Liczba punktów: </html>";
    private JLabel panel_02text = new JLabel(text);

    private final long startTime;
    private long currentTime;

    private boolean running = true;

    public MainWindow() {
        startTime = System.currentTimeMillis();

        panel_02text.setForeground(Color.RED);
        panel_01.setLayout(null);

        panel_01.setBackground(new Color(Game.backgroundColorTable[0] + Game.backgroundColorTable[1], 216, 131));
        panel_02.setBackground(Color.DARK_GRAY);
        panel_02.add(panel_02text);

        mainFrame = new JFrame(Game.TITLE);
        mainFrame.setDefaultCloseOperation(mainFrame.getDefaultCloseOperation());
        mainFrame.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        mainFrame.setResizable(true);
        mainFrame.getContentPane().setBackground(Color.BLACK);

        mainFrame.add(panel_01, BorderLayout.CENTER);
        mainFrame.add(panel_02, BorderLayout.EAST);
        mainFrame.addMouseMotionListener(new MouseMotionHandler());

        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        init();

    }

    private class MouseMotionHandler implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getBestCursorSize(100, 100);
            Image img = tk.getImage("res/celownik.png");
            Cursor cursor = tk.createCustomCursor(img, new Point(10,10), null);
            panel_01.setCursor(cursor);
        }
    }

    public void init() {
        for (int i = 0; i < tableOfPumpkins.length; i++) {
            tableOfPumpkins[i] = new PumpkinObject(imageLoader.loadImage("/pumpkin.png"));
            buttonsTable[i] = new JButton();

            int finalI = i;
            buttonsTable[i].addActionListener(event -> {
                click += 1;
                panel_01.remove(buttonsTable[finalI]);
                buttonsTable[finalI] = null;
                panel_01.revalidate();
                panel_01.repaint();
                Game.player.points += 100;
            });

            BufferedImage image = tableOfPumpkins[i].image;
            Image resizedImage = PumpkinObject.resize(image, tableOfPumpkins[i].getSizeOfObject());

            buttonsTable[i].setIcon(new ImageIcon(resizedImage));
            buttonsTable[i].setBounds(tableOfPumpkins[i].getX_coordinate(), (int) tableOfPumpkins[i].getY_coordinate(), (int) (tableOfPumpkins[i].getSizeOfObject() * Game.WIDTH * 0.01), (int) (tableOfPumpkins[i].getSizeOfObject() * Game.WIDTH * 0.01));
            panel_01.add(buttonsTable[i]);
            panel_01.repaint();
        }
    }

    public void drawPumpkinObjects() {
        while(running){
            currentTime = System.currentTimeMillis();
            Game.timeOfGame = (currentTime - startTime) / 1000;
            String textPoints = "<html>Liczba żyć: <br/><html>" + Game.player.numberOfLives + "<html><br/><br/><br/><br/><br/><br/><br/>Czas gry: <br/><html>" + Game.timeOfGame + " [s]" + "<html><br/><br/><br/><br/><br/><br/><br/>Liczba punktów: <br/><html>" + Game.player.points;
            panel_02text.setText(textPoints);

            if(Game.player.numberOfLives == 0){
                running = false;
                Game.gameResults.saveResult();
                //okno koca gry
            }

            if(Game.timeOfGame  % 5 == 0 && Game.player.numberOfLives > 0){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                removeLeftObjects();
                init();
            }
        }
    }

    public void removeLeftObjects() {
        for (int i = 0; i < tableOfPumpkins.length; i++) {
            if(buttonsTable[i] != null){
                if(Game.player.numberOfLives != 0){
                    Game.player.numberOfLives -= 1;
                }
                panel_01.remove(buttonsTable[i]);
                panel_01.revalidate();
                panel_01.repaint();
            }
        }
    }

}

