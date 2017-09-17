package application.math.geom;

import application.gui.canvas.Drawable;
import application.gui.canvas.Drawer;

public class Segment implements Drawer {
    public double xi = 0.0, xf = 0.0, yi = 0.0, yf = 0.0;

    public Segment() {
    }

    public Segment(double xi, double xf, double yi, double yf) {
        this.xi = xi;
        this.xf = xf;
        this.yi = yi;
        this.yf = yf;
    }

    // xi <-> xf && yi <-> yf
    public void revert() {
        double t = xi;
        xi = xf;
        xf = t;
        t = yi;
        yi = yf;
        yf = t;
    }

    @Override
    public void draw(Drawable target) {
        target.line(xi, yi, xf, yf);
    }
}