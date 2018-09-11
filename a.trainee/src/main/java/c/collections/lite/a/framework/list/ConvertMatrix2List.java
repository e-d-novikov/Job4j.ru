package c.collections.lite.a.framework.list;

import java.util.ArrayList;
import java.util.List;
/**
 * Converting a two-dimensional array to a list.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class ConvertMatrix2List {
    /**
     * Method convert two-dimensional array to a list.
     * @param array - two-dimensional array;
     * @return - list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] one : array) {
            for (int two : one) {
                list.add(two);
            }
        }
        return list;
    }
}
