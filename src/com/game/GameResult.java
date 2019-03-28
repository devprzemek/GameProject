package com.game;

import javax.swing.*;
import java.awt.*;

public class GameResult extends JFrame {

    public GameResult() {
        Game game1 = new Game();

        game1.setPreferredSize(new Dimension(Menu.WIDTH / 2, Menu.HEIGHT / 2 ));

        JFrame frame1 = new JFrame("WYNIKI");
        frame1.add(game1);
        frame1.setDefaultCloseOperation(frame1.getDefaultCloseOperation());
        frame1.pack();
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}
