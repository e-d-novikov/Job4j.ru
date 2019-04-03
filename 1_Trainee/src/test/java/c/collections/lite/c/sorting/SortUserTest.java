package c.collections.lite.c.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class SortUserTest tests methods from class UserTest.
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version 1
 * @since 1
 */
public class SortUserTest {

    @Test
    public void whenSort() {
        SortUser list = new SortUser();
        List<User> test = new ArrayList<User>();
        User one = new User("Вася", 20);
        User two = new User("Коля", 26);
        User three = new User("Дима", 18);
        test.add(one);
        test.add(two);
        test.add(three);
        TreeSet<User> result = new TreeSet<User>();
        result.add(three);
        result.add(one);
        result.add(two);
        assertThat(list.sort(test), is(result));
    }

    @Test
    public void whenSortByAllFields() {
        SortUser list = new SortUser();
        List<User> test = new ArrayList<User>();
        User one = new User("Василий", 20);
        User two = new User("Коля", 26);
        User three = new User("Антон", 18);
        User four = new User("Василий", 22);
        User five = new User("Антон", 19);
        test.add(one);
        test.add(two);
        test.add(three);
        test.add(four);
        test.add(five);
        List<User> result = new ArrayList<User>();
        result.add(three);
        result.add(five);
        result.add(one);
        result.add(four);
        result.add(two);
        assertThat(list.sortByAllFields(test), is(result));
    }

    @Test
    public void whenSortNameLength() {
        SortUser list = new SortUser();
        List<User> test = new ArrayList<User>();
        User one = new User("Василий", 20);
        User two = new User("Коля", 26);
        User three = new User("Антон", 18);
        User four = new User("Василий", 22);
        User five = new User("Антон", 19);
        test.add(one);
        test.add(two);
        test.add(three);
        test.add(four);
        test.add(five);
        List<User> result = new ArrayList<User>();
        result.add(one);
        result.add(four);
        result.add(three);
        result.add(five);
        result.add(two);
        assertThat(list.sortNameLength(test), is(result));
    }
}
