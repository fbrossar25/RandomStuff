package application.sketch.noise;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.noise.NoiseGenerator;
import application.sketch.Sketch;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class NoiseCloud extends Sketch {
    private double                zOff        = 0.0;
    public static final Dimension DEFAULT_DIM = new Dimension(650, 420);
    private Dimension             dim;
    private NoiseGenerator        noiseGen;
    private double                xPadding    = 0.02;
    private double                yPadding    = 0.02;
    private double                zPadding    = 0.01;
    private Random                seedGen     = new Random();
    private ArrayList<Thread>     threadList  = new ArrayList<>();

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
    public void setup() {
        noiseGen.reseed(seedGen.nextLong());
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(SketchCanvas canvas) {
        if (!threadList.isEmpty())
            threadList.clear();
        canvas.clear();
        int W = dim.width;
        int H = dim.height;
        if (W <= 0 || H <= 0)
            return;
        WritableImage img = new WritableImage(W, H);
        PixelWriter pw = img.getPixelWriter();
        IntStream.range(0, W).parallel().forEach((i) -> {
            double xOff = i * xPadding, yOff = 0.0;
            for (int y = 0; y < H; y++) {
                pw.setColor(i, y, Color.gray(noiseGen.grayForNoise(xOff, yOff, zOff)));
                yOff += yPadding;
            }
        });
        zOff += zPadding;
        canvas.image(img);
    }

    @Override
    public SketchControls getControls() {
        // TODO Auto-generated method stub
        return null;
    }
}
