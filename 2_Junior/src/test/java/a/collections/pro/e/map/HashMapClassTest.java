package a.collections.pro.e.map;

import org.junit.Test;
import java.util.Objects;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class HashMapClassTest tests methods from HashMapClass.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class HashMapClassTest {

    HashMapClass<String, User> test = new HashMapClass<>();
    User userNull = null;

    @Test
    public void whenAdd20Get20Delete20() {
        User[] users = new User[1200];
        String testName = "Test Name";
        String testSurname = "Test Surname";
        int testPassport = 1234567890;
        int testAge = 0;
        int i = 0;
        while (i < 1200) {
            User user = new User(testName + " " + i, testSurname + " " + i, String.valueOf(testPassport + i), testAge + i);
            users[i] = user;
            test.insert(user.passport, user);
            i++;
        }
        int j = 0;
        while (j < 1200) {
            assertThat(test.get(users[j].passport), is(users[j]));
            test.delete(users[j].passport);
            j++;
        }
        assertThat(test.size(), is(0));
    }

    private static class User {

        String name;
        String surname;
        String passport;
        int age;

        public User(String name, String surname, String passport, int age) {
            this.name = name;
            this.surname = surname;
            this.passport = passport;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return passport == user.passport
                    && age == user.age
                    && Objects.equals(name, user.name)
                    && Objects.equals(surname, user.surname);
        }

        @Override
        public int hashCode() {
            int result = 31;
            result = result * passport.hashCode();
            result = result * age;
            return result;
        }
    }
}
