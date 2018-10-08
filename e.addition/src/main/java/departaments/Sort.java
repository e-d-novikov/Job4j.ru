package departaments;
/**
 * @author Egor Novikov (e.novikov@yahoo.com)
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
                int o1Length = o1.length();
                int o2Length = o2.length();
                char[] o1Char =o1.toCharArray();
                char[] o2Char =o2.toCharArray();
                if (o1Length > o2Length) {
                    for (int i = 0; i < o2Length; i++) {
                        int comp = Character.toString(o2Char[i]).compareTo(Character.toString(o1Char[i]));
                        if (comp > 0) {
                            result = result + comp + o2Length - i;
                        } else if (comp < 0) {
                            result = result + comp - o2Length - i;
                        }
                    }
                } else if (o2Length > o1Length) {
                    for (int i = 0; i < o1Length; i++) {
                        int comp = Character.toString(o2Char[i]).compareTo(Character.toString(o1Char[i]));
                        if (comp > 0) {
                            result = result + comp + o1Length - i;
                        } else if (comp < 0) {
                            result = result + comp - o1Length - i;
                        }
                    }
                } else {
                    for (int i = 0; i < o1Length; i++) {
                        int comp = Character.toString(o2Char[i]).compareTo(Character.toString(o1Char[i]));
                        if (comp > 0) {
                            result = result + comp + o1Length - i;
                        } else if (comp < 0) {
                            result = result + comp - o1Length - i;
                        }
                    }
                }
                return result;
            }
        });
        return departaments.toArray(new String[0]);
    }
}






