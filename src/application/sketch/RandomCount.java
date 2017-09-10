package application.sketch;

import application.gui.canvas.SketchCanvas;
import application.math.random.RandomUtils;
import javafx.scene.paint.Color;

public class RandomCount extends Sketch {

    private int counters[];

    public RandomCount() {
        super("Random Counters");
        counters = new int[21];
    }

    @Override
    public void setup(SketchCanvas canvas) {
        for (int i = 0; i < counters.length; i++) {
            counters[i] = 0;
        }
        canvas.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void update(SketchCanvas canvas) {
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
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < counters.length; i++) {
            sb.append(counters[i] + " ");
        }
        return sb.toString();
    }
}
