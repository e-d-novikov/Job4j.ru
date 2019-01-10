package d.servlet.cinema;

public class CinemaValidate {

    private static volatile CinemaValidate instance = new CinemaValidate();
    private final CinemaStorage memory;

    public CinemaValidate() {
        memory = new CinemaStorage(10, 8);
    }

    public static CinemaValidate getInstance() {
        return instance;
    }
}
