package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class AutoRebuildPanelTest {
    public static void main(String[] args) {
        new AutoRebuildPanelTest().test();
    }

    private void test() {
        new Window(AutoRebuildPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addVertical(toggleButton("AutoRebuild", () -> panel.setAutoRebuild(!panel.isAutoRebuild())), 0, 0);
        panel.addVertical(button("Rebuild", () -> rebuild(panel)), 0, 0);
        panel.addVertical(button("Add", () -> add(panel)), 0, 0);
        return panel;
    }

    private void rebuild(@Mandatory Panel panel) {
        panel.rebuild();
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private void add(@Mandatory Panel panel) {
        panel.addVertical(new JLabel("Label"), 0, 0);
        ((JFrame)SwingUtilities.getRoot(panel)).pack();
    }

    private @Mandatory JToggleButton toggleButton(@Mandatory String label, @Mandatory Runnable runnable) {
        JToggleButton button = new JToggleButton(label);
        button.setSelected(true);
        button.addActionListener(event -> runnable.run());
        return button;
    }

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }
}
