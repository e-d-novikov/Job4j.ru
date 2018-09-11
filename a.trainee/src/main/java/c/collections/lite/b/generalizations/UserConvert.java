package c.collections.lite.b.generalizations;

import java.util.HashMap;
import java.util.List;
/**
 * Class UserConvert.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class UserConvert {
    /**
     * The method converts the list to map.
     * @param list - list;
     * @return - map.
     */
    public HashMap<Integer, User> convert(List<User> list) {
        HashMap<Integer, User> result = new HashMap<Integer, User>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
