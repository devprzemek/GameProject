package com.game;

import java.awt.*;

/**
 * Klasa tworząca menu gry
 */
public class Menu extends Canvas{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public Rectangle playButton = new Rectangle(WIDTH /4, 150, 300, 50);
    public Rectangle tableButton = new Rectangle(WIDTH / 4 , 250, 300, 50);
    public Rectangle contactButton = new Rectangle(WIDTH / 4 , 350, 300, 50);
    public Rectangle quitButton = new Rectangle(WIDTH / 4 , 450, 300, 50);

    /**
     * Metoda rysująca menu
     */
    public void render(Graphics g){

        Graphics2D g2D = (Graphics2D) g;

        Font font0 = new Font("helvetica", Font.BOLD,50);
        g.setFont(font0);
        g.setColor(Color.BLACK);
        g.drawString( "MENU GRY ", WIDTH / 6 + 65, 100);

        Font font1 = new Font("arial", Font.BOLD, 30);
        g.setFont(font1);
        g.setColor(Color.RED);

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
