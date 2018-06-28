package fr.IHM;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    private String name;

    public Button(String str) {
        super(str);
        this.name = str;
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }
}
