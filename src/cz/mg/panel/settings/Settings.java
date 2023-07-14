package cz.mg.panel.settings;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;


public @Component class Settings {
    public final @Mandatory Alignment alignment;
    public final @Mandatory Fill fill;
    public final int x;
    public final int y;
    public final int wx;
    public final int wy;
    public final int spanX;
    public final int spanY;

    public Settings(
        @Mandatory Alignment alignment,
        @Mandatory Fill fill,
        int x,
        int y,
        int wx,
        int wy,
        int spanX,
        int spanY
    ) {
        this.x = x;
        this.y = y;
        this.wx = wx;
        this.wy = wy;
        this.alignment = alignment;
        this.fill = fill;
        this.spanX = spanX;
        this.spanY = spanY;
    }
}
