package b.oop.b.chess.figure.black;

import b.oop.b.chess.Logic;
import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.Figure;

/**
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version $Id$
 * @since 22/06/2018
 * Черная фигура - Пешка
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest};
        } else if (source.y == dest.y + 2 && source.x == dest.x && source.y == 6) {
            steps = new Cell[2];
            steps[0] = Logic.findBy(dest.x, dest.y);
            steps[1] = Logic.findBy(dest.x, dest.y + 1);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}