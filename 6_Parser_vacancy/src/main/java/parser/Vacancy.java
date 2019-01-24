package parser;

import java.sql.Timestamp;

public class Vacancy {

    private String name;
    private String text;
    private String link;
    private Timestamp date;

    public Vacancy(String name, String text, String link, Timestamp date) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public Timestamp getDate() {
        return date;
    }
}
