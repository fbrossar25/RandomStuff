package application.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public interface Drawable {
    void draw();

    void setBackgroundColor(Color backgroundColor);

    Color getBackgroundColor();

    Paint getFill();

    Paint getStroke();

    Paint setStroke(int gray);

    Paint setStroke(Paint stroke);

    Paint setFill(Paint fill);

    Paint setStroke(int r, int g, int b);

    Paint setFill(int gray);

    Paint setFill(int r, int g, int b);

    void fillRect(double x, double y, double w, double h);

    void strokeRect(double x, double y, double w, double h);

    void strokeCenteredRect(double x, double y, double w, double h);

    void fillCenteredRect(double x, double y, double w, double h);

    void fillText(Object text, double x, double y);

    void strokeText(Object text, double x, double y);

    void fillPoint(double x, double y);

    void strokePoint(double x, double y);

    void strokeCircle(double x, double y, double r);

    void fillCircle(double x, double y, double r);

    void image(Image img);

    void image(Image img, int x, int y);

    void image(Image img, int x, int y, int w, int h);

    void line(double x1, double y1, double x2, double y2);

    GraphicsContext getContext();
}
