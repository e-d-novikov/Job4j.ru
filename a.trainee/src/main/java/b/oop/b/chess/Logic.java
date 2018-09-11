package b.oop.b.chess;

import  b.oop.b.chess.exceptions.*;
import b.oop.b.chess.figure.*;
/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version $Id$
 * @since 22/06/2018
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        try {
            validateMove(source, dest);
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        } catch (FigureNotFoundException e) {
            System.out.println("Ячейка пуста");
        } catch (ImpossibleMoveException e) {
            System.out.println("Фигура не может так двигаться");
        } catch (OccupiedWayException e) {
            System.out.println("Фигура не может ходить через другие фигуры");
        }
        return rst;
    }

    public void validateMove(Cell source, Cell dest) {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Ячейка пуста");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (steps.length == 0) {
            throw new ImpossibleMoveException("Фигура не может так двигаться");
        }
        if (!validateWay(steps)) {
            throw new OccupiedWayException("Фигура не может ходить через другие фигуры");
        }
    }


    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                    rst = index;
                    break;
            }
        }
        return rst;
    }

    private boolean validateWay(Cell[] cell) {
        boolean result = true;
        for (int j = 0; j < this.figures.length; j++) {
            for (int i = 0; i < cell.length; i++) {
                if (cell[i] == this.figures[j].position()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static Cell findBy(int graphX, int graphY) {
        Cell rst = Cell.A1;
        int x = graphX;
        int y = graphY;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }
}