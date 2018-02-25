package application.sketch.chaos;

import javax.management.RuntimeErrorException;

import application.Main;
import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.geom.Point2D;
import application.sketch.Sketch;
import javafx.scene.paint.Color;

public class DoublePendulumSketch extends Sketch {

    private double         initialAngleAnchorToIn = Math.toRadians(110);
    private double         initialAngleInToOut    = Math.toRadians(-20);
    private DoublePendulum p;

    public DoublePendulumSketch() {
    }

    @Override
    public void setup() {

        SketchCanvas canvas = Main.CONTROLLER_INSTANCE.getCanvas();
        if (canvas == null) {
            throw new RuntimeErrorException(new Error("Missing SketchCanvas instance"));
        }
        p = new DoublePendulum(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4), initialAngleAnchorToIn, initialAngleInToOut);
    }

    @Override
    public void update() {
        p.update();
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        p.draw(canvas, Color.BLACK);
    }

    @Override
    public SketchControls getControls() {
        // TODO Auto-generated method stub
        return null;
    }
}
