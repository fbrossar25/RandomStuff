package application.sketch.fractal;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.sketch.Sketch;

public class RandomFractal extends Sketch {

    public RandomFractal() {
        super("Random Fractal");
    }

    public RandomFractal(int regularity) {
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();

    }
    
    @Override
	public SketchControls getControls() {
		// TODO Auto-generated method stub
		return null;
	}
}
