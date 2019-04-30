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
        setLocation(635,250);

        String text = "<html> KONIEC GRY <html>";
        JLabel labelText = new JLabel(text, JLabel.CENTER);
        labelText.setFont(new Font("Helvetica", Font.PLAIN, 24));
        add(labelText);

        JButton ok = new JButton("OK");
        ok.setBackground(Color.RED);
        ok.addActionListener(event -> {
            setVisible(false);
            Game.mainWindow.mainFrame.setVisible(false);
            Game.mainWindow.dispose();
        });

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.AFTER_LAST_LINE);

        setVisible(true);

    }
}
