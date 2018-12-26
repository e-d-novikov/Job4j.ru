package b.oop.b.chess;

import org.junit.Before;
import org.junit.Test;
import b.oop.b.chess.figure.Cell;
import b.oop.b.chess.figure.black.*;
import b.oop.b.chess.figure.white.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    private final Logic test = new Logic();

    @Before
    public void inputdata() {
        test.add(new PawnWhite(Cell.A2));
        test.add(new PawnWhite(Cell.B2));
        test.add(new PawnWhite(Cell.C2));
        test.add(new PawnWhite(Cell.D2));
        test.add(new PawnWhite(Cell.E2));
        test.add(new PawnWhite(Cell.F2));
        test.add(new PawnWhite(Cell.G2));
        test.add(new PawnWhite(Cell.H2));
        test.add(new RookWhite(Cell.A1));
        test.add(new KnightWhite(Cell.B1));
        test.add(new BishopWhite(Cell.C1));
        test.add(new QueenWhite(Cell.D1));
        test.add(new KingWhite(Cell.E1));
        test.add(new BishopWhite(Cell.F1));
        test.add(new KnightWhite(Cell.G1));
        test.add(new RookWhite(Cell.H1));

        test.add(new PawnBlack(Cell.A7));
        test.add(new PawnBlack(Cell.B7));
        test.add(new PawnBlack(Cell.C7));
        test.add(new PawnBlack(Cell.D7));
        test.add(new PawnBlack(Cell.E7));
        test.add(new PawnBlack(Cell.F7));
        test.add(new PawnBlack(Cell.G7));
        test.add(new PawnBlack(Cell.H7));
        test.add(new RookBlack(Cell.A8));
        test.add(new KnightBlack(Cell.B8));
        test.add(new BishopBlack(Cell.C8));
        test.add(new QueenBlack(Cell.D8));
        test.add(new KingBlack(Cell.E8));
        test.add(new BishopBlack(Cell.F8));
        test.add(new KnightBlack(Cell.G8));
        test.add(new RookBlack(Cell.H8));
    }

    @Test
    public void whenThereIsNoFigure() {
        assertThat(test.move(Cell.A3, Cell.A5), is(false));
    }

    //White Rook

    @Test
    public void whenWhiteRookWalks() {
        test.move(Cell.A2, Cell.A4);
        test.move(Cell.A4, Cell.A5);
        test.move(Cell.A5, Cell.A6);
        test.move(Cell.A1, Cell.A5); //вперед
        test.move(Cell.A5, Cell.A3); //назад
        test.move(Cell.A3, Cell.F3); //вправо
        assertThat(test.move(Cell.F3, Cell.A3), is(true));
    }

    @Test
    public void whenWhiteRookWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.A1, Cell.A5), is(false));
    }

    @Test
    public void whenWhiteRookDoesNotGoRight() {
        test.move(Cell.A2, Cell.A4);
        test.move(Cell.A4, Cell.A5);
        test.move(Cell.A5, Cell.A6);
        test.move(Cell.A1, Cell.A5);
        assertThat(test.move(Cell.A5, Cell.C3), is(false));
    }

    //Black Rook

    @Test
    public void whenBlackRookWalksStraightAhead() {
        test.move(Cell.A7, Cell.A5);
        test.move(Cell.A5, Cell.A4);
        test.move(Cell.A4, Cell.A3);
        assertThat(test.move(Cell.A8, Cell.A4), is(true));
    }

    @Test
    public void whenBlackRookWalks() {
        test.move(Cell.A7, Cell.A5);
        test.move(Cell.A5, Cell.A4);
        test.move(Cell.A4, Cell.A3);
        test.move(Cell.A8, Cell.A4);
        test.move(Cell.A4, Cell.A6);
        test.move(Cell.A6, Cell.F6);
        assertThat(test.move(Cell.F6, Cell.A6), is(true));
    }

    @Test
    public void whenBlackRookWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.A8, Cell.A5), is(false));
    }

    @Test
    public void whenBlackRookDoesNotGoRight() {
        test.move(Cell.A7, Cell.A5);
        test.move(Cell.A5, Cell.A4);
        test.move(Cell.A4, Cell.A3);
        test.move(Cell.A8, Cell.A4);
        assertThat(test.move(Cell.A4, Cell.C3), is(false));
    }

    //Knight White

    @Test
    public void whenWhiteKnightWalks() {
        test.move(Cell.B8, Cell.C6);
        test.move(Cell.C6, Cell.B4);
        test.move(Cell.B4, Cell.C6);
        assertThat(test.move(Cell.C6, Cell.B8), is(true));
    }

    @Test
    public void whenWhiteKnightDoesNotGoRight() {
        assertThat(test.move(Cell.B8, Cell.B6), is(false));
    }

    //Knight Black

    @Test
    public void whenBlackKnightWalks() {
        test.move(Cell.B1, Cell.C3);
        test.move(Cell.C3, Cell.B5);
        test.move(Cell.B5, Cell.C3);
        assertThat(test.move(Cell.C3, Cell.B1), is(true));
    }

    @Test
    public void whenBlackKnightDoesNotGoRight() {
        assertThat(test.move(Cell.B1, Cell.B3), is(false));
    }

    //Bishop White

    @Test
    public void whenWhiteBishopWalks() {
        test.move(Cell.D7, Cell.D5);
        test.move(Cell.C8, Cell.F5);
        test.move(Cell.F5, Cell.D3);
        test.move(Cell.D3, Cell.B5);
        assertThat(test.move(Cell.B5, Cell.D7), is(true));
    }

    @Test
    public void whenWhiteBishopWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.C8, Cell.F5), is(false));
    }

    @Test
    public void whenWhiteBishopDoesNotGoRight() {
        test.move(Cell.D7, Cell.D5);
        test.move(Cell.C8, Cell.F5);
        assertThat(test.move(Cell.F5, Cell.F6), is(false));
    }

    //Bishop Black

    @Test
    public void whenBlackBishopWalks() {
        test.move(Cell.D2, Cell.D4);
        test.move(Cell.C1, Cell.F4);
        test.move(Cell.F4, Cell.D6);
        test.move(Cell.D6, Cell.B4);
        assertThat(test.move(Cell.B4, Cell.D2), is(true));
    }

    @Test
    public void whenBlackBishopWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.C1, Cell.F4), is(false));
    }

    @Test
    public void whenBlackBishopDoesNotGoRight() {
        test.move(Cell.D2, Cell.D4);
        test.move(Cell.C1, Cell.F4);
        assertThat(test.move(Cell.F4, Cell.F6), is(false));
    }

    //Queen White

    @Test
    public void whenWhiteQueenWalks() {
        test.move(Cell.D7, Cell.D5);
        test.move(Cell.D8, Cell.D6);
        test.move(Cell.D6, Cell.A6);
        test.move(Cell.A6, Cell.A3);
        test.move(Cell.A3, Cell.H3);
        test.move(Cell.H3, Cell.H6);
        test.move(Cell.H6, Cell.E3);
        test.move(Cell.E3, Cell.B6);
        test.move(Cell.B6, Cell.A5);
        test.move(Cell.A5, Cell.C3);
        assertThat(test.move(Cell.C3, Cell.F6), is(true));
    }

    @Test
    public void whenWhiteQueenWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.D8, Cell.D6), is(false));
    }

    @Test
    public void whenWhiteQueenDoesNotGoRight() {
        test.move(Cell.D7, Cell.D5);
        test.move(Cell.D8, Cell.D6);
        assertThat(test.move(Cell.D6, Cell.F5), is(false));
    }

    //Queen Black

    @Test
    public void whenBlackQueenWalks() {
        test.move(Cell.D2, Cell.D4);
        test.move(Cell.D1, Cell.D3);
        test.move(Cell.D3, Cell.H3);
        test.move(Cell.H3, Cell.H6);
        test.move(Cell.H6, Cell.A6);
        test.move(Cell.A6, Cell.A3);
        test.move(Cell.A3, Cell.D6);
        test.move(Cell.D6, Cell.G3);
        test.move(Cell.G3, Cell.H4);
        test.move(Cell.H4, Cell.F6);
        assertThat(test.move(Cell.F6, Cell.E5), is(true));
    }

    @Test
    public void whenBlackQueenWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.D1, Cell.D3), is(false));
    }

    @Test
    public void whenBlackQueenDoesNotGoRight() {
        test.move(Cell.D2, Cell.D4);
        test.move(Cell.D1, Cell.D3);
        assertThat(test.move(Cell.D3, Cell.F4), is(false));
    }

    //King White

    @Test
    public void whenWhiteKingWalks() {
        test.move(Cell.E7, Cell.E5);
        test.move(Cell.E8, Cell.E7);
        test.move(Cell.E7, Cell.E6);
        test.move(Cell.E6, Cell.D5);
        test.move(Cell.D5, Cell.E4);
        test.move(Cell.E4, Cell.F5);
        test.move(Cell.F5, Cell.E6);
        test.move(Cell.E6, Cell.F6);
        test.move(Cell.F6, Cell.E6);
        assertThat(test.move(Cell.E6, Cell.E7), is(true));
    }

    @Test
    public void whenWhiteKingWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.E8, Cell.E7), is(false));
    }

    @Test
    public void whenWhiteKingDoesNotGoRight() {
        test.move(Cell.E7, Cell.E5);
        test.move(Cell.E8, Cell.E7);
        assertThat(test.move(Cell.E7, Cell.C5), is(false));
    }

    //King Black

    @Test
    public void whenBlackKingWalks() {
        test.move(Cell.E2, Cell.E4);
        test.move(Cell.E1, Cell.E2);
        test.move(Cell.E2, Cell.E3);
        test.move(Cell.E3, Cell.F4);
        test.move(Cell.F4, Cell.E5);
        test.move(Cell.E5, Cell.D4);
        test.move(Cell.D4, Cell.E3);
        test.move(Cell.E3, Cell.D3);
        test.move(Cell.D3, Cell.E3);
        assertThat(test.move(Cell.E3, Cell.E2), is(true));
    }

    @Test
    public void whenBlackKingWalksStraightForwardThroughTheFigure() {
        assertThat(test.move(Cell.E1, Cell.E2), is(false));
    }

    @Test
    public void whenBlackKingDoesNotGoRight() {
        test.move(Cell.E2, Cell.E4);
        test.move(Cell.E1, Cell.E2);
        assertThat(test.move(Cell.E2, Cell.G4), is(false));
    }

    //Pawn White

    @Test
    public void whenWhitePawnWalksFirstMove() {
        test.move(Cell.B7, Cell.B5);
        assertThat(test.move(Cell.B5, Cell.B4), is(true));
    }

    @Test
    public void whenWhitePawnWalksSecondMove() {
        test.move(Cell.B7, Cell.B5);
        assertThat(test.move(Cell.B5, Cell.B3), is(false));
    }

    @Test
    public void whenWhitePawnWalksStraightForwardThroughTheFigure() {
        test.move(Cell.B2, Cell.B4);
        test.move(Cell.B4, Cell.B5);
        test.move(Cell.B5, Cell.B6);
        assertThat(test.move(Cell.B7, Cell.B5), is(false));
    }

    @Test
    public void whenWhitePawnDoesNotGoRight() {
        assertThat(test.move(Cell.B7, Cell.C6), is(false));
    }

    //Pawn Black

    @Test
    public void whenBlackPawnWalksFirstMove() {
        test.move(Cell.D2, Cell.D4);
        assertThat(test.move(Cell.D4, Cell.D5), is(true));
    }

    @Test
    public void whenBlackPawnWalksSecondMove() {
        test.move(Cell.D2, Cell.D4);
        assertThat(test.move(Cell.D4, Cell.D6), is(false));
    }

    @Test
    public void whenBlackPawnWalksStraightForwardThroughTheFigure() {
        test.move(Cell.D7, Cell.D5);
        test.move(Cell.D5, Cell.D4);
        test.move(Cell.D4, Cell.D3);
        assertThat(test.move(Cell.D2, Cell.D4), is(false));
    }

    @Test
    public void whenBlackPawnDoesNotGoRight() {
        assertThat(test.move(Cell.D2, Cell.B3), is(false));
    }

}