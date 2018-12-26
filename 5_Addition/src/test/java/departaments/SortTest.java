package departaments;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortTest {

    @Test
    public void whenSortAscendingOne() {
        Sort test = new Sort();
        String[] departaments = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = new String[9];
        result[0] = "K1";
        result[1] = "K1\\SK1";
        result[2] = "K1\\SK1\\SSK1";
        result[3] = "K1\\SK1\\SSK2";
        result[4] = "K1\\SK2";
        result[5] = "K2";
        result[6] = "K2\\SK1";
        result[7] = "K2\\SK1\\SSK1";
        result[8] = "K2\\SK1\\SSK2";
        assertThat(test.sortInOrder(departaments), is(result));
    }

    @Test
    public void whenSortAscendingTwo() {
        Sort test = new Sort();
        String[] departaments = new String[]{"K2\\SK2", "K2\\SK2\\SSK1", "K2\\SK2\\SSK2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK3", "K3"};
        String[] result = new String[14];
        result[0] = "K1";
        result[1] = "K1\\SK1";
        result[2] = "K1\\SK1\\SSK1";
        result[3] = "K1\\SK1\\SSK2";
        result[4] = "K1\\SK1\\SSK3";
        result[5] = "K1\\SK2";
        result[6] = "K2";
        result[7] = "K2\\SK1";
        result[8] = "K2\\SK1\\SSK1";
        result[9] = "K2\\SK1\\SSK2";
        result[10] = "K2\\SK2";
        result[11] = "K2\\SK2\\SSK1";
        result[12] = "K2\\SK2\\SSK2";
        result[13] = "K3";
        assertThat(test.sortInOrder(departaments), is(result));
    }

    @Test
    public void whenSortInDicreasingOrderOne() {
        Sort test = new Sort();
        String[] departaments = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = new String[9];
        result[0] = "K2";
        result[1] = "K2\\SK1";
        result[2] = "K2\\SK1\\SSK2";
        result[3] = "K2\\SK1\\SSK1";
        result[4] = "K1";
        result[5] = "K1\\SK2";
        result[6] = "K1\\SK1";
        result[7] = "K1\\SK1\\SSK2";
        result[8] = "K1\\SK1\\SSK1";
        assertThat(test.sortInDicreasingOrder(departaments), is(result));
    }

    @Test
    public void whenSortInDicreasingOrderTwo() {
        Sort test = new Sort();
        String[] departaments = new String[]{"K2\\SK2", "K2\\SK2\\SSK1", "K2\\SK2\\SSK2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK3", "K3"};
        String[] result = new String[14];
        result[0] = "K3";
        result[1] = "K2";
        result[2] = "K2\\SK2";
        result[3] = "K2\\SK2\\SSK2";
        result[4] = "K2\\SK2\\SSK1";
        result[5] = "K2\\SK1";
        result[6] = "K2\\SK1\\SSK2";
        result[7] = "K2\\SK1\\SSK1";
        result[8] = "K1";
        result[9] = "K1\\SK2";
        result[10] = "K1\\SK1";
        result[11] = "K1\\SK1\\SSK3";
        result[12] = "K1\\SK1\\SSK2";
        result[13] = "K1\\SK1\\SSK1";
        assertThat(test.sortInDicreasingOrder(departaments), is(result));
    }
}
