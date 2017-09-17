package application.sketch;

import java.util.Timer;
import java.util.TimerTask;

import application.gui.canvas.SketchCanvas;
import application.math.Segment;

public class KochSnowflake extends Sketch {
    private int     currentLevel      = 0;
    private int     desiredLevel      = 10;
    private long    timeBetweenLevels = 1000;
    private Segment firstSegment;
    private Timer   timer             = new Timer();

    @Override
    public void setup(SketchCanvas canvas) {
        canvas.noLoop();
        canvas.draw();
    }

    @Override
    public void update(SketchCanvas canvas) {
        double h = canvas.getHeight() / 2.0;
        firstSegment = new Segment(0.0, canvas.getWidth(), h, h);
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        currentLevel %= desiredLevel;
        drawSnowFlake(canvas, firstSegment, currentLevel);
        scheduleNextDraw(canvas);
        currentLevel++;
    }

    private void scheduleNextDraw(SketchCanvas canvas) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canvas.draw();
            }
        }, timeBetweenLevels);
    }

    private void drawSnowFlake(SketchCanvas canvas, Segment s, int level) {
        if (level == 0) {
            s.draw(canvas);
            return;
        }
        Segment s1 = new Segment();
        Segment s2 = new Segment();
        Segment s3 = new Segment();
        Segment s4 = new Segment();

        double dx = (s.xf - s.xi) / 3.0;
        double dy = (s.yf - s.yi) / 3.0;
        s1.xi = s.xi;
        s1.yi = s.yi;
        s1.xf = s.xi + dx;
        s1.yf = s.yi + dy;
        drawSnowFlake(canvas, s1, level - 1);
        s2.xi = s1.xf;
        s2.yi = s1.yf;
        s2.xf = s2.xi + (dx - Math.sqrt(3.0) * dy) / 2.0;
        s2.yf = s2.yi + (dy + Math.sqrt(3.0) * dx) / 2.0;
        drawSnowFlake(canvas, s2, level - 1);
        s3.xi = s2.xf;
        s3.yi = s2.yf;
        s3.xf = s.xf - dx;
        s3.yf = s.yf - dy;
        drawSnowFlake(canvas, s3, level - 1);
        s4.xi = s3.xf;
        s4.yi = s3.yf;
        s4.xf = s.xf;
        s4.yf = s.yf;
        drawSnowFlake(canvas, s4, level - 1);

    }
}