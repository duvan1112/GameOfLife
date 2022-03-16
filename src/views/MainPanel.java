package views;

import models.Food;
import models.Ilife;
import models.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {
    private BufferedImage background;

    public MainPanel(ActionListener actionListener, int width, int height) {
        setSize(width, height);
        setBackground(Color.WHITE);
    }

    public void updateGame(Ilife life) {
        if (background == null) {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics g = background.getGraphics();
        super.paint(g);
        g.setColor(Color.yellow);
        for (Food f : life.getFood()) {

            g.fillRect(f.x, f.y, 10, 10);
        }

        for (Person p : life.getPeople()) {
            if (p.getGender() == 1) {
                g.setColor(Color.MAGENTA);
                g.fillRect(p.x, p.y, 10, 10);
            } else if (p.getGender() == 0) {
                g.setColor(Color.gray);
                g.fillRect(p.x, p.y, 10, 10);
            } else {
                g.fillOval(0, 0, 100, 100);
            }
        }

        int countMen = 0;
        for (Person p : life.getPeople()) {
            if (p.getGender() == 0) {
                countMen++;
            }
        }
        g.setColor(Color.black);
        g.setFont(new Font("Monospaced", Font.BOLD, 13));
        g.drawString("Población total: " + life.getPeople().size(), 0, 20);
        g.drawString("Población hombres : " + countMen, 0, 30);
        g.drawString("Población mujeres : " + (life.getPeople().size() - countMen), 0, 40);
        g.drawString("Años:" + life.getYears(), 0, 60);
        g.drawString("Comida:" + life.getFood().size(), 0, 80);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }
}
