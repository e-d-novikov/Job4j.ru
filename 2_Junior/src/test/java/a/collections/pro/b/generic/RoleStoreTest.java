package a.collections.pro.b.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class RoleStoreTest tests methods from class RoleStore.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class RoleStoreTest {

    RoleStore role = new RoleStore();
    Role one = new Role("One");
    Role two = new Role("Two");
    Role three = new Role("Three");
    Role four = new Role("Four");

    @Before
    public void beforeTest() {
        role.add(one);
        role.add(two);
        role.add(three);
    }
    @Test
    public void whenFindById() {
        assertThat(role.findById("One"), is(one));
        assertThat(role.findById("Two"), is(two));
        assertThat(role.findById("Three"), is(three));
    }

    @Test
    public void whenReplace() {
        role.replace("One", four);
        assertThat(role.findById("Four"), is(four));
    }

    @Test
    public void whenDelete() {
        assertThat(role.delete("One"), is(true));
        assertThat(role.delete("Two"), is(true));
        assertThat(role.delete("Three"), is(true));
    }
}
