package application.sketch;

import java.awt.Dimension;

import application.gui.canvas.SketchCanvas;
import application.math.noise.NoiseGenerator;
import application.math.noise.Perlin;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class NoiseCloud extends Sketch {
    private double            xOff        = 0.0;
    private double            yOff        = 0.0;
    private double            zOff        = 0.0;
    protected final Dimension DEFAULT_DIM = new Dimension(650, 420);
    private Dimension         dim;
    private NoiseGenerator    noiseGen;

    public NoiseCloud() {
        this(new Perlin());
    }

    protected NoiseCloud(NoiseGenerator generator) {
        super("Noise Gray Shades");
        dim = new Dimension(DEFAULT_DIM);
        noiseGen = generator;
    }

    public void setDrawDimension(int w, int h) {
        if (dim == null)
            dim = new Dimension();
        dim.setSize(w, h);
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
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        xOff = 0.0;
        int drawX = (W / 2) - (dim.width / 2);
        int drawY;
        for (int x = 0; x < dim.width; x++) {
            yOff = 0.0;
            drawY = (H / 2) - (dim.height / 2);
            for (int y = 0; y < dim.height; y++) {
                pw.setColor(drawX, drawY, Color.gray(noiseGen.grayForNoise(xOff, yOff, zOff)));
                yOff += 0.01;
                drawY++;
            }
            x += 0.01;
            drawX++;
        }
        zOff += 0.01;
        canvas.image(img);
    }
}
