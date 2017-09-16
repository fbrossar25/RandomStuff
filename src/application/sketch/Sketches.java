package application.sketch;

import application.gui.dialogs.ExceptionDialog;

public enum Sketches {
    RANDOM_COUNT(RandomCount.class, "Random count"), RANDOM_GRAY_SHADES(RandomGrayShades.class, "Random Gray Shades"), SIMPLEX_CLOUD(
            OpenSimplexNoiseCloud.class, "OpenSimplex Noise Gray Shades"), IMPROVED_PERLIN_CLOUD(ImprovedPerlinNoiseCloud.class,
                    "Improved Perlin Noise Gray Shades"), KOCH_SNOWFLAKE(KochSnowflake.class, "Koch Snowflake");

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