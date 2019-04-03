package c.collections.lite.a.framework.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convert list to array.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 1
 */
public class ConvertList2Array {
    /**
     * Method converts the list into a two-dimensional array.
     * @param list - list;
     * @param rows - number of lines;
     * @return - two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / (double) rows);
        int[][] array = new int[rows][cells];
        int x = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (x == list.size()) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = list.get(x);
                    x++;
                }
            }
        }
        return array;
    }
    /**
     * Method converts a two-dimensional array to a list.
     * @param array - two-dimensional array;
     * @return - list.
     */
    public List<Integer> convert(List<int[]> array) {
        List<Integer> result = new ArrayList<>();
        for (int[] massive : array) {
            for (int sym : massive) {
                result.add(sym);
            }
        }
        return result;
    }
}
