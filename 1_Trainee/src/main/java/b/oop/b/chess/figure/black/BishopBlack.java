package b.oop.b.chess.figure.black;

import b.oop.b.chess.Logic;
import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;
/**
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version $Id$
 * @since 22/06/2018
 * Черная фигура - Слон
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int deltaX = compare(source.x, dest.x), deltaY = compare(source.y, dest.y);
        Cell[] steps = new Cell[Math.abs(source.x - dest.x)];
        if (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y) && 0 <= Math.abs(source.x - dest.x) && Math.abs(source.x - dest.x) <= 7) {
            for (int i = 0; i < Math.abs(source.x - dest.x); i++) {
                steps[i] = Logic.findBy(dest.x + (deltaX * i), dest.y + (deltaY * i));
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    public int compare(int one, int two) {
        int result = 0;
        if (one - two < 0) {
            result = -1;
        } else if (one - two > 0) {
            result = 1;
        }
        return result;
    }
}
