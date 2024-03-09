package cz.mg.panel;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.panel.settings.RebuildMode;

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

        JComboBox<RebuildMode> rebuildModeComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
        rebuildModeComboBox.addItem(RebuildMode.MANUAL);
        rebuildModeComboBox.addItem(RebuildMode.AUTODETECT);
        rebuildModeComboBox.addItem(RebuildMode.ALWAYS);
        rebuildModeComboBox.setSelectedItem(panel.getRebuildMode());
        rebuildModeComboBox.addActionListener(event -> panel.setRebuildMode(
            rebuildModeComboBox.getItemAt(
                rebuildModeComboBox.getSelectedIndex()
            )
        ));

        panel.addVertical(rebuildModeComboBox, 0, 0);
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

    private @Mandatory JButton button(@Mandatory String label, @Mandatory Runnable runnable) {
        JButton button = new JButton(label);
        button.addActionListener(event -> runnable.run());
        return button;
    }
}
