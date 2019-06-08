package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

/**
 * Klasa tworząca okno z najlepszymi wynikami
 */
public class GameResult extends JFrame {

    private JFrame frame;

    /**
     * Konstruktor klasy GameResult
     */
    public GameResult() {

        frame = new JFrame("WYNIKI");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setPreferredSize(new Dimension(Menu.WIDTH / 2, Menu.HEIGHT / 2 ));
        frame.setDefaultCloseOperation(frame.getDefaultCloseOperation());
        frame.pack();
        frame.setResizable(true);
        frame.setLocation(618,250);
        frame.setVisible(true);

        showResults(Game.bestResults);
    }

    /**
     * Metoda zapisująca wynik gracza
     */
    public static void saveResult(){
        if(Game.bestResults.size() <= 4){
            Game.bestResults.putIfAbsent(Game.player.points, Game.player.name);
        }
        else {
            if(Game.player.points > Game.bestResults.lastKey()){
                Game.bestResults.remove(Game.bestResults.lastKey());
                Game.bestResults.put(Game.player.points, Game.player.name);
            }
        }
    }

    /**
     * Metoda wyświetlająca listę wyników
     * @parm list lista wyników
     */
    public void showResults(TreeMap<Integer, String> list){
        String text = "<html>Najlepsze wyniki:<br/><html>";

        for (Integer key : list.keySet()) {
            String value = list.get(key);
            text += "<html><html>" + value + "  " + key + "<html><br/><html>";
        }

        JLabel labelText = new JLabel(text, SwingConstants.CENTER);
        labelText.setFont(new Font("Helvetica", Font.PLAIN, 24));
        labelText.setForeground(Color.white);
        frame.add(labelText, BorderLayout.PAGE_START);
    }
}
