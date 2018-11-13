package d.servlet.http;

public class User {

    private final int id;
    private final String name;
    private final String login;
    private final String email;
    private final String date;

    public User(int id, String name, String login, String email, String date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + login + " " + email + " " + date;
    }
}
