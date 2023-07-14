package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class PaddingPanelTest {
    public static void main(String[] args) {
        new PaddingPanelTest().test();
    }

    private void test() {
        new Window(PaddingPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addHorizontal(button("More padding", () -> morePadding(panel)), 0, 0);
        panel.addHorizontal(button("Less padding", () -> lessPadding(panel)), 0, 0);
        return panel;
    }

    private void morePadding(@Mandatory Panel panel) {
        panel.setPadding(Math.min(32, panel.getPadding() + 2));
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private void lessPadding(@Mandatory Panel panel) {
        panel.setPadding(Math.max(0, panel.getPadding() - 2));
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }
}
