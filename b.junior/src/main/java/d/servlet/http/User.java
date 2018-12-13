package d.servlet.http;

public class User {

    private final Integer id;
    private final String login;
    private final String password;
    private final String role;
    private final String name;
    private final String sername;
    private final String email;

    public User(Integer id, String login, String password, String role, String name, String sername, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.sername = sername;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSername() {
        return sername;
    }

    public String getEmail() {
        return email;
    }
}
