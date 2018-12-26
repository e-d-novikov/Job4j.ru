package a.collections.pro.b.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class UserStoreTest tests methods from class UserStore.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class UserStoreTest {

    UserStore user = new UserStore();
    User one = new User("One");
    User two = new User("Two");
    User three = new User("Three");
    User four = new User("Four");

    @Before
    public void beforeTest() {
        user.add(one);
        user.add(two);
        user.add(three);
    }
    @Test
    public void whenFindById() {
        assertThat(user.findById("One"), is(one));
        assertThat(user.findById("Two"), is(two));
        assertThat(user.findById("Three"), is(three));
    }

    @Test
    public void whenReplace() {
        user.replace("One", four);
        assertThat(user.findById("Four"), is(four));
    }

    @Test
    public void whenDelete() {
        assertThat(user.delete("One"), is(true));
        assertThat(user.delete("Two"), is(true));
        assertThat(user.delete("Three"), is(true));
    }
}
