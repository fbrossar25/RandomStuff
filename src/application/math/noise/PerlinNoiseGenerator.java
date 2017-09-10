package application.math.noise;

import application.math.MathUtils;
import application.math.random.LinearCongruentialGenerator;

/**
 * Adaptation of the p5.js noise() function.<br>
 * See their project at p5js.org
 */
public class PerlinNoiseGenerator implements NoiseGenerator {
    private int      PERLIN_YWRAPB      = 4;
    private int      PERLIN_YWRAP       = 1 << PERLIN_YWRAPB;
    private int      PERLIN_ZWRAPB      = 8;
    private int      PERLIN_ZWRAP       = 1 << PERLIN_ZWRAPB;
    private int      PERLIN_SIZE        = 4095;

    private double   perlin_octaves     = 1;
    private double   perlin_amp_falloff = 0.5;

    private double[] random_samples;

    public PerlinNoiseGenerator() {
        random_samples = new double[PERLIN_SIZE + 1];
        for (int i = 0; i < PERLIN_SIZE + 1; i++) {
            random_samples[i] = Math.random();
        }
    }

    @Override
    public double noise(double x) {
        return noise(x, 0, 0);
    }

    @Override
    public double noise(double x, double y) {
        return noise(x, y, 0);
    }

    @Override
    public double noise(double x, double y, double z) {
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        if (z < 0) {
            z = -z;
        }

        int xi = MathUtils.fastfloor(x);
        int yi = MathUtils.fastfloor(y);
        int zi = MathUtils.fastfloor(z);
        double xf = x - xi;
        double yf = y - yi;
        double zf = z - zi;
        double rxf, ryf;

        double r = 0;
        double ampl = 0.5;

        double n1, n2, n3;

        for (int o = 0; o < perlin_octaves; o++) {
            int of = xi + (yi << PERLIN_YWRAPB) + (zi << PERLIN_ZWRAPB);

            rxf = MathUtils.scaled_cosine(xf);
            ryf = MathUtils.scaled_cosine(yf);

            n1 = random_samples[of & PERLIN_SIZE];
            n1 += rxf * (random_samples[(of + 1) & PERLIN_SIZE] - n1);
            n2 = random_samples[(of + PERLIN_YWRAP) & PERLIN_SIZE];
            n2 += rxf * (random_samples[(of + PERLIN_YWRAP + 1) & PERLIN_SIZE] - n2);
            n1 += ryf * (n2 - n1);

            of += PERLIN_ZWRAP;
            n2 = random_samples[of & PERLIN_SIZE];
            n2 += rxf * (random_samples[(of + 1) & PERLIN_SIZE] - n2);
            n3 = random_samples[(of + PERLIN_YWRAP) & PERLIN_SIZE];
            n3 += rxf * (random_samples[(of + PERLIN_YWRAP + 1) & PERLIN_SIZE] - n3);
            n2 += ryf * (n3 - n2);

            n1 += MathUtils.scaled_cosine(zf) * (n2 - n1);

            r += n1 * ampl;
            ampl *= perlin_amp_falloff;
            xi <<= 1;
            xf *= 2;
            yi <<= 1;
            yf *= 2;
            zi <<= 1;
            zf *= 2;

            if (xf >= 1.0) {
                xi++;
                xf--;
            }
            if (yf >= 1.0) {
                yi++;
                yf--;
            }
            if (zf >= 1.0) {
                zi++;
                zf--;
            }
        }
        return r;
    }

    public void noiseDetail(double lod, double falloff) {
        if (lod > 0) {
            perlin_octaves = lod;
        }
        if (falloff > 0) {
            perlin_amp_falloff = falloff;
        }
    }

    public void noiseSeed(long seed) {
        LinearCongruentialGenerator lcg = new LinearCongruentialGenerator();

        lcg.setSeed(seed);
        for (int i = 0; i < PERLIN_SIZE + 1; i++) {
            random_samples[i] = lcg.rand();
        }
    }

    public int mapToInt(double noiseValue, int min, int max) {
        return (int) MathUtils.map(noiseValue, 0.0, 1.0, min, max);
    }

    public int grayShadeForNoise(double x, double y, double z) {
        return mapToInt(noise(x, y, z), 0, 255);
    }
}
