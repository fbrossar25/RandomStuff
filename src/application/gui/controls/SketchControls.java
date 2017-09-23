package application.gui.controls;

import application.sketch.Sketch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public abstract class SketchControls extends Scene {
    protected Sketch     sketch;
    protected BorderPane rootPane;

    public SketchControls(Sketch sketch) {
        super(new BorderPane());
        rootPane = (BorderPane) getRoot();
        this.sketch = sketch;
        initControls();
    }

    protected abstract void resetControls();

    protected abstract void initControls();

    public Sketch getSketch() {
        return sketch;
    }

    public void setSketch(Sketch sketch) {
        this.sketch = sketch;
    }
}
