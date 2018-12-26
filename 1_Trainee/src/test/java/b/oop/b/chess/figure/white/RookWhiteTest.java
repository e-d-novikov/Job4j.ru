package b.oop.b.chess.figure.white;

import org.junit.Test;
import b.oop.b.chess.figure.Cell;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RookWhiteTest {
    @Test
    public void xUp() {
        RookWhite turner = new RookWhite(Cell.A1);
        Cell[] result = new Cell[]{Cell.F1, Cell.E1, Cell.D1, Cell.C1, Cell.B1};
        assertThat(turner.way(Cell.A1, Cell.F1), is(result));
    }

    @Test
    public void xDown() {
        RookWhite turner = new RookWhite(Cell.F1);
        Cell[] result = new Cell[]{Cell.A1, Cell.B1, Cell.C1, Cell.D1, Cell.E1};
        assertThat(turner.way(Cell.F1, Cell.A1), is(result));
    }

    @Test
    public void yUp() {
        RookWhite turner = new RookWhite(Cell.F7);
        Cell[] result = new Cell[]{Cell.F1, Cell.F2, Cell.F3, Cell.F4, Cell.F5, Cell.F6};
        assertThat(turner.way(Cell.F7, Cell.F1), is(result));
    }

    @Test
    public void yDown() {
        RookWhite turner = new RookWhite(Cell.F1);
        Cell[] result = new Cell[]{Cell.F7, Cell.F6, Cell.F5, Cell.F4, Cell.F3, Cell.F2};
        assertThat(turner.way(Cell.F1, Cell.F7), is(result));
    }
}