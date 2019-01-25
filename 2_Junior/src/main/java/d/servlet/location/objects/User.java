package d.servlet.location.objects;
/**
 * Класс User.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class User {

    private String name;
    private String surname;
    private String sex;
    private String country;
    private String region;
    private String city;
    private String description;

    public User() {

    }

    public User(String name, String surname, String sex, String country, String region, String city, String description) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.country = country;
        this.region = region;
        this.city = city;
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

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }
}
