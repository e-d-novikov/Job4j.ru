package c.collections.lite.d.control;
/**
 * Class SortUserTest tests methods from class UserTest.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User data.
     */
    private int passport;

    User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return this.name;
    }

    public int getPassport() {
        return this.passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "User name " + name + " Passport " + passport;
    }
}
