package application.gui.canvas;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public interface Drawable {
    void draw();

    void setBackgroundColor(Color backgroundColor);

    Color getBackgroundColor();

    Paint getFill();

    Paint getStroke();

    void setStroke(int gray);

    void setStroke(int r, int g, int b);

    void setFill(int gray);

    void setFill(int r, int g, int b);

    void fillRect(double x, double y, double w, double h);

    void strokeRect(double x, double y, double w, double h);

    void fillText(Object text, double x, double y);

    void strokeText(Object text, double x, double y);

    void fillPoint(int x, int y);

    void strokePoint(int x, int y);

    void image(Image img);

    void image(Image img, int x, int y);

    void image(Image img, int x, int y, int w, int h);

    void line(double x1, double y1, double x2, double y2);
}
