package application.sketch;

import application.math.noise.Perlin;

public class PerlinNoiseCloud extends NoiseCloud {

    public PerlinNoiseCloud() {
        super(new Perlin());
    }

}
