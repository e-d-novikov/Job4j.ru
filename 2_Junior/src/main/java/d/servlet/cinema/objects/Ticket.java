package d.servlet.cinema.objects;

import java.util.Objects;

/**
 * Класс билет.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Ticket {

    private int id;
    private int row;
    private int place;
    private int price;
    private Account account;

    public Ticket() {

    }

    public Ticket(int id, int row, int place, int price) {
        this.id = id;
        this.row = row;
        this.place = place;
        this.price = price;
        this.account = null;
    }

    public Ticket(int id, int row, int place, int price, Account account) {
        this.id = id;
        this.row = row;
        this.place = place;
        this.price = price;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id
                && row == ticket.row
                && place == ticket.place
                && price == ticket.price
                && Objects.equals(account, ticket.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, place, price, account);
    }
}
