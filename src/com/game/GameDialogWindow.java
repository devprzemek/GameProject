package com.game;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa informująca o etapach gry
 */

public class GameDialogWindow extends JDialog {

    public GameDialogWindow(JFrame owner){
        super(owner, "Etap gry", true);

        setSize(300,200);
        setLocation(625,250);

        String text = "<html> KONIEC GRY <br/> Liczba punktów: <html>" + Game.player.points;
        JLabel labelText = new JLabel(text, JLabel.CENTER);
        labelText.setFont(new Font("Helvetica", Font.PLAIN, 24));
        add(labelText);

        JButton bOk = new JButton("OK");
        bOk.setBackground(Color.RED);
        bOk.addActionListener(event -> {
            setVisible(false);
            Game.mainWindow.mainFrame.setVisible(false);
            Game.mainWindow.dispose();
            Game.level.pauseTime = 0;
            Game.timeOfGame = 0;
        });

        JPanel panel = new JPanel();
        panel.add(bOk);
        add(panel, BorderLayout.AFTER_LAST_LINE);

        setVisible(true);

    }
}
