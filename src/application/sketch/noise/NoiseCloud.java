package application.sketch.noise;

import java.awt.Dimension;
import java.util.Random;

import application.gui.canvas.SketchCanvas;
import application.math.noise.NoiseGenerator;
import application.sketch.Sketch;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class NoiseCloud extends Sketch {
    private double                xOff        = 0.0;
    private double                yOff        = 0.0;
    private double                zOff        = 0.0;
    public static final Dimension DEFAULT_DIM = new Dimension(650, 420);
    private Dimension             dim;
    private NoiseGenerator        noiseGen;
    private double                xPadding    = 0.02;
    private double                yPadding    = 0.02;
    private double                zPadding    = 0.01;
    private Random                seedGen     = new Random();

    protected NoiseCloud(String name, NoiseGenerator generator) {
        super(name);
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
        noiseGen.reseed(seedGen.nextLong());
    }

    @Override
    public void update(SketchCanvas canvas) {
    }

    @Override
    public void draw(SketchCanvas canvas) {
        // int W = (int) canvas.getWidth();
        // int H = (int) canvas.getHeight();
        canvas.clear();
        int W = dim.width;
        int H = dim.height;
        if (W <= 0 || H <= 0)
            return;
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        xOff = 0.0;
        int drawX = (W / 2) - (dim.width / 2);
        int drawY;
        for (int x = 0; x < W; x++) {
            yOff = 0.0;
            drawY = (H / 2) - (dim.height / 2);
            for (int y = 0; y < H; y++) {
                pw.setColor(drawX, drawY, Color.gray(noiseGen.grayForNoise(xOff, yOff, zOff)));
                yOff += yPadding;
                drawY++;
            }
            xOff += xPadding;
            drawX++;
        }
        zOff += zPadding;
        canvas.image(img);
    }
}
