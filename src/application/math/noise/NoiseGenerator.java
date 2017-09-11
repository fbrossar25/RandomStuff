package application.math.noise;

import application.math.MathUtils;

public abstract class NoiseGenerator {
    public double getMaxValue() {
        return 1.0;
    }

    public double getMinValue() {
        return -1.0;
    }

    public int grayRGBForNoise(double x) {
        return MathUtils.fastfloor(MathUtils.map(noise(x), getMinValue(), getMaxValue(), 0, 255));
    }

    public int grayRGBForNoise(double x, double y) {
        return MathUtils.fastfloor(MathUtils.map(noise(x, y), getMinValue(), getMaxValue(), 0, 255));
    }

    public int grayRGBForNoise(double x, double y, double z) {
        return MathUtils.fastfloor(MathUtils.map(noise(x, y, z), getMinValue(), getMaxValue(), 0, 255));
    }

    public double grayForNoise(double x) {
        return MathUtils.map(noise(x), getMinValue(), getMaxValue(), 0.0, 1.0);
    }

    public double grayForNoise(double x, double y) {
        return MathUtils.map(noise(x, y), getMinValue(), getMaxValue(), 0.0, 1.0);
    }

    public double grayForNoise(double x, double y, double z) {
        return MathUtils.map(noise(x, y, z), getMinValue(), getMaxValue(), 0.0, 1.0);
    }

    public abstract double noise(double x);

    public abstract double noise(double x, double y);

    public abstract double noise(double x, double y, double z);

    public abstract void reseed(long seed);
}
