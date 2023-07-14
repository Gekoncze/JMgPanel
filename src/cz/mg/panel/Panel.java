package cz.mg.panel;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.panel.settings.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static cz.mg.panel.settings.Alignment.MIDDLE;
import static cz.mg.panel.settings.Alignment.TOP_LEFT;
import static cz.mg.panel.settings.Fill.BOTH;


public @Component class Panel extends JPanel {
    private static final int DEFAULT_MARGIN = 4;
    private static final int DEFAULT_PADDING = 4;
    private static final Alignment DEFAULT_ALIGNMENT = TOP_LEFT;

    private final @Mandatory ArrayList<JComponent> components = new ArrayList<>();
    private final @Mandatory HashMap<JComponent, Settings> componentSettings = new HashMap<>();

    private @Mandatory Alignment alignment;
    private int margin;
    private int padding;
    private boolean autoRebuild = true;

    public Panel() {
        this(DEFAULT_MARGIN, DEFAULT_PADDING);
    }

    public Panel(int margin, int padding) {
        this(margin, padding, DEFAULT_ALIGNMENT);
    }

    public Panel(int margin, int padding, @Mandatory Alignment alignment) {
        this.margin = margin;
        this.padding = padding;
        this.alignment = alignment;
        setOpaque(false);
        setBackground(null);
        rebuild();
    }

    public boolean isAutoRebuild() {
        return autoRebuild;
    }

    public void setAutoRebuild(boolean autoRebuild) {
        this.autoRebuild = autoRebuild;
        autoRebuild();
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
        autoRebuild();
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
        autoRebuild();
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        autoRebuild();
    }

    public void add(@Mandatory JComponent component, @Mandatory Settings settings) {
        components.add(component);
        componentSettings.put(component, settings);
        autoRebuild();
    }

    public void addHorizontal(@Mandatory JComponent component, int wx, int wy) {
        addHorizontal(component, wx, wy, MIDDLE, BOTH);
    }

    public void addHorizontal(
        @Mandatory JComponent component,
        int wx, int wy,
        @Mandatory Alignment alignment,
        @Mandatory Fill fill
    ) {
        add(component, new Settings(alignment, fill, components.size(), 0, wx, wy, 1, 1));
    }

    public void addVertical(@Mandatory JComponent component, int wx, int wy) {
        addVertical(component, wx, wy, MIDDLE, BOTH);
    }

    public void addVertical(
        @Mandatory JComponent component,
        int wx, int wy,
        @Mandatory Alignment alignment,
        @Mandatory Fill fill
    ) {
        add(component, new Settings(alignment, fill, 0, components.size(), wx, wy, 1, 1));
    }

    private void autoRebuild() {
        if (autoRebuild) {
            rebuild();
        }
    }

    public void clear() {
        removeAll();
        components.clear();
        repaint();
    }

    public void rebuild() {
        removeAll();
        setLayout(new GridBagLayout());
        if (components.isEmpty()) return;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxWX = 0;
        int maxWY = 0;

        for (Settings settings : this.componentSettings.values()) {
            minX = Math.min(minX, settings.x);
            minY = Math.min(minY, settings.y);
            maxX = Math.max(maxX, settings.x);
            maxY = Math.max(maxY, settings.y);
            maxWX = Math.max(maxWX, settings.wx);
            maxWY = Math.max(maxWY, settings.wy);
        }

        int hc = maxX - minX + 1;
        int vc = maxY - minY + 1;

        HorizontalAlignment horizontalAlignment = alignment.getHorizontal();
        VerticalAlignment verticalAlignment = alignment.getVertical();

        int cx = minX >= 0 ? 0 : -minX;
        int cy = minY >= 0 ? 0 : -minY;

        int ax = horizontalAlignment == HorizontalAlignment.RIGHT ? 1 : 0;
        int ay = verticalAlignment == VerticalAlignment.BOTTOM ? 1 : 0;

        int dx = cx + ax;
        int dy = cy + ay;

        for (JComponent component : components) {
            Settings settings = componentSettings.get(component);

            int pLeft = settings.x == minX ? margin : 0;
            int pRight = settings.x == maxX ? margin : padding;
            int pTop = settings.y == minY ? margin : 0;
            int pBottom = settings.y == maxY ? margin : padding;

            add(
                component,
                constraints(
                    settings.alignment,
                    settings.fill,
                    settings.x + dx,
                    settings.y + dy,
                    settings.wx,
                    settings.wy,
                    pTop,
                    pLeft,
                    pBottom,
                    pRight,
                    settings.spanX,
                    settings.spanY
                )
            );
        }

        if (maxWX == 0) {
            if (horizontalAlignment == HorizontalAlignment.LEFT) {
                add(createDummy(), constraints(MIDDLE, BOTH, hc + 1 , dy, 1, 0, 0, 0, 0, 0, 1, 1));
            }

            if (horizontalAlignment == HorizontalAlignment.RIGHT) {
                add(createDummy(), constraints(MIDDLE, BOTH, 0, dy, 1, 0, 0, 0, 0, 0, 1, 1));
            }
        }

        if (maxWY == 0) {
            if (verticalAlignment == VerticalAlignment.TOP) {
                add(createDummy(), constraints(MIDDLE, BOTH, dx, vc + dy, 0, 1, 0, 0, 0, 0, 1, 1));
            }

            if (verticalAlignment == VerticalAlignment.BOTTOM) {
                add(createDummy(), constraints(MIDDLE, BOTH, dx, 0, 0, 1, 0, 0, 0, 0, 1, 1));
            }
        }

        repaint();
        revalidate();
    }

    private @Mandatory JLabel createDummy(){
        JLabel dummy = new JLabel();
        dummy.setMinimumSize(new Dimension(0, 0));
        dummy.setPreferredSize(new Dimension(0, 0));
        dummy.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        dummy.setOpaque(false);
        dummy.setBackground(null);
        return dummy;
    }

    private @Mandatory GridBagConstraints constraints(
        @Mandatory Alignment alignment,
        @Mandatory Fill fill,
        int x, int y, int wx, int wy,
        int pTop, int pLeft, int pBottom, int pRight,
        int spanX, int spanY
    ){
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Illegal position [" + x + "," + y + "].");
        }

        if (wx < 0 || wy < 0) {
            throw new IllegalArgumentException("Illegal wight [" + wx + "," + wy + "].");
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = fill.getInternalCode();
        constraints.insets = new Insets(pTop, pLeft, pBottom, pRight);
        constraints.anchor = alignment.getInternalAnchor();
        constraints.gridwidth = spanX;
        constraints.gridheight = spanY;
        return constraints;
    }

}
