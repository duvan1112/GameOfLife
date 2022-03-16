package presenters;

import models.Life;
import views.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {
    private Window window;
    private Life life;


    public Presenter() {
        int height = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Altura del area"));
        int width = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Ancho del area"));
        int men = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de hombres"));
        int women = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de Mujeres"));
        window = new Window(this,width,height);
        life = new Life(men, women, width, height);

        startAnimation();
        life.start();
    }

    private void startAnimation() {
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.updateGame(life);
            }
        });
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.valueOf(e.getActionCommand())) {

        }
    }
}
