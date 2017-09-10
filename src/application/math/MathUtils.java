package application.math;

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
}
