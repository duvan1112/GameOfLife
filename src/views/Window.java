package views;

import models.Ilife;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private MainPanel mainPanel;

    public Window(ActionListener actionListener,int height,int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.mainPanel = new MainPanel(actionListener,width,height);
        setLocationRelativeTo(null);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void updateGame(Ilife life) {
        mainPanel.updateGame(life);
    }
}
