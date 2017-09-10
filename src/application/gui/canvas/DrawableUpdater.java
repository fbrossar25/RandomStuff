package application.gui.canvas;

import javafx.animation.AnimationTimer;

public class DrawableUpdater extends AnimationTimer {
    private final Drawable drawable;

    public DrawableUpdater(Drawable drawable) {
        if (drawable == null)
            throw new IllegalArgumentException("drawable cannot be null");
        this.drawable = drawable;
        start();
    }

    @Override
    public void handle(long now) {
        drawable.draw();
    }
}
