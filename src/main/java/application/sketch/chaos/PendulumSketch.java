package application.sketch.chaos;

import javax.management.RuntimeErrorException;

import application.Main;
import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.geom.Point2D;
import application.sketch.Sketch;
import javafx.scene.paint.Color;

public class PendulumSketch extends Sketch {
    private double initialAngle = Math.toRadians(110);
    Pendulum       p;
    public PendulumSketch() {
    }

    @Override
    public void setup() {
        SketchCanvas canvas = Main.CONTROLLER_INSTANCE.getCanvas();
        if (canvas == null) {
            throw new RuntimeErrorException(new Error("Missing SketchCanvas instance"));
        }
        p = new Pendulum(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4), initialAngle);
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
