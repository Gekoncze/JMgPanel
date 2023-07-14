package cz.mg.panel;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Component class Window extends JFrame {
    public Window(@Mandatory String title, @Mandatory Panel panel) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public Window(@Mandatory String title, @Mandatory Panel panel, int width, int height) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        getContentPane().add(panel);
        setSize(width, height);
        setLocationRelativeTo(null);
    }
}
