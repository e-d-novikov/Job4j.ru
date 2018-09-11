package b.oop.b.chess.figure.white;

import b.oop.b.chess.Logic;
import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;

/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version $Id$
 * @since 22/06/2018
 * Белая фигура - Ладья
 */
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int a = source.x - dest.x;
        int b = source.y - dest.y;
        Cell[] steps = new Cell[0];
        if ((0 <= dest.y) && (dest.y <= 7) && source.x == dest.x) {
            steps = new Cell[Math.abs(b)];
            if (b > 0) {
                //у+
                for (int i = 0; i < b; i++) {
                    steps[i] = Logic.findBy(dest.x, dest.y + i);
                }
            } else if (b < 0) {
                //y-
                for (int i = 0; i > b; i--) {
                    steps[Math.abs(i)] = Logic.findBy(dest.x, dest.y - Math.abs(i));
                }
            }
        } else if (source.y == dest.y && (0 <= dest.x) && (dest.x <= 7)) {
            steps = new Cell[Math.abs(a)];
            if (a > 0) {
                //x+
                for (int i = 0; i < a; i++) {
                    steps[i] = Logic.findBy(dest.x + i, source.y);
                }
            } else if (a < 0) {
                //x-
                for (int i = 0; i > a; i--) {
                    steps[Math.abs(i)] = Logic.findBy(dest.x - Math.abs(i), source.y);
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}