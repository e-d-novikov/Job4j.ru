package d.servlet.cinema.objects;

public class Account {

    Integer id;
    String name;
    String phone;

    public Account() {

    }

    public Account(String name, String phone) {
        this.id = null;
        this.name = name;
        this.phone = phone;
    }

    public Account(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
