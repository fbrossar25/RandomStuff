package application.sketch;

public class SimplexGrayShades extends PerlinGrayShades {

    public SimplexGrayShades() {
        super(false); // false to use simplex instead of perlin
    }

}
