package application.sketch;

import application.math.noise.OpenSimplex;

public class OpenSimplexNoiseCloud extends NoiseCloud {

    public OpenSimplexNoiseCloud() {
        super(new OpenSimplex());
    }

}
