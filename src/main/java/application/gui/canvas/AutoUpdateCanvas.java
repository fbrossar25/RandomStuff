package application.gui.canvas;

import javafx.scene.canvas.Canvas;

public abstract class AutoUpdateCanvas extends Canvas implements Drawable {

    protected DrawableUpdater updater = new DrawableUpdater(this);

    public AutoUpdateCanvas() {
    }

    public AutoUpdateCanvas(double width, double height) {
        super(width, height);
    }

    @Override
    public abstract void draw();

}
