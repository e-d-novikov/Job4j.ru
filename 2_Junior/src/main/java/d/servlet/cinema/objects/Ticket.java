package d.servlet.cinema.objects;

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
}
