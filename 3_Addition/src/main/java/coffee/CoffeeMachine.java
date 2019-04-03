package coffee;

import java.util.Arrays;
/**
 * The class CoffeeMachine.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class CoffeeMachine {
    /**
     * Types of coins.
     */
    private final int[] coin = new int[]{10, 5, 2, 1};
    /**
     * The method realize calculation of coins for delivery.
     * @param value - amount of money;
     * @param price - price coffee;
     * @return - surrender.
     */
    public int[] coins(int value, int price) {
        int sum = value - price;
        int[] result = new int[50];
        int x = 0;
        for (int i = 0; i < coin.length; i++) {
            for (int j = 0; j < 100; j++) {
                if (sum - coin[i] >= 0) {
                    sum -= coin[i];
                    result[x] = coin[i];
                    x++;
                } else if (sum - coin[i] < 0) {
                    j = 100;
                }
            }
        }
        result = Arrays.copyOf(result, x);
        return result;
    }
}
