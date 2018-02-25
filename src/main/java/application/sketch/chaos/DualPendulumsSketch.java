package application.sketch.chaos;

import javax.management.RuntimeErrorException;

import application.Main;
import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.geom.Point2D;
import application.sketch.Sketch;
import javafx.scene.paint.Color;

public class DualPendulumsSketch extends Sketch {
    private Pendulum p1;
    private Pendulum p2;
    Color            p1Color      = Color.rgb(200, 70, 60);
    Color            p2Color      = Color.rgb(110, 230, 60);
    private double   p1InitialAngle = Math.toRadians(110);
    private double   p2InitialAngle = Math.toRadians(111);

    public DualPendulumsSketch() {
    }

    @Override
    public void setup() {
        SketchCanvas canvas = Main.CONTROLLER_INSTANCE.getCanvas();
        if (canvas == null) {
            throw new RuntimeErrorException(new Error("Missing SketchCanvas instance"));
        }
        p1 = new Pendulum(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4), p1InitialAngle);
        p2 = new Pendulum(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4), p2InitialAngle);
    }

    @Override
    public void update() {
        p1.update();
        p2.update();
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        p1.draw(canvas, p1Color);
        p2.draw(canvas, p2Color);
    }

    @Override
    public SketchControls getControls() {
        // TODO Auto-generated method stub
        return null;
    }
}
