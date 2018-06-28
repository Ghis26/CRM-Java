package fr.IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JButton button = new JButton("Validation");
    private JButton button2 = new JButton("Re-valid");
    private JLabel label = new JLabel("Le JLabel est ici");
    private int compteur = 0;


    public Window() {
        this.setTitle("Hello World !!");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        button.addActionListener(this);

        JPanel south = new JPanel();
        south.add(button);
        south.add(button2);
        panel.add(south, BorderLayout.SOUTH);


        this.setVisible(true);
        this.getContentPane().add(label, BorderLayout.NORTH);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    public void actionPerformed(ActionEvent arg0){
        this.compteur++;
        label.setText("vous avez cliqué " +this.compteur+ " fois");
    }
}
