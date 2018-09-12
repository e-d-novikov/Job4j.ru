package c.collections.lite.d.control;
/**
 * Account.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class Account {
    /**
     * Amount of money on the account.
     */
    private double value;
    /**
     * Account details.
     */
    private int requisites;

    Account(int value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getRequisites() {
        return this.requisites;
    }

    @Override
    public String toString() {
        return "Account " + requisites + " Amount " + value;
    }
}
