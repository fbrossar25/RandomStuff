package application.math.noise;

public interface NoiseGenerator {
    double noise(double x);

    double noise(double x, double y);

    double noise(double x, double y, double z);
}
