package application.sketch.noise;

import application.math.noise.ImprovedPerlin;

public class ImprovedPerlinNoiseCloud extends NoiseCloud {

    public ImprovedPerlinNoiseCloud() {
        super("Improved Perlin Noise Cloud", new ImprovedPerlin());
    }

}
