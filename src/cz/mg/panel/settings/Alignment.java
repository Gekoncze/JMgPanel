package cz.mg.panel.settings;

import cz.mg.annotations.requirement.Mandatory;

import java.awt.*;

public enum Alignment {
    TOP_LEFT, TOP, TOP_RIGHT,
    LEFT, MIDDLE, RIGHT,
    BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;

    public @Mandatory HorizontalAlignment getHorizontal() {
        switch (this) {
            case TOP_LEFT:
                return HorizontalAlignment.LEFT;
            case LEFT:
                return HorizontalAlignment.LEFT;
            case BOTTOM_LEFT:
                return HorizontalAlignment.LEFT;

            case TOP:
                return HorizontalAlignment.MIDDLE;
            case MIDDLE:
                return HorizontalAlignment.MIDDLE;
            case BOTTOM:
                return HorizontalAlignment.MIDDLE;

            case TOP_RIGHT:
                return HorizontalAlignment.RIGHT;
            case RIGHT:
                return HorizontalAlignment.RIGHT;
            case BOTTOM_RIGHT:
                return HorizontalAlignment.RIGHT;
        }
        throw new IllegalStateException();
    }

    public @Mandatory VerticalAlignment getVertical() {
        switch (this) {
            case TOP_LEFT:
                return VerticalAlignment.TOP;
            case TOP:
                return VerticalAlignment.TOP;
            case TOP_RIGHT:
                return VerticalAlignment.TOP;

            case LEFT:
                return VerticalAlignment.MIDDLE;
            case MIDDLE:
                return VerticalAlignment.MIDDLE;
            case RIGHT:
                return VerticalAlignment.MIDDLE;

            case BOTTOM_LEFT:
                return VerticalAlignment.BOTTOM;
            case BOTTOM:
                return VerticalAlignment.BOTTOM;
            case BOTTOM_RIGHT:
                return VerticalAlignment.BOTTOM;
        }
        throw new IllegalStateException();
    }

    public int getInternalAnchor() {
        switch (this) {
            case TOP_LEFT:
                return GridBagConstraints.FIRST_LINE_START;
            case TOP:
                return GridBagConstraints.PAGE_START;
            case TOP_RIGHT:
                return GridBagConstraints.FIRST_LINE_END;

            case LEFT:
                return GridBagConstraints.LINE_START;
            case MIDDLE:
                return GridBagConstraints.CENTER;
            case RIGHT:
                return GridBagConstraints.LINE_END;

            case BOTTOM_LEFT:
                return GridBagConstraints.LAST_LINE_START;
            case BOTTOM:
                return GridBagConstraints.PAGE_END;
            case BOTTOM_RIGHT:
                return GridBagConstraints.LAST_LINE_END;
        }
        throw new IllegalStateException();
    }
}
