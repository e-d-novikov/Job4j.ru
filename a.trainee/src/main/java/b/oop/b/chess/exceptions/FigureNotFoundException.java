package b.oop.b.chess.exceptions;

public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}