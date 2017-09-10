package application.sketch;

import java.awt.Dimension;

import application.gui.canvas.SketchCanvas;
import application.math.noise.PerlinNoiseGenerator;
import application.math.noise.SimplexNoise;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class PerlinGrayShades extends Sketch {
    private double               xOff        = 0.0;
    private double               yOff        = 0.0;
    private double               zOff        = 0.0;
    private final Dimension      DEFAULT_DIM = new Dimension(650, 420);
    private Dimension            dim;
    private boolean              usePerlin;                               // true -> PerlinNoiseGenerator, false -> SimplexNoise
    private PerlinNoiseGenerator perlin      = new PerlinNoiseGenerator();

    public PerlinGrayShades() {
        this(true);
    }

    protected PerlinGrayShades(boolean usePerlin) { // too lazy too name it correctly :D
        super("Noise Gray Shades");
        this.usePerlin = usePerlin;
        dim = new Dimension(DEFAULT_DIM);
    }

    @Override
    public void setup(SketchCanvas canvas) {
        // canvas.noLoop();
        // draw(canvas);
    }

    @Override
    public void update(SketchCanvas canvas) {
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        int W = (int) canvas.getWidth();
        int H = (int) canvas.getHeight();
        // dim.setSize(W, H);
        int grayShade;
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        xOff = 0.0;
        int drawX = (W / 2) - (dim.width / 2);
        int drawY;
        for (int x = 0; x < dim.width; x++) {
            yOff = 0.0;
            drawY = (H / 2) - (dim.height / 2);
            for (int y = 0; y < dim.height; y++) {
                if (usePerlin)
                    grayShade = perlin.grayShadeForNoise(xOff, yOff, zOff);
                else
                    grayShade = SimplexNoise.grayShadeForNoise(xOff, yOff, zOff);
                // System.out.println("(" + x + "," + y + ") -> " + grayShade);
                pw.setColor(drawX, drawY, Color.grayRgb(grayShade));
                yOff += 0.01;
                drawY++;
            }
            x += 0.01;
            drawX++;
        }
        zOff += 0.02;
        canvas.image(img);
    }
}
