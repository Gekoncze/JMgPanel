package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class HorizontalPanelTest {
    public static void main(String[] args) {
        new HorizontalPanelTest().test();
    }

    private void test() {
        new Window(HorizontalPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addHorizontal(new JLabel("Label 1"));
        panel.addHorizontal(new JLabel("Label 2"));
        panel.addHorizontal(new JLabel("Label 3"));
        return panel;
    }
}
