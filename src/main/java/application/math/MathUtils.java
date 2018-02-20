package application.math;

import application.math.geom.Segment;
import javafx.geometry.Point2D;

public class MathUtils {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }

    public static double scaled_cosine(double n) {
        return 0.5 * (1.0 - Math.cos(n * Math.PI));
    };

    public static int map(int value, int in_min, int in_max, int out_min, int out_max) {
        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static double map(double value, double in_min, double in_max, double out_min, double out_max) {
        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static int fastfloor(double x) {
        int xi = (int) x;
        return x < xi ? xi - 1 : xi;
    }

    // Angle in rads
    public static Point2D[] getRegularPolyogonPoints(double x, double y, int n, double size, double angleOffset) {
        Point2D[] points = new Point2D[n];
        double realSize = size / (Math.sqrt(2.0 - Math.sqrt(2.0)));
        double val;
        for (int i = 0; i < n; i++) {
            val = (i * 2 * Math.PI / n) + angleOffset;
            points[i] = new Point2D(x + realSize * Math.cos(val), y + realSize * Math.sin(val));
        }
        return points;
    }

    // Angle in rads
    public static Segment[] getPolygonSegments(double x, double y, int n, double size, double angleOffset) {
        Segment[] segments = new Segment[n];
        Point2D[] points = getRegularPolyogonPoints(x, y, n, size, angleOffset);
        Segment currentSegment;
        for (int i = 0; i < points.length; i++) {
            currentSegment = new Segment();
            currentSegment.xi = points[i].getX();
            currentSegment.yi = points[i].getY();
            currentSegment.xf = points[(i + 1) % points.length].getX();
            currentSegment.yf = points[(i + 1) % points.length].getY();
            segments[i] = currentSegment;
        }
        return segments;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // Angle in rads
    public static Point2D rotatePoint(Point2D p, Point2D c, double angle) {
        double effectiveAngle = angle > Math.PI ? angle - 2.0 * Math.PI : angle, dx = p.getX() - c.getX(), dy = p.getY() - c.getY(),
                cos = Math.cos(effectiveAngle), sin = Math.sin(effectiveAngle), rx = cos * dx + sin * dy + c.getX(),
                ry = cos * dy - sin * dx + c.getY();
        ;
        return new Point2D(rx, ry);
    }
}
