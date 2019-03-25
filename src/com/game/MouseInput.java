package com.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
    public Rectangle playButton = new Rectangle(Game.WIDTH /3, 200, 300, 50);
    public Rectangle tableButton = new Rectangle(Game.WIDTH / 3 , 300, 300, 50);
    public Rectangle contactButton = new Rectangle(Game.WIDTH / 3 , 400, 300, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 3 , 500, 300, 50);
     **/

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //playButton
        if(mx >= Menu.WIDTH /3 && mx <= Menu.WIDTH /3 + 300){
            if(my >= 200 && my <= 250) {
                Game.state = Game.GAME_STATE.GAME;
                Game game = new Game();
            }
        }

        //quitButton
        if(mx >= Menu.WIDTH /3 && mx <= Menu.WIDTH /3 + 300){
            if(my >= 500 && my <= 550) {
                System.exit(0);
            }
        }

        //tableButton
        if(mx >= Menu.WIDTH /3 && mx <= Menu.WIDTH /3 + 300){
            if(my >= 300 && my <= 350) {
                GameResult results = new GameResult();
            }
        }
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
