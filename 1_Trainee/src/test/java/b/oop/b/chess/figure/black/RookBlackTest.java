package b.oop.b.chess.figure.black;

import org.junit.Test;
import b.oop.b.chess.figure.Cell;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RookBlackTest {
    @Test
    public void xUp() {
        RookBlack turner = new RookBlack(Cell.A1);
        Cell[] result = new Cell[]{Cell.F1, Cell.E1, Cell.D1, Cell.C1, Cell.B1};
        assertThat(turner.way(Cell.A1, Cell.F1), is(result));
    }

    @Test
    public void xDown() {
        RookBlack turner = new RookBlack(Cell.F1);
        Cell[] result = new Cell[]{Cell.A1, Cell.B1, Cell.C1, Cell.D1, Cell.E1};
        assertThat(turner.way(Cell.F1, Cell.A1), is(result));
    }

    @Test
    public void yUp() {
        RookBlack turner = new RookBlack(Cell.F7);
        Cell[] result = new Cell[]{Cell.F1, Cell.F2, Cell.F3, Cell.F4, Cell.F5, Cell.F6};
        assertThat(turner.way(Cell.F7, Cell.F1), is(result));
    }

    @Test
    public void yDown() {
        RookBlack turner = new RookBlack(Cell.F1);
        Cell[] result = new Cell[]{Cell.F7, Cell.F6, Cell.F5, Cell.F4, Cell.F3, Cell.F2};
        assertThat(turner.way(Cell.F1, Cell.F7), is(result));
    }
}