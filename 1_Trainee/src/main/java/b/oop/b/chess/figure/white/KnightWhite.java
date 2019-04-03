package b.oop.b.chess.figure.white;

import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;

/**
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version $Id$
 * @since 22/06/2018
 *  Белая фигура - Конь
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        for (int i = 2; i >= -2; i = i - 4) {
            for (int j = 1; j >= -1; j = j - 2) {
                if (source.y == dest.y + i && source.x == dest.x + j) {
                    steps = new Cell[] {dest};
                } else if (source.y == dest.y + j && source.x == dest.x + i) {
                    steps = new Cell[] {dest};
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}