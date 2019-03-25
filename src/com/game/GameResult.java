package com.game;

import javax.swing.*;
import java.awt.*;

public class GameResult extends Canvas {

    public GameResult() {
        Game game1 = new Game();

        game1.setPreferredSize(new Dimension(Menu.WIDTH / 2, Menu.HEIGHT / 2 ));

        JFrame frame1 = new JFrame("WYNIKI");
        frame1.add(game1);
        frame1.pack();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}
