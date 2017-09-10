package application.sketch;

import application.gui.canvas.SketchCanvas;
import application.math.random.RandomUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class RandomGrayShades extends Sketch {

    public RandomGrayShades() {
        super("Random Gray Shades");
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
        int W = (int) canvas.getWidth();
        int H = (int) canvas.getHeight();
        int grayShade;
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                grayShade = RandomUtils.randomIntInclusive(255);
                pw.setColor(x, y, Color.grayRgb(grayShade));
            }
        }
        canvas.image(img);
    }

}
