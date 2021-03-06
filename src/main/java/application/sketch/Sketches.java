package application.sketch;

import application.gui.dialogs.ExceptionDialog;
import application.sketch.chaos.DoublePendulumSketch;
import application.sketch.chaos.DualDoublePendulumsSketch;
import application.sketch.chaos.DualPendulumsSketch;
import application.sketch.chaos.PendulumSketch;
import application.sketch.fractal.FractalNoise;
import application.sketch.fractal.KochSnowflake;
import application.sketch.noise.ImprovedPerlinNoiseCloud;
import application.sketch.noise.OpenSimplexNoiseCloud;
import application.sketch.random.RandomCount;
import application.sketch.random.RandomGrayShades;

public enum Sketches {
    RANDOM_COUNT(RandomCount.class, "Random count"), RANDOM_GRAY_SHADES(RandomGrayShades.class, "Random Gray Shades"), SIMPLEX_CLOUD(
            OpenSimplexNoiseCloud.class, "OpenSimplex Noise Gray Shades"), IMPROVED_PERLIN_CLOUD(ImprovedPerlinNoiseCloud.class,
                    "Improved Perlin Noise Gray Shades"), FRACTAL_NOISE(FractalNoise.class, "Fractal Noise"), KOCH_SNOWFLAKE(
                            KochSnowflake.class,
                            "Koch Snowflake"), PENDULUM(PendulumSketch.class,
                                    "Pendulum"), DOUBLE_PENDULUM(DoublePendulumSketch.class,
                                            "Double Pendulum"), DUAL_PENDULUMS(DualPendulumsSketch.class,
                                                    "Dual Pendulums"), DUAL_DOUBLE_PENDULUMS(DualDoublePendulumsSketch.class,
                                                            "Dual Double Pendulums");

    private final Class<? extends Sketch> sketchClass;
    private String                        display;

    private Sketches(Class<? extends Sketch> classRef, String display) {
        sketchClass = classRef;
        this.display = display;
    }

    public final Class<? extends Sketch> getSketchClass() {
        return sketchClass;
    }

    public Sketch instanciateNew() {
        try {
            return sketchClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            new ExceptionDialog(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return display;
    }
}