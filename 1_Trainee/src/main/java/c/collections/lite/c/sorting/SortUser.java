package c.collections.lite.c.sorting;;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class SortUser.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 1
 */
public class SortUser {
    /**
     * The method returns the TreeSet of users sorted by age in ascending order.
     * @param users - list users.
     * @return - TreeSet of users.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<User>(users);
    }
    /**
     * The method returns a list of users sorted by length of name in ascending order.
     * @param users - list users;
     * @return - list of users sorted by length of name in ascending order.
     */
    public List<User> sortNameLength(List<User> users) {
        return users.stream().sorted((p1, p2) -> Integer.compare(p2.name.length(), p1.name.length())).collect(Collectors.toList());
    }
    /**
     * The method returns a list of users sorted by name in lexicographical order and then by age in ascending order.
     * @param users - list users;
     * @return - list of users sorted by name in lexicographical order and then by age in ascending order.
     */
    public List<User> sortByAllFields(List<User> users) {
      return users.stream().sorted(new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
          int result = o1.name.compareTo(o2.name);
          return result != 0 ? result : Integer.compare(o1.age, o2.age);
        }
      }).collect(Collectors.toList());
    }
}
