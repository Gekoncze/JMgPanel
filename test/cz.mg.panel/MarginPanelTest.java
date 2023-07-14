package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class MarginPanelTest {
    public static void main(String[] args) {
        new MarginPanelTest().test();
    }

    private void test() {
        new Window(MarginPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addHorizontal(button("More margin", () -> moreMargin(panel)), 0, 0);
        panel.addHorizontal(button("Less margin", () -> lessMargin(panel)), 0, 0);
        return panel;
    }

    private void moreMargin(@Mandatory Panel panel) {
        panel.setMargin(Math.min(32, panel.getMargin() + 2));
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private void lessMargin(@Mandatory Panel panel) {
        panel.setMargin(Math.max(0, panel.getMargin() - 2));
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }
}
