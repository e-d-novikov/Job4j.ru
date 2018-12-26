package b.oop.b.chess.figure.white;

import org.junit.Test;
import b.oop.b.chess.figure.Cell;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BishopWhiteTest {
    @Test
    public void xUpyUp() {
        BishopWhite turner = new BishopWhite(Cell.A1);
        Cell[] result = new Cell[]{Cell.E5, Cell.D4, Cell.C3, Cell.B2};
        assertThat(turner.way(Cell.A1, Cell.E5), is(result));
    }

    @Test
    public void xDownyDown() {
        BishopWhite turner = new BishopWhite(Cell.F1);
        Cell[] result = new Cell[]{Cell.A1, Cell.B2, Cell.C3, Cell.D4};
        assertThat(turner.way(Cell.E5, Cell.A1), is(result));
    }

    @Test
    public void xDownyUp() {
        BishopWhite turner = new BishopWhite(Cell.H5);
        Cell[] result = new Cell[]{Cell.E8, Cell.F7, Cell.G6};
        assertThat(turner.way(Cell.H5, Cell.E8), is(result));
    }

    @Test
    public void xUpyDown() {
        BishopWhite turner = new BishopWhite(Cell.F1);
        Cell[] result = new Cell[]{Cell.H5, Cell.G6, Cell.F7};
        assertThat(turner.way(Cell.E8, Cell.H5), is(result));
    }
}