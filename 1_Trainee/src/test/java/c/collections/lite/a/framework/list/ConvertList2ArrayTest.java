package c.collections.lite.a.framework.list;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class ConvertList2ArrayTest tests methods from class ConvertList2Array.
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version 1
 * @since 1
 */
public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when15ElementsThen16() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                4
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when15ElementsThen16Two() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when15ElementsThen15() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when15ElementsThen15gj() {
        ConvertList2Array list = new ConvertList2Array();
        int[] one = new int[]{1, 2};
        int[] two = new int[]{3, 4, 5};
        List<int[]> test = new ArrayList<int[]>();
        test.add(one);
        test.add(two);
        List<Integer> result = list.convert(test);
        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(2);
        expect.add(3);
        expect.add(4);
        expect.add(5);
        assertThat(result, is(expect));
    }
}
