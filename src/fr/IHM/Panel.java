package fr.IHM;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public void paintComponent(Graphics g){
        System.out.println("I'm executed !");
        g.drawString("Bienvenue dans l'application", 75,20);
    }
}
