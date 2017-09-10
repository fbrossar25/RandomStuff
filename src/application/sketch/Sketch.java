package application.sketch;

import application.gui.canvas.SketchCanvas;

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

    public abstract void setup(SketchCanvas canvas);

    public abstract void update(SketchCanvas canvas);

    public abstract void draw(SketchCanvas canvas);

    public void reset(SketchCanvas canvas) {
        setup(canvas);
    }
}
