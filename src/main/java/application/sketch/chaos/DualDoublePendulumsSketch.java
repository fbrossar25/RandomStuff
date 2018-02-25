package application.sketch.chaos;

import javax.management.RuntimeErrorException;

import application.Main;
import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.geom.Point2D;
import application.sketch.Sketch;
import javafx.scene.paint.Color;

public class DualDoublePendulumsSketch extends Sketch {

    private double p1InitialAngleAnchorToIn = Math.toRadians(110);
    private double p1InitialAngleInToOut    = Math.toRadians(-20);
    private double p2InitialAngleAnchorToIn = Math.toRadians(110);
    private double p2InitialAngleInToOut    = Math.toRadians(-21);
    DoublePendulum p1;
    DoublePendulum p2;
    Color          p1Color                  = Color.rgb(200, 70, 60);
    Color          p2Color                  = Color.rgb(110, 230, 60);

    public DualDoublePendulumsSketch() {
    }

    @Override
    public void setup() {
        SketchCanvas canvas = Main.CONTROLLER_INSTANCE.getCanvas();
        if (canvas == null) {
            throw new RuntimeErrorException(new Error("Missing SketchCanvas instance"));
        }
        Point2D anchor1 = new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4);
        Point2D anchor2 = new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 4);
        p1 = new DoublePendulum(anchor1, p1InitialAngleAnchorToIn, p1InitialAngleInToOut);
        p2 = new DoublePendulum(anchor2, p2InitialAngleAnchorToIn, p2InitialAngleInToOut);
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
