package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static java.lang.Thread.sleep;

/**
 * Klasa obsługująca główne okno gry z animacją rozszerzająca klasę JFrame
 */

public class MainWindow extends JFrame {

    public PumpkinObject[] tableOfPumpkins = new PumpkinObject[Game.numberOfObjects];
    private ImageLoader imageLoader = new ImageLoader();
    public JButton[] buttonsTable = new JButton[Game.numberOfObjects];

    public JFrame mainFrame;
    public JPanel panel_01 = new JPanel();
    public JPanel panel_02 = new JPanel();

    private String text = "<html>Liczba żyć: <br/><br/><br/>Numer poziomu: <br/><br/><br/>Czas gry: <br/><br/><br/><br/><br/><br/>Liczba punktów: </html>";
    private JLabel panel_02text = new JLabel(text);

    private final long startTime;
    private long currentTime;

    private boolean running = true;

    /**
     * Konstruktor klasy MainWindow
     */
    public MainWindow() {
        panel_02text.setForeground(Color.RED);
        panel_01.setLayout(null);

        panel_01.setBackground(new Color(Game.backgroundColorTable[0] + Game.backgroundColorTable[1], 216, 131));
        panel_02.setBackground(Color.DARK_GRAY);
        panel_02.add(panel_02text);

        mainFrame = new JFrame(Game.TITLE);
        mainFrame.setDefaultCloseOperation(mainFrame.getDefaultCloseOperation());
        mainFrame.setSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        mainFrame.setPreferredSize(new Dimension(Game.WIDTH + (mainFrame.getSize().width - Game.WIDTH), Game.HEIGHT + (mainFrame.getSize().height - Game.HEIGHT)));

        mainFrame.getContentPane().setBackground(Color.BLACK);

        mainFrame.add(panel_01, BorderLayout.CENTER);
        mainFrame.add(panel_02, BorderLayout.EAST);
        mainFrame.addMouseMotionListener(new MouseMotionHandler());

        mainFrame.setResizable(true);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        Game.level.createLevel(Game.player.points);
        startTime = System.currentTimeMillis();
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

    /**
     * Metoda tworząca obiekty do zestrzelenia
     */
    public void createPumpkinObjects() {
        for (int i = 0; i < tableOfPumpkins.length; i++) {
            tableOfPumpkins[i] = new PumpkinObject(imageLoader.loadImage("/pumpkin.png"));
            buttonsTable[i] = new JButton();

            int finalI = i;
            buttonsTable[i].addActionListener(event -> {
                Sound.playSound("res/sound/strzelanie2.wav");
                panel_01.remove(buttonsTable[finalI]);
                buttonsTable[finalI] = null;
                panel_01.revalidate();
                panel_01.repaint();
                Game.player.points += 100;
                Game.level.increaseLevel(Game.player.points);
                if(Game.player.points == 1000 || Game.player.points == 2000)
                Game.level.createLevel(Game.player.points);
            });

            Image resizedImage = PumpkinObject.resize(tableOfPumpkins[i].image, tableOfPumpkins[i].getSizeOfObject());

            buttonsTable[i].setIcon(new ImageIcon(resizedImage));
            buttonsTable[i].setBounds(tableOfPumpkins[i].getX_coordinate(), (int) tableOfPumpkins[i].getY_coordinate(), (int) (tableOfPumpkins[i].getSizeOfObject() * (Game.WIDTH + (mainFrame.getSize().width - Game.WIDTH)) * 0.01), (int) (tableOfPumpkins[i].getSizeOfObject() * (Game.WIDTH + (mainFrame.getSize().height - Game.HEIGHT)) * 0.01));
            panel_01.add(buttonsTable[i]);
            panel_01.repaint();
        }
    }

    /**
     * Metoda rysująca obiekty do zestrzelenia oraz wygląd głównego okna gry
     */
    public void drawPumpkinObjects() {
        while(running){
            double windowWidth = mainFrame.getSize().width;

            currentTime = System.currentTimeMillis();
            Game.timeOfGame = ((currentTime - startTime) - Game.level.pauseTime) / 1000 - 1;
            //text = "<html>Liczba żyć: <br/><html>" + Game.player.numberOfLives + "<html><br/><br/><br/><br/><br/>Numer poziomu: <br/><html>" + Game.level.getCurrentLevel() + "<html><br/><br/><br/><br/><br/>Czas gry: <br/><html>" + Game.timeOfGame + " [s]" + "<html><br/><br/><br/><br/><br/>Liczba punktów: <br/><html>" + Game.player.points;
            text = "<html>Liczba żyć: <br/><html>" + Game.player.numberOfLives + "<html><br/><br/><br/><br/>Numer poziomu: <br/><html>" + Game.level.getCurrentLevel() + "<html><br/><br/><br/><br/>Czas gry: <br/><html>" + Game.timeOfGame + " [s]" + "<html><br/><br/><br/><br/>Liczba punktów: <br/><html>" + Game.player.points + "<html><br/><br/><br/><br/>Poz. trudności: <br/><html>" + 0;
            panel_02text.setText(text);

            if(frameSizeChanged(windowWidth)){
                scale(mainFrame.getSize().width, mainFrame.getSize().height);
            }

            if(Game.player.numberOfLives == 0){
                running = false;
                Game.gameResults.saveResult();
                Game.state = Game.GAME_STATE.MENU;

                Sound.playSound("res/sound/koniecgry.wav");
                //wywołanie okna informującego o końcu gry
                JDialog dialog = new GameDialogWindow(this);
            }

            if(Game.timeOfGame  % Game.level.timeForShooting == 0 && Game.timeOfGame > 0 && Game.player.numberOfLives > 0){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                removeLeftObjects();
                createPumpkinObjects();
            }
        }
    }

    /**
     * Metoda usuwająca niezestrzelone obiekty
     */
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

    /**
     * Metoda skalująca rozmiar obiektów i ich położenie
     */
    public void scale(double previousWidth, double previousHeight){
        for (int i = 0; i < tableOfPumpkins.length; i++) {
            if(buttonsTable[i] != null){

                //skalowanie współrzędnych
                int newX = (int) (tableOfPumpkins[i].getX_coordinate() + (mainFrame.getSize().width - previousWidth));

                tableOfPumpkins[i].setX_coordinate(newX);

                int newY = (int) (tableOfPumpkins[i].getY_coordinate() + (mainFrame.getSize().height - previousHeight)) ;
                tableOfPumpkins[i].setY_coordinate(newY);

                //skalowanie rozmiaru obiektów
                Image resizedImage = PumpkinObject.resize(tableOfPumpkins[i].image, tableOfPumpkins[i].getSizeOfObject());
                buttonsTable[i].setIcon(new ImageIcon(resizedImage));
                buttonsTable[i].setBounds(tableOfPumpkins[i].getX_coordinate(), tableOfPumpkins[i].getY_coordinate(), (int) (tableOfPumpkins[i].getSizeOfObject() * (Game.WIDTH + (mainFrame.getSize().width - Game.WIDTH)) * 0.01), (int) (tableOfPumpkins[i].getSizeOfObject() * (Game.WIDTH + (mainFrame.getSize().height - Game.HEIGHT)) * 0.01));
            }
        }
    }

    /**
     * Metoda sprawdzająca czy rozmiar okna głównego uległ zmianie
     * @param size Poprzednia wielkość okna
     * @return Wartość logiczna informująca o zmianie rozmiaru okna lub nie
    */
    public boolean frameSizeChanged(double size){
        if(mainFrame.getSize().width != size){
            return true;
        }
        else
            return false;
    }

    public void pause(boolean b){
        running = b;
    }
}

