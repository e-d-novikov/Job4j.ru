package d.servlet.json;

public class User {

    private String name;
    private String surname;
    private String sex;
    private String description;

    public User() {

    }

    public User(String name, String surname, String sex, String description) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", surname=" + surname + ", sex=" + sex + ", description=" + description + "]";
    }
}
