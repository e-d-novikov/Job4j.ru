package b.oop.a.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class StartUITest checks the correctness of the application.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 18/06/2018
 */
public class StartUITest {

    private final Tracker tracker = new Tracker();
    private final Item one = new Item("test 1", "desc 1", 1);
    private final Item two = new Item("test 2", "desc 2", 12);
    private final Item[] test = new Item[]{};
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    public String l = System.lineSeparator();


    @Before
    public void inputdata() {
        tracker.add(one);
        tracker.add(two);
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenAddItem() {
        Input input = new StubInput(new String[]{"1", "testname", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Application with ID: " + tracker.getAll().get(2).getId() + " created"
                + l));
    }

    @Test
    public void whenUpdateItem() {
        Input input = new StubInput(new String[]{"2", one.getId(), "replace", "replace", "n", "4", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Edit application"
                + l + "Application with ID:" + one.getId() + " updated"
                + l + "Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Current applications:"
                + l + one.getId() + " replace replace"
                + l + two.getId() + " test 2 desc 2"
                + l));
    }

    @Test
    public void whenDeleteItem() {
        Input input = new StubInput(new String[]{"3", one.getId(), "n", "4", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Delete an order"
                + l + "Application with ID: " + one.getId() + " removed"
                + l + "Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Current applications:"
                + l + two.getId() + " test 2 desc 2"
                + l));
    }

    @Test
    public void whenGetAllItems() {
        Input input = new StubInput(new String[]{"4", "y"});
        new StartUI(input, tracker).init();
        Item[] test = new Item[]{one, two};
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Current applications:"
                + l + one.getId() + " test 1 desc 1"
                + l + two.getId() + " test 2 desc 2"
                + l));
    }

    @Test
    public void whenFindId() {
        Input input = new StubInput(new String[]{"5", one.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Search by ID"
                + l + one.getId() + " test 1 desc 1"
                + l));
    }

    @Test
    public void whenFindByName() {
        Input input = new StubInput(new String[]{"6", two.getName(), "y"});
        new StartUI(input, tracker).init();
        Item[] test = new Item[]{two};
        assertThat(new String(this.out.toByteArray()), is("Application Editor"
                + l + "Please select action:"
                + l + "1. Add application"
                + l + "2. Edit application"
                + l + "3. Remove application"
                + l + "4. Current applications"
                + l + "5. Search by ID"
                + l + "6. Search by name"
                + l + "7. Exit"
                + l + "Search by name"
                + l + two.getId() + " test 2 desc 2"
                + l));
    }
}