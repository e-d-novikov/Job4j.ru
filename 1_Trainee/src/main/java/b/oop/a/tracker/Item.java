package b.oop.a.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {

    private final Integer id;
    private final String name;
    private final String desc;
    private final String date;

    public Item(String name, String desc) {
        this.id = null;
        this.name = name;
        this.desc = desc;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM uuuuг. HH:mm"));
    }

    public Item(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM uuuuг. HH:mm"));
    }

    public Item(Integer id, String name, String desc, String date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s", id, name, desc, date);
    }
}
