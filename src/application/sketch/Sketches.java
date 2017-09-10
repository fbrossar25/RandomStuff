package application.sketch;

import application.gui.dialogs.ExceptionDialog;

public enum Sketches {
    RANDOM_COUNT(RandomCount.class, "Random count"), RANDOM_GRAY_SHADES(RandomGrayShades.class, "Random Gray Shades"), PERLIN_GRAY_SHADES(
            PerlinGrayShades.class, "Perlin Noise Gray Shades"), SIMPLEX_GRAY_SHADES(SimplexGrayShades.class, "Simplex Noise Gray Shades");

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