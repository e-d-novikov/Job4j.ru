package c.collections.lite.a.framework.search;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class PhoneDictionaryTest tests methods from class PhoneDictionary.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class PhoneBookTest {

    @Test
    public void whenFind() {
        PhoneBook phones = new PhoneBook();
        Person person = new Person("Petr", "Arsentev", "534872", "Bryansk");
        phones.add(person);
        List<Person> persons = new ArrayList<Person>();
        persons.add(person);
        assertThat(phones.find("Petr"), is(persons));
        assertThat(phones.find("Arsentev"), is(persons));
        assertThat(phones.find("534872"), is(persons));
        assertThat(phones.find("Bryansk"), is(persons));
    }
}