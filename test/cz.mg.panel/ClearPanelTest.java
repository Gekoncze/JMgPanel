package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;

public @Test class ClearPanelTest {
    public static void main(String[] args) {
        new ClearPanelTest().test();
    }

    private void test() {
        new Window(ClearPanelTest.class.getSimpleName(), create()).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.addHorizontal(button("Clear", () -> panel.clear()), 0, 0);
        return panel;
    }

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }
}
