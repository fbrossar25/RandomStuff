package application.sketch.noise;

import application.math.noise.OpenSimplex;

public class OpenSimplexNoiseCloud extends NoiseCloud {

    public OpenSimplexNoiseCloud() {
        super("OpenSimplex Noise Cloud", new OpenSimplex());
    }

}
