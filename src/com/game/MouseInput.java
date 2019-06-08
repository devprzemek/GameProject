package com.game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Klasa obsługująca zdarzenia wyboru w menu
 */
public class MouseInput implements MouseListener {
    /**
     public Rectangle playButton = new Rectangle(WIDTH /4, 150, 300, 50);
     public Rectangle tableButton = new Rectangle(WIDTH / 4 , 250, 300, 50);
     public Rectangle contactButton = new Rectangle(WIDTH / 4 , 350, 300, 50);
     public Rectangle quitButton = new Rectangle(WIDTH / 4 , 450, 300, 50);
     **/

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //playButton
        if(mx >= Menu.WIDTH /4 && mx <= Menu.WIDTH /4 + 300){
            if(my >= 150 && my <= 200) {
                DialogWindow dialogWindow = new DialogWindow();
            }
        }

        //tableButton
        if(mx >= Menu.WIDTH /4 && mx <= Menu.WIDTH /4 + 300){
            if(my >= 250 && my <= 300) {
                Game.gameResults = new GameResult();
            }
        }

        //contactButton
        if(mx >= Menu.WIDTH /4 && mx <= Menu.WIDTH /4 + 300){
            if(my >= 350 && my <= 400) {
                JFrame frame = new JFrame();
                SendEmail sendEmail = new SendEmail(frame);
            }
        }

        //quitButton
        if(mx >= Menu.WIDTH /4 && mx <= Menu.WIDTH /4 + 300){
            if(my >= 450 && my <= 500) {
                System.exit(0);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
