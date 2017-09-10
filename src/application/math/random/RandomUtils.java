package application.math.random;

public class RandomUtils {

    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    public static int randomInt(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }

    public static int randomIntInclusive(int max) {
        return randomIntInclusive(0, max);
    }

    public static int randomIntInclusive(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
