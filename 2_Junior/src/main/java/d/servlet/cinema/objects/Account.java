package d.servlet.cinema.objects;

import java.util.ArrayList;

public class Account {

    int id;
    String name;
    String phone;
    ArrayList<Ticket> tickets;

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

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void deleteTicket(int id) {

    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
