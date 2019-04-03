package b.multithreading.c.synchronization.storage;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class UserStorageTest.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class UserStorageTest {

    UserStorage stor = new UserStorage();

    @Test
    public void whenHasCycle() {
        stor.add(new User(1, 1000));
        stor.add(new User(2, 1000));
        stor.add(new User(3, 1000));
        stor.add(new User(4, 1000));
        stor.transfer(4, 2, 1000);
        stor.transfer(1, 2, 500);
        stor.transfer(3, 2, 250);
        stor.delete(4);
        assertThat(stor.getUserAmountFromId(1), is(500));
        assertThat(stor.getUserAmountFromId(2), is(2750));
        assertThat(stor.getUserAmountFromId(3), is(750));
        assertThat(stor.getUserAmountFromId(4), is(-1));
    }
}
