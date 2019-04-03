package soft;
/**
 * Тестовое задание Zaycev.net
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 *
 * Упростить данный алгоритм можно включив в поиск наибольшего значения
 * следующего наибольшего, и таким образом можно сразу после обнуления
 * первого наибольшего начать распределение
 * количество операций сравнения останется таким же,
 *
 * 1 итерация цикла с начала массива
 * - Поиск 1 и 2 максимального значения
 * Последующие итерации так же с начала массива
 * -
 *
 * выполнять проверку массивов на сходство в конце каждого цикла
 *
 * предусмотреть вариант когда распределение максимального значения
 * коснется следующего максимального значения
 *
 * цикл миимум один раз проходит через все ячейки
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    /**
     * Длина массива
     */
    private int length;
    /**
     * Контейнер массивов
     */
    private ArrayList<int[]> arrays;
    /**
     * Количество шагов
     */
    private int steps;
    /**
     * Длина цикла
     */
    private int cycleLength;

    /**
     * Конструктор
     * @param array - массив
     */
    public Task(int[] array) {
        length = array.length;
        arrays = new ArrayList<>();
        cycleLength = -1;
        result(array);
    }

    /**
     * Метод возвращает количество шагов.
     * @return - количество шагов.
     */
    public int getSteps() {
        return this.steps;
    }

    /**
     * Метод возвращает длину цикла.
     * @return - длина цикла.
     */
    public int getCycleLength() {
        return this.cycleLength;
    }

    /**
     * Метод выполняет рассчет длины цикла и количество шагов.
     * @param array - массив
     */
    private void result(int[] array) {
        int[] result = Arrays.copyOf(array, length);
        do {
            steps++;
            arrays.add(result);
            int maxIndex = findMaxValue(result);
            result = distributionMaxValue(result, maxIndex);
        } while (comparison(result) == -1);
    }

    /**
     * Метод находит максимальное значение в массиве
     */
    public int findMaxValue(int[] array) {
        int value = array[0];
        int maxIndex = 0;
        for (int i = 0; i < length; i++) {
            if (value < array[i]) {
                value = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Метод распределяет максимальное значение по массиву
     */
    public int[] distributionMaxValue(int[] array, int maxIndex) {
        int value = array[maxIndex];
        int[] result = Arrays.copyOf(array, length);
        result[maxIndex] = 0;
        int index = maxIndex + 1;
        if (maxIndex == length - 1) {
            index = 0;
        }
        for (int i = 0; i < value; i++) {
            if (index == length - 1) {
                result[index] = result[index] + 1;
                index = 0;
            } else {
                result[index] = result[index] + 1;
                index++;
            }
        }
        return result;
    }

    /**
     * Метод сравнивает текущий входящий массив с предыдущими.
     * @param array - массив;
     * @return - длину цикла в случае если такой массив уже имеется, иначе -1.
     */
    private int comparison(int[] array) {
        int result = -1;
        for (int i = 0; i < arrays.size(); i++) {
            if (Arrays.equals(arrays.get(i), array)) {
                result = arrays.size() - i;
                cycleLength = result;
                break;
            }
        }
        return result;
    }
}
