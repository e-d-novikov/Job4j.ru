package a.base.e.control;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;
    private static final int START = 1;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = false;
        if (this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, 2, 0, -1, 1)) {
            result = true;
        } else {
            for (int i = 0, j = 2; i < 3; i++, j--) {
                if (this.fillBy(Figure3T::hasMarkX, 0, i, 1, 0)
                        || this.fillBy(Figure3T::hasMarkX, j, 0, 0, 1)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        if (this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, 2, 0, -1, 1)) {
            result = true;
        } else {
            for (int i = 0, j = 2; i < 3; i++, j--) {
                if (this.fillBy(Figure3T::hasMarkO, 0, i, 1, 0)
                        || this.fillBy(Figure3T::hasMarkO, j, 0, 0, 1)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Figure3T a = table[i][j];
                if (a.hasMarkO() == a.hasMarkX()) {
                    result = true;
                    i = 3;
                    j = 3;
                }
            }
        }
        return result;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }
}