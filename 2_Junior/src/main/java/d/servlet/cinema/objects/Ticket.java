package d.servlet.cinema.objects;

public class Ticket {

    private int row;
    private int place;
    private String person;
    private String phone;
    private int price;

    public Ticket(int row, int place, String person, String phone, int price) {
        this.row = row;
        this.place = place;
        this.person = person;
        this.phone = phone;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public String getPerson() {
        return person;
    }

    public String getPhone() {
        return phone;
    }

    public int getPrice() {
        return price;
    }
}
