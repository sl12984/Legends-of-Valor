import java.util.Random;
public class Dice {
    public static int rowDice() {
        Random rand = new Random();
        int num = rand.nextInt(6);
        return num + 1;
    }
}
