package application.sketch.fractal;

import java.util.Timer;
import java.util.TimerTask;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.math.MathUtils;
import application.math.geom.Segment;
import application.sketch.Sketch;

public class KochSnowflake extends Sketch {
    private final static int    DEFAULT_SIDE_LENGTH  = 100;
    private final static int    DEFAULT_SIDES_NUMBER = 3;
    private final static double DEFAULT_ANGLE        = -Math.PI / 2.0; // angle to have the first summit on top
    public final static long    DEFAULT_DELAY        = 1000;
    public final static int     MAX_LEVELS           = 7;              // no significant changes beyond 7 levels
    private int                 currentLevel;
    private long                timeBetweenLevels    = DEFAULT_DELAY;
    private Segment[]           beginingSegments;
    private int                 sides;
    private double              length;
    private double              angle;
    private boolean             reverse              = false;          // true mean draw towards center
    private Timer               timer                = null;
    private boolean             disableTimer         = false;

    public KochSnowflake() {
        this(DEFAULT_SIDES_NUMBER);
    }

    public KochSnowflake(int sides) {
        this(sides, DEFAULT_SIDE_LENGTH);
    }

    public KochSnowflake(int sides, int sideLength) {
        this(sides, sideLength, DEFAULT_ANGLE);
    }

    // angle in rads
    public KochSnowflake(int sides, int sideLength, double angle) {
        super("Koch Snowflake");
        if (sides < 3) {
            throw new IllegalArgumentException("sides must be >= 3");
        }
        this.sides = sides;
        length = sideLength;
        this.angle = angle;
    }

    public void reverse() {
        reverse = !reverse;
    }

    @Override
    public void setup() {
        currentLevel = 0;
        if (timer != null)
            timer.cancel();
        timer = new Timer();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(SketchCanvas canvas) {
        canvas.clear();
        canvas.noLoop();
        length = Math.min(canvas.getWidth() / 3.0, canvas.getHeight() / 3.0);
        initSegments(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
        currentLevel %= MAX_LEVELS;
        for (Segment s : beginingSegments) {
            drawSnowflake(canvas, s, currentLevel);
        }
        if (!disableTimer) {
            currentLevel++;
            scheduleNextDraw(canvas);
        }
    }

    private void initSegments(double cx, double cy) {
        beginingSegments = MathUtils.getPolygonSegments(cx, cy, sides, length, angle);
        if (!reverse) { // needed because final and initial points are reverted
            for (Segment s : beginingSegments) {
                s.revert();
            }
        }
    }

    private void scheduleNextDraw(SketchCanvas canvas) {
        if (timer == null)
            return;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canvas.draw();
            }
        }, timeBetweenLevels);
    }

    private void drawSnowflake(SketchCanvas canvas, Segment s, int level) {
        drawSnowflake(canvas, s, level, false);
    }

    private void drawSnowflake(SketchCanvas canvas, Segment s, int level, boolean reverse) {
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
        drawSnowflake(canvas, s1, level - 1, reverse);
        s2.xi = s1.xf;
        s2.yi = s1.yf;
        s2.xf = s2.xi + (dx - Math.sqrt(3.0) * dy) / 2.0;
        s2.yf = s2.yi + (dy + Math.sqrt(3.0) * dx) / 2.0;
        drawSnowflake(canvas, s2, level - 1, reverse);
        s3.xi = s2.xf;
        s3.yi = s2.yf;
        s3.xf = s.xf - dx;
        s3.yf = s.yf - dy;
        drawSnowflake(canvas, s3, level - 1, reverse);
        s4.xi = s3.xf;
        s4.yi = s3.yf;
        s4.xf = s.xf;
        s4.yf = s.yf;
        drawSnowflake(canvas, s4, level - 1, reverse);
    }

    @Override
	public SketchControls getControls() {
		// TODO Auto-generated method stub
		return null;
	}
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
        timer.cancel();
    }
}