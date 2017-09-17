package application.sketch.random;

import java.awt.Dimension;

import application.gui.canvas.SketchCanvas;
import application.math.random.RandomUtils;
import application.sketch.Sketch;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class RandomGrayShades extends Sketch {
    public static final Dimension DEFAULT_DIM = new Dimension(650, 420);
    private Dimension             dim;

    public RandomGrayShades() {
        super("Random Gray Shades");
        dim = new Dimension(DEFAULT_DIM);
    }

    @Override
    public void setup(SketchCanvas canvas) {
    }

    @Override
    public void update(SketchCanvas canvas) {
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        int W = dim.width;
        int H = dim.height;
        int grayShade;
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        int drawX = (W / 2) - (dim.width / 2);
        int drawY;
        for (int x = 0; x < W; x++) {
            drawY = (H / 2) - (dim.height / 2);
            for (int y = 0; y < H; y++) {
                grayShade = RandomUtils.randomIntInclusive(255);
                pw.setColor(drawX, drawY, Color.grayRgb(grayShade));
                drawY++;
            }
            drawX++;
        }
        canvas.image(img);
    }

}
