package application.math.random;

public class LinearCongruentialGenerator {
    double m = 4294967296.0, a = 1664525, c = 1013904223, z;
    long   seed;

    public LinearCongruentialGenerator() {
        // TODO Auto-generated constructor stub
    }

    public void setSeed(long seed) {
        z = this.seed = seed >>> 0;
    }

    public long getSeed() {
        return seed;
    }

    public double rand() {
        z = (a * z + c) % m;
        return z / m;
    }
}
