package c.collections.lite.b.generalizations;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class UserConvertTest test method from class UserConvert.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class UserConvertTest {
  @Test
  public void whenFind() {
    UserConvert test = new UserConvert();
    User one = new User(1, "name", "city");
    User two = new User(2, "name", "city");
    User three = new User(3, "name", "city");
    User four = new User(4, "name", "city");
    ArrayList<User> users = new ArrayList<>();
    users.add(one);
    users.add(two);
    users.add(three);
    users.add(four);
    Map<Integer, User> result = new HashMap<>();
    result.put(one.getId(), one);
    result.put(two.getId(), two);
    result.put(three.getId(), three);
    result.put(four.getId(), four);
    assertThat(test.convert(users), is(result));
  }
}
