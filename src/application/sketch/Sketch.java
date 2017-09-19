package application.sketch;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;

public abstract class Sketch {

    protected String name;

    public Sketch() {
        this("");
    }

    public Sketch(String name) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
    }

    public abstract void setup();

    public abstract void update();

    public abstract void draw(SketchCanvas canvas);

    public void reset() {
        setup();
    }
    
    public abstract SketchControls getControls();
}
