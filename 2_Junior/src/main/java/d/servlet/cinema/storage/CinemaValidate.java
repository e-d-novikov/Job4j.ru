package d.servlet.cinema.storage;

public class CinemaValidate {

    private static volatile CinemaValidate instance = new CinemaValidate();
    private final CinemaStorage memory;

    public CinemaValidate() {
        memory = new CinemaStorage();
    }

    public static CinemaValidate getInstance() {
        return instance;
    }
}
