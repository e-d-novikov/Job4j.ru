package b.oop.b.chess.figure.white;

import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;

/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version $Id$
 * @since 22/06/2018
 * Белая фигура - Король
 */
public class KingWhite implements Figure {
    private final Cell position;

    public KingWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (source.y == dest.y + i && source.x == dest.x + j) {
                    steps = new Cell[] {dest};
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}