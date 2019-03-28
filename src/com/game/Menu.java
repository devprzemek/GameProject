package com.game;

import java.awt.*;

public class Menu extends Canvas{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 700;


    public Rectangle playButton = new Rectangle(WIDTH /3, 200, 300, 50);
    public Rectangle tableButton = new Rectangle(WIDTH / 3 , 300, 300, 50);
    public Rectangle contactButton = new Rectangle(WIDTH / 3 , 400, 300, 50);
    public Rectangle quitButton = new Rectangle(WIDTH / 3 , 500, 300, 50);

    public void render(Graphics g){

        Graphics2D g2D = (Graphics2D) g;
        Font font0 = new Font("helvetica", Font.BOLD,50);
        g.setFont(font0);
        g.setColor(Color.RED);
        g.drawString( "Gra ", WIDTH / 6 + 50, 150);

        Font font1 = new Font("arial", Font.BOLD, 30);
        g.setFont(font1);
        g.setColor(Color.black);

        g.drawString("ROZPOCZNIJ", playButton.x + 55, playButton.y + 35);
        g2D.draw(playButton);
        g.drawString("LISTA WYNIKÓW", tableButton.x + 25, tableButton.y + 35);
        g2D.draw(tableButton);
        g.drawString("KONTAKT", contactButton.x + 75, contactButton.y + 35);
        g2D.draw(contactButton);
        g.drawString("ZAKOŃCZ", quitButton.x + 75, quitButton.y + 35);
        g2D.draw(quitButton);
    }
}
