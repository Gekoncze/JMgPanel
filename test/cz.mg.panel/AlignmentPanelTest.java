package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.panel.settings.Alignment;
import cz.mg.panel.settings.Fill;
import cz.mg.panel.settings.Settings;

import javax.swing.*;

public @Test class AlignmentPanelTest {
    public static void main(String[] args) {
        new AlignmentPanelTest().test();
    }

    private void test() {
        new Window(AlignmentPanelTest.class.getSimpleName(), create(), 640, 480).setVisible(true);
    }

    private @Mandatory Panel create() {
        Panel panel = new Panel();
        panel.add(button("Top left", () -> setAlignment(panel, Alignment.TOP_LEFT)), settings(0, 0));
        panel.add(button("Top", () -> setAlignment(panel, Alignment.TOP)), settings(1, 0));
        panel.add(button("Top right", () -> setAlignment(panel, Alignment.TOP_RIGHT)), settings(2, 0));
        panel.add(button("Left", () -> setAlignment(panel, Alignment.LEFT)), settings(0, 1));
        panel.add(button("Middle", () -> setAlignment(panel, Alignment.MIDDLE)), settings(1, 1));
        panel.add(button("Right", () -> setAlignment(panel, Alignment.RIGHT)), settings(2, 1));
        panel.add(button("Bottom left", () -> setAlignment(panel, Alignment.BOTTOM_LEFT)), settings(0, 2));
        panel.add(button("Bottom", () -> setAlignment(panel, Alignment.BOTTOM)), settings(1, 2));
        panel.add(button("Bottom right", () -> setAlignment(panel, Alignment.BOTTOM_RIGHT)), settings(2, 2));
        return panel;
    }

    private void setAlignment(@Mandatory Panel panel, @Mandatory Alignment alignment) {
        if (panel.getAlignment() != alignment) {
            panel.setAlignment(alignment);
        }
    }

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }

    private @Mandatory Settings settings(int x, int y) {
        return new Settings(Alignment.MIDDLE, Fill.BOTH, x, y, 0, 0, 1, 1);
    }
}
