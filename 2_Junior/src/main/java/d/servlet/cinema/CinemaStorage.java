package d.servlet.cinema;

import d.servlet.cinema.objects.Ticket;

public class CinemaStorage {

    private Ticket[][] tickets;
    private static final CinemaStorage INSTANCE = new CinemaStorage(10, 8);

    public CinemaStorage(int rows, int places) {
        tickets = new Ticket[rows][places];
    }

    public static CinemaStorage getInstance() {
        return INSTANCE;
    }

    //TODO
}
