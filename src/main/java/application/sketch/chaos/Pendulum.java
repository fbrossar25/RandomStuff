package application.sketch.chaos;

import application.gui.canvas.SketchCanvas;
import application.math.geom.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Pendulum {
    private double  g             = 9.81;
    private double  dt            = 0.20;
    private Point2D anchor;
    private Point2D ball;
    private double  anchorRadius  = 15.0;
    private double  ballRadius    = 20.0;
    private double  l             = 100.0;
    private double  angle;
    private double  angleVelocity = 0.0;

    public Pendulum(Point2D anchor, double angle) {
        this.anchor = anchor;
        this.angle = angle;
        ball = new Point2D(anchor.x + (Math.sin(angle) * l), anchor.y + (Math.cos(angle) * l));
    }

    void update() {
        double sina = Math.sin(angle);
        angleVelocity += (-g / l * sina) * dt;
        angle += angleVelocity * dt;
        ball.x = anchor.x + (sina * l);
        ball.y = anchor.y + (Math.cos(angle) * l);
    }

    void draw(SketchCanvas canvas, Color c) {
        Paint prevStroke = canvas.setStroke(c);
        Paint prevFill = canvas.setFill(c);
        canvas.line(anchor.x, anchor.y, ball.x, ball.y);
        canvas.fillCenteredRect(anchor.x, anchor.y, anchorRadius, anchorRadius);
        canvas.fillCircle(ball.x, ball.y, ballRadius);
        canvas.setStroke(prevStroke);
        canvas.setFill(prevFill);
    }
}