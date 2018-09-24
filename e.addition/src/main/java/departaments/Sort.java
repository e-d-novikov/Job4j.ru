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
                if (o1.length() > o2.length()) {
                    for (int i = 0; i < o2.split("\\\\").length; i++) {
                        int comp = o2.split("\\\\")[i].compareTo(o1.split("\\\\")[i]);
                        int length = o2.split("\\\\")[i].length();
                        if (comp > 0) {
                            result = result + comp + length - o1Length;
                        } else if (comp < 0) {
                            result = result + comp - length - o1Length;
                        }
                    }
                } else if (o2.length() > o1.length()) {
                    for (int i = 0; i < o1.split("\\\\").length; i++) {
                        int comp = o2.split("\\\\")[i].compareTo(o1.split("\\\\")[i]);
                        int length = o1.split("\\\\")[i].length();
                        if (comp > 0) {
                            result = result + comp + length - o2Length;
                        } else if (comp < 0) {
                            result = result + comp - length - o2Length;
                        }
                    }
                } else {
                    for (int i = 0; i < o1.split("\\\\").length; i++) {
                        int comp = o2.split("\\\\")[i].compareTo(o1.split("\\\\")[i]);
                        int length = o1.split("\\\\")[i].length();
                        if (comp > 0) {
                            result = result + comp + length - o1Length;
                        } else if (comp < 0) {
                            result = result + comp - length - o1Length;
                        }
                    }
                }
                return result;
            }
        });
        String[] result = departaments.toArray(new String[0]);
        return result;
    }
}





