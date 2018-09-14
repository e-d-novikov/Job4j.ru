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

    HashMapClass test = new HashMapClass<>();
    User userOne = new User("FirstUserName", "FirstUserSurname", 1212, 121212, 30);
    User userTwo = new User("SecondUserName", "SecondUserSurname", 5896, 288796, 28);
    User userThree = new User("ThirdUserName", "ThirdUserSurname", 7895, 284896, 55);
    User userNull = null;

    @Test
    public void whenHasCycle() {
        test.insert(userOne.hashCode(), userOne);
        test.insert(userTwo.hashCode(), userTwo);
        test.insert(userThree.hashCode(), userThree);
        assertThat(test.get(userOne.hashCode()), is(userOne));
        assertThat(test.get(userTwo.hashCode()), is(userTwo));
        assertThat(test.get(userThree.hashCode()), is(userThree));
        test.delete(userOne.hashCode());
        test.delete(userTwo.hashCode());
        test.delete(userThree.hashCode());
        assertThat(test.get(userOne.hashCode()), is(userNull));
        assertThat(test.get(userTwo.hashCode()), is(userNull));
        assertThat(test.get(userThree.hashCode()), is(userNull));
    }

    private static class User {

        String name;
        String surname;
        int passportSeries;
        int passportNumber;
        int age;

        public User(String name, String surname, int passportSeries, int passportNumber, int age) {
            this.name = name;
            this.surname = surname;
            this.passportSeries = passportSeries;
            this.passportNumber = passportNumber;
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
            return passportSeries == user.passportSeries
                    && passportNumber == user.passportNumber
                    && age == user.age
                    && Objects.equals(name, user.name)
                    && Objects.equals(surname, user.surname);
        }

        @Override
        public int hashCode() {
            int result = 31;
            result = result * passportSeries;
            result = result * passportNumber;
            result = result * age;
            return result;
        }
    }
}
