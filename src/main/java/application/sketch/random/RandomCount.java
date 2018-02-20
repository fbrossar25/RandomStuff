package application.sketch.random;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.gui.controls.sketch.RandomControls;
import application.math.random.RandomUtils;
import application.sketch.Sketch;

public class RandomCount extends Sketch {

    private int counters[];

    public RandomCount() {
        super("Random Counters");
        counters = new int[21];
    }

    @Override
    public void setup() {
        for (int i = 0; i < counters.length; i++) {
            counters[i] = 0;
        }
    }

    @Override
    public void update() {
        int idx = RandomUtils.randomIntInclusive(counters.length - 1);
        counters[idx]++;
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        double rectWidth = canvas.getWidth() / counters.length;
        double spacing = 1.0;
        for (int i = 0; i < counters.length; i++) {
            canvas.setFill(173, 173, 173);
            canvas.fillRect(i * rectWidth, canvas.getHeight() - counters[i], rectWidth - spacing, counters[i]);
            canvas.setFill(0, 0, 0);
            canvas.fillText(i, i * rectWidth + 3, canvas.getHeight() - 25);
            canvas.fillText(counters[i], i * rectWidth + 3, canvas.getHeight() - 10);
        }
    }

    @Override
    public SketchControls getControls() {
        return new RandomControls(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < counters.length; i++) {
            sb.append(counters[i] + " ");
        }
        return sb.toString();
    }
}
