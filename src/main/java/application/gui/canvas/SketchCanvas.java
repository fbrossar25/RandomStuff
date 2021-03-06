package application.gui.canvas;

import application.sketch.Sketch;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SketchCanvas extends AutoUpdateCanvas {

    private Sketch          sketch;
    private Color           backgroundColor = Color.WHITE;
    private GraphicsContext ctx;
    private Paint           fill            = Color.BLACK;
    private Paint           stroke          = Color.BLACK;
    private boolean         loop            = true;

    public SketchCanvas() {
        this(null);
    }

    public SketchCanvas(Sketch sketch) {
        ctx = getGraphicsContext2D();
        // Redraw when size changes.
        widthProperty().addListener(evt -> handleSizeChange());
        heightProperty().addListener(evt -> handleSizeChange());
        clear();
        setSketch(sketch);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    @Override
    public void draw() {
        if (sketch == null)
            return;
        sketch.update();
        sketch.draw(this);
    }

    private void handleSizeChange() {
        if (sketch == null)
            return;
        sketch.draw(this);
    }

    public Sketch getSketch() {
        return sketch;
    }

    public void setSketch(Sketch sketch) {
        setLoop(true);
        this.sketch = sketch;
        if (sketch == null)
            return;
        sketch.setup();
    }

    public void resetSketch() {
        if (sketch == null)
            return;
        sketch.reset();
    }

    public void clear() {
        Paint previousFill = ctx.getFill();
        ctx.setFill(backgroundColor);
        ctx.fillRect(0.0, 0.0, getWidth(), getHeight());
        ctx.setFill(previousFill);
    }

    public boolean isLoop() {
        return loop;
    }

    public void noLoop() {
        setLoop(false);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        if (loop) {
            updater.start();
        } else {
            updater.stop();
        }
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = (backgroundColor == null) ? Color.TRANSPARENT : backgroundColor;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public Paint getFill() {
        return fill;
    }

    @Override
    public Paint getStroke() {
        return stroke;
    }

    @Override
    public Paint setFill(Paint fill) {
        ctx.setFill(fill);
        return fill;
    }

    @Override
    public Paint setStroke(Paint stroke) {
        ctx.setStroke(stroke);
        return stroke;
    }

    @Override
    public Paint setStroke(int gray) {
        return setStroke(gray, gray, gray);
    }

    @Override
    public Paint setStroke(int r, int g, int b) {
        stroke = Color.rgb(r % 256, g % 256, b % 256);
        ctx.setStroke(stroke);
        return stroke;
    }

    @Override
    public Paint setFill(int gray) {
        return setFill(gray, gray, gray);
    }

    @Override
    public Paint setFill(int r, int g, int b) {
        fill = Color.rgb(r % 256, g % 256, b % 256);
        ctx.setFill(fill);
        return fill;
    }

    @Override
    public void fillRect(double x, double y, double w, double h) {
        ctx.fillRect(x, y, w, h);
    }

    @Override
    public void strokeRect(double x, double y, double w, double h) {
        ctx.strokeRect(x, y, w, h);
    }

    @Override
    public void fillText(Object text, double x, double y) {
        ctx.fillText(text.toString(), x, y);
    }

    @Override
    public void strokeText(Object text, double x, double y) {
        ctx.strokeText(text.toString(), x, y);
    }

    @Override
    public void fillPoint(double x, double y) {
        ctx.fillRect(x, y, 1, 1);
    }

    @Override
    public void strokePoint(double x, double y) {
        ctx.strokeRect(x, y, 1, 1);
    }

    @Override
    public void image(Image img) {
        ctx.drawImage(img, (getWidth() / 2) - (img.getWidth() / 2), (getHeight() / 2) - (img.getHeight() / 2));
    }

    @Override
    public void image(Image img, int x, int y) {
        ctx.drawImage(img, x, y);
    }

    @Override
    public void image(Image img, int x, int y, int w, int h) {
        ctx.drawImage(img, x, y, w, h);
    }

    @Override
    public void line(double x1, double y1, double x2, double y2) {
        ctx.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public GraphicsContext getContext() {
        return ctx;
    }

    @Override
    public void strokeCircle(double x, double y, double r) {
        double r2 = r / 2.0;
        ctx.strokeOval(x - r2, y - r2, r, r);
    }

    @Override
    public void fillCircle(double x, double y, double r) {
        double r2 = r / 2.0;
        ctx.fillOval(x - r2, y - r2, r, r);
    }

    @Override
    public void strokeCenteredRect(double x, double y, double w, double h) {
        strokeRect(x - w / 2, y - h / 2, w, h);
    }

    @Override
    public void fillCenteredRect(double x, double y, double w, double h) {
        fillRect(x - w / 2, y - h / 2, w, h);
    }
}
