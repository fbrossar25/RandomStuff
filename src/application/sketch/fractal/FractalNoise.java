package application.sketch.fractal;

import java.awt.Dimension;
import java.util.Random;

import application.gui.canvas.SketchCanvas;
import application.math.MathUtils;
import application.math.noise.ImprovedPerlin;
import application.math.noise.NoiseGenerator;
import application.sketch.Sketch;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FractalNoise extends Sketch {
    public static final Dimension DEFAULT_DIM     = new Dimension(650, 420);
    private Dimension             dim;
    private double                xPadding        = 0.02;
    private double                yPadding        = 0.02;
    private double                zPadding        = 0.05;
    private double                xOff            = 0.0;
    private double                yOff            = 0.0;
    private double                zOff            = 0.0;
    protected NoiseGenerator      noise;
    private Random                rand;
    private int                   octaves;
    private static final int      DEFAULT_OCTAVES = 3;                      // 1 means for classic noise
    private double[]              octavesWeights;
    private double                octavesWeightsSum;

    public FractalNoise() {
        super("Fractal Noise");
        noise = new ImprovedPerlin();
        rand = new Random();
        dim = new Dimension(DEFAULT_DIM);
    }

    @Override
    public void setup(SketchCanvas canvas) {
        noise.reseed(rand.nextLong());
        octaves = DEFAULT_OCTAVES;
        init();
    }

    @Override
    public void update(SketchCanvas canvas) {
    }

    @Override
    public void draw(SketchCanvas canvas) {
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
                pw.setColor(drawX, drawY, Color.gray(octaveNoise()));
                yOff += yPadding;
                drawY++;
            }
            xOff += xPadding;
            drawX++;
        }
        zOff += zPadding;
        canvas.image(img);
    }

    private void init() {
        initOctavesWeights();
    }

    private void initOctavesWeights() {
        octavesWeights = new double[octaves];
        octavesWeightsSum = 0.0;
        int octave = 1;
        for (int i = 0; i < octaves; i++) {
            octavesWeights[i] = 1.0 / octave;
            octavesWeightsSum += octavesWeights[i];
            octave <<= 1;
        }
    }

    private double octaveNoise() {
        double value = 0.0;
        int octave = 1;
        for (int i = 0; i < octaves; i++) {
            value += noise.grayForNoise(octave * xOff, octave * yOff, zOff) * octavesWeights[i];
            octave <<= 1; // octave *= 2;
        }
        return MathUtils.map(value, 0.0, octavesWeightsSum, 0.0, 1.0);
    }

}
