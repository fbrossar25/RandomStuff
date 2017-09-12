package application.sketch;

import application.math.noise.ImprovedPerlin;

public class ImprovedPerlinNoiseCloud extends NoiseCloud {

    public ImprovedPerlinNoiseCloud() {
        super(new ImprovedPerlin());
    }

}
