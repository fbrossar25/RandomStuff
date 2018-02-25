package application.sketch.chaos;

import application.gui.canvas.SketchCanvas;
import application.math.geom.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DoublePendulum {
    private double  g                    = 9.81;
    private double  dt                   = 0.33;
    private double  lAnchorToIn          = 150.0;
    private double  lInToOut             = 125.0;
    private double  angleAnchorToIn;
    private double  angleInToOut;
    private double  anchorRadius         = 15.0;
    private double  ballRadius           = 20.0;
    private double  derivAnchorToInAngle = 0.0;
    private double  derivInToOutAngle    = 0.0;
    private Point2D anchor;
    private double  ballInMass           = 1.5;
    private double  ballOutMass          = 1.5;
    private Point2D ballIn;
    private Point2D ballOut;

    public DoublePendulum(Point2D anchor, double angleAnchorToIn, double angleInToOut) {
        this.anchor = anchor;
        this.angleAnchorToIn = angleAnchorToIn;
        this.angleInToOut = angleInToOut;
        ballIn = new Point2D(anchor.x + (Math.sin(angleAnchorToIn) * lAnchorToIn), anchor.y + (Math.cos(angleAnchorToIn) * lAnchorToIn));
        ballOut = new Point2D(anchor.x + (Math.sin(angleAnchorToIn) * lAnchorToIn), anchor.y + (Math.cos(angleAnchorToIn) * lAnchorToIn));
    }

    void update() {
        double mu = 1.0 + (ballInMass / ballOutMass);
        double cosAtoIMinItoO = Math.cos(angleAnchorToIn - angleInToOut);
        double sinAtoInMinItoO = Math.sin(angleAnchorToIn - angleInToOut);
        double sinInToOut = Math.sin(angleInToOut);
        double sinAnchorToIn = Math.sin(angleAnchorToIn);
        double squaredDerivAnchorToIn = derivAnchorToInAngle * derivAnchorToInAngle;
        double squaredDerivInToOut = derivInToOutAngle * derivInToOutAngle;
        double deriv2AnchorToInAngle = (g * (sinInToOut * cosAtoIMinItoO - mu * sinAnchorToIn)
                - (lInToOut * squaredDerivInToOut + lAnchorToIn * squaredDerivAnchorToIn * cosAtoIMinItoO) * sinAtoInMinItoO)
                / (lAnchorToIn * (mu - cosAtoIMinItoO * cosAtoIMinItoO));
        double deriv2InToOutAngle = (mu * g * (sinAnchorToIn * cosAtoIMinItoO - sinInToOut)
                + (mu * lInToOut * squaredDerivAnchorToIn + lInToOut * squaredDerivInToOut * cosAtoIMinItoO) * sinAtoInMinItoO)
                / (lInToOut * (mu - cosAtoIMinItoO * cosAtoIMinItoO));
        derivAnchorToInAngle += deriv2AnchorToInAngle * dt;
        derivInToOutAngle += deriv2InToOutAngle * dt;
        angleAnchorToIn += derivAnchorToInAngle * dt;
        angleInToOut += derivInToOutAngle * dt;
        ballIn.x = anchor.x + lAnchorToIn * Math.sin(angleAnchorToIn);
        ballIn.y = anchor.y + lAnchorToIn * Math.cos(angleAnchorToIn);
        ballOut.x = anchor.x + lAnchorToIn * Math.sin(angleAnchorToIn) + lInToOut * Math.sin(angleInToOut);
        ballOut.y = anchor.y + lAnchorToIn * Math.cos(angleAnchorToIn) + lInToOut * Math.cos(angleInToOut);
    }

    void draw(SketchCanvas canvas, Color c) {
        Paint prevStroke = canvas.setStroke(c);
        Paint prevFill = canvas.setFill(c);
        canvas.line(anchor.x, anchor.y, ballIn.x, ballIn.y);
        canvas.line(ballIn.x, ballIn.y, ballOut.x, ballOut.y);
        canvas.fillCenteredRect(anchor.x, anchor.y, anchorRadius, anchorRadius);
        canvas.fillCircle(ballIn.x, ballIn.y, ballRadius * (ballInMass / 2.0));
        canvas.fillCircle(ballOut.x, ballOut.y, ballRadius * (ballOutMass / 2.0));
        canvas.setFill(prevFill);
        canvas.setStroke(prevStroke);
    }
}
