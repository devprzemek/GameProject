package com.game;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JFrame mainFrame;
    private JPanel panel_01 = new JPanel();
    private JPanel panel_02 = new JPanel();

    private String text = "<html>Liczba żyć: <br/><br/><br/>Czas gry: <br/><br/><br/>Liczba punktów: </html>";
    JLabel panel_02text = new JLabel(text);

    public MainWindow() {

        panel_02text.setForeground(Color.RED);

        panel_01.setBackground(new Color(88, 216,  131));
        panel_02.setBackground(Color.DARK_GRAY);
        panel_02.add(panel_02text);


        mainFrame = new JFrame(Game.TITLE);
        mainFrame.setDefaultCloseOperation(mainFrame.getDefaultCloseOperation());
        mainFrame.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        mainFrame.getContentPane().setBackground(Color.BLACK);

        mainFrame.add(panel_01, BorderLayout.CENTER);
        mainFrame.add(panel_02, BorderLayout.EAST);


        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    public static void render(Graphics g){
            Game.getPumpkinObject().render(g);
    }

}
