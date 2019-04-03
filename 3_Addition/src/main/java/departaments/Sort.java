package departaments;
/**
 * @author Egor Novikov (enovikovdev@gmail.com)
 * Сортировка департаментов.
 */
import java.util.*;

public class Sort {

    private TreeSet<String> add(String[] units) {
        TreeSet<String> newUnits = new TreeSet<>();
        for (String unit : units) {
            String[] oneUnit = unit.split("\\\\");
            String[] twoUnit = new String[oneUnit.length];
            for (int i = 0; i < oneUnit.length; i++) {
                if (i == 0) {
                    twoUnit[i] = oneUnit[i];
                    newUnits.add(twoUnit[i]);
                } else {
                    twoUnit[i] = twoUnit[i - 1] + "\\" + oneUnit[i];
                    newUnits.add(twoUnit[i]);
                }
            }
        }
        return newUnits;
    }
    /**
     * Метод sor принимает массив департаментов,
     * и возвращает новый массив с добавленными
     * директориями верхнего уровня, отсортированный
     * в лексографическом порядке.
     */
    public String[] sortInOrder(String[] deps) {
        return add(deps).toArray(new String[0]);
    }
    /**
     * Метод sortInDicreasingOrder принимает массив департаментов,
     * и возвращает новый массив с добавленными
     * директориями верхнего уровня, отсортированный
     * в лексографическом порядке по убыванию.
     */
    public String[] sortInDicreasingOrder(String[] units) {
        ArrayList<String> departaments = new ArrayList<>(add(units));
        departaments.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
                    result = Character.compare(o2.charAt(i), o1.charAt(i));
                    if (result != 0) {
                        break;
                    }
                }
                return result != 0 ? result : Integer.compare(o1.length(), o2.length());
            }
        });
        return departaments.toArray(new String[0]);
    }
}






