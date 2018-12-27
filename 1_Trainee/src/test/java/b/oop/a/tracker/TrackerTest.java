package b.oop.a.tracker;

import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TrackerTest {

    public Connection init() throws IOException, ClassNotFoundException, SQLException {
        Properties config = new Properties();
        File file = new File(System.getProperty("user.dir") + "/src/main/java/b/oop/a/tracker/config/database.properties");
        try (FileInputStream fin = new FileInputStream(file)) {
            config.load(fin);
        }
        Class.forName(config.getProperty("driver-class-name"));
        return DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"));
    }

    @Test
    public void createItem() throws Exception {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            Item item = tracker.findByName("name").get(0);
            tracker.replace(new Item(item.getId(), "replace", "desc"));
            assertThat(tracker.findById(item.getId()).getName(), is("replace"));
            tracker.delete(item.getId());
            assertThat(tracker.isEmpty(), is(true));
            tracker.add(new Item("TestNameOne", "desc"));
            tracker.add(new Item("TestNameTwo", "desc"));
            tracker.add(new Item("TestNameThree", "desc"));
            ArrayList<Item> result = tracker.getAll();
            assertThat(result.get(0).getName(), is("TestNameOne"));
            assertThat(result.get(1).getName(), is("TestNameTwo"));
            assertThat(result.get(2).getName(), is("TestNameThree"));
        }
    }
}
