package b.oop.b.chess.figure.black;

import b.oop.b.chess.Logic;
import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;

/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version $Id$
 * @since 22/06/2018
 * Черная фигура - Королева
 */
public class QueenBlack implements Figure {
    private final Cell position;

    public QueenBlack(final Cell position) {
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
        Cell[] result = new Cell[0];
        if (a == 0) {
            //x не изменяется
            result = new Cell[Math.abs(b)];
            if (b > 0) {
                //у+
                for (int i = 0; i < b; i++) {
                    result[i] = Logic.findBy(dest.x, dest.y + i);
                }
            } else if (b < 0) {
                //y-
                for (int i = 0; i < Math.abs(b); i++) {
                    result[i] = Logic.findBy(dest.x, dest.y - i);
                }
            }
        } else if (b == 0) {
            //y не изменяется
            result = new Cell[Math.abs(a)];
            if (a > 0) {
                //x+
                for (int i = 0; i < a; i++) {
                    result[i] = Logic.findBy(dest.x + i, dest.y);
                }
            } else if (a < 0) {
                //x-
                for (int i = 0; i < Math.abs(a); i++) {
                    result[i] = Logic.findBy(dest.x - i, dest.y);
                }
            }
        } else if (Math.abs(a) == Math.abs(b)) {
            //диагонали
            result = new Cell[Math.abs(a)];
            if (a > 0 && b > 0) {
                //x+ y+
                for (int i = 0; i < a; i++) {
                    result[i] = Logic.findBy(dest.x + i, dest.y + i);
                }
            } else if (a < 0 && b < 0) {
                //x- y-
                for (int i = 0; i < Math.abs(a); i++) {
                    result[i] = Logic.findBy(dest.x - i, dest.y - i);
                }
            } else if (a > 0 && b < 0) {
                //x+ y-
                for (int i = 0; i < a; i++) {
                    result[i] = Logic.findBy(dest.x + i, dest.y - i);
                }
            } else if (a < 0 && b > 0) {
                //x- y+
                for (int i = 0; i < b; i++) {
                    result[i] = Logic.findBy(dest.x - i, dest.y + i);
                }
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}