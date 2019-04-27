package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Klasa tworząca okno z najlepszymi wynikami
 */

public class GameResult extends JFrame {

    public JFrame frame1;

    public GameResult() {

        frame1 = new JFrame("WYNIKI");
        frame1.setPreferredSize(new Dimension(Menu.WIDTH / 2, Menu.HEIGHT / 2 ));
        frame1.setDefaultCloseOperation(frame1.getDefaultCloseOperation());
        frame1.pack();
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

        showResults();
    }

    public static void saveResult(){
        Game.bestResults.add(Game.player.name + " " + Game.player.points);
    }

    public void showResults(){
        JLabel text1 = new JLabel(Arrays.deepToString(Game.bestResults.toArray()));
        frame1.add(text1, BorderLayout.PAGE_START);
        frame1.setVisible(true);
    }



}
