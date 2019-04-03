package soft;
/**
 * Тестовое задание Zaycev.net
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 */
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TaskTest {

    @Test
    public void whenSteps7864AndCycleLength1695() {
        int[] array = new int[]{0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11};
        Task task = new Task(array);
        assertThat(task.getSteps(), is(7864));
        assertThat(task.getCycleLength(), is(1695));
    }
}
