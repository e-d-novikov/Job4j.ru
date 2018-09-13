package c.collections.lite.a.framework.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Phone book.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class PhoneBook {
    /**
     * Phone book.
     */
    private List<Person> book = new ArrayList<Person>();
    /**
     * Method adds person to phone directory.
     * @param person - person.
     */
    public void add(Person person) {
        this.book.add(person);
    }
    /**
     * Find person by key.
     * @param key - key.
     * @return - person.
     */
    public List<Person> find(String key) {
        List<Person> result = book.stream().filter(person -> person.getName().contains(key)
                || person.getSurname().contains(key)
                || person.getPhone().contains(key)
                || person.getAddress().contains(key)
        ).collect(Collectors.toList());
        return result;
    }
}