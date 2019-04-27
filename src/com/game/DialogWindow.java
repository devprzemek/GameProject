package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa dodająca nowego gracza
 */

public class DialogWindow extends JFrame implements ActionListener {

    private JFrame frame;
    private JButton addPlayerButton;
    private DataPanel dataPanel;


    public DialogWindow(){

        frame = new JFrame();
        frame.setTitle("");
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setLocation(635,250);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addPlayerButton = new JButton("Dodaj gracza");
        addPlayerButton.setSize(150, 40);
        addPlayerButton.setLocation(70, 100);
        addPlayerButton.setBackground(Color.RED);
        addPlayerButton.addActionListener(this);
        frame.add(addPlayerButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(dataPanel == null)
            dataPanel = new DataPanel(this);

        dataPanel.setVisible(true);
        dataPanel.setFocus();

        Game.player.setName(dataPanel.getPlayerName()); //ustawienie nazwy gracza
        Game.player.setNumberOfLives(dataPanel.getGameCode()); //ustawienie liczby żyć

        frame.setVisible(false);
        frame.dispose();

        Game.mainWindow = new MainWindow();
        Game.state = Game.GAME_STATE.GAME;
    }
}
