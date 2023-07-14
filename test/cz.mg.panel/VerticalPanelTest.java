package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class VerticalPanelTest {
    public static void main(String[] args) {
        new VerticalPanelTest().test();
    }

    private void test() {
        new Window(VerticalPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addVertical(new JLabel("Label 1"), 0, 0);
        panel.addVertical(new JLabel("Label 2"), 0, 0);
        panel.addVertical(new JLabel("Label 3"), 0, 0);
        return panel;
    }
}
