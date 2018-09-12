package departaments;
/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 * Сортировка департаментов.
 */
import java.util.*;

public class Sort {

    private ArrayList<Dep> addDep(String[] units) {
        ArrayList<Dep> departaments = new ArrayList<>();
        for (String unit : units) {
            Dep dep = new Dep();
            for (String str : unit.split("\\\\")) {
                dep.add(str);
            }
            departaments.add(dep);
        }
        return departaments;
    }
    /**
     * Метод sor принимает массив департаментов,
     * и возвращает новый массив с добавленными
     * директориями верхнего уровня, отсортированный
     * в лексографическом порядке.
     */
    public String[] sortInOrder(String[] units) {
        ArrayList<Dep> departaments = addDep(units);
        TreeSet<String> sorted = new TreeSet<>();
        for (Dep res : departaments) {
           for (String str : res.getDep()) {
               sorted.add(str);
           }
        }
        String[] result = sorted.toArray(new String[0]);
        return result;
    }
    /**
     * Метод sortInDicreasingOrder принимает массив департаментов,
     * и возвращает новый массив с добавленными
     * директориями верхнего уровня, отсортированный
     * в лексографическом порядке по убыванию.
     */
    public String[] sortInDicreasingOrder(String[] units) {
        ArrayList<Dep> departaments = addDep(units);
        departaments.sort(new Comparator<Dep>() {
            @Override
            public int compare(Dep o1, Dep o2) {
                int result = 0;
                if (o1.size() < o2.size()) {
                    for (int i = 0; i < o1.size(); i++) {
                        result = result + o2.getDep().get(i).compareTo(o1.getDep().get(i));
                    }
                } else if (o1.size() > o2.size()) {
                    for (int i = 0; i < o2.size(); i++) {
                        result = result + o2.getDep().get(i).compareTo(o1.getDep().get(i));
                    }
                } else {
                    for (int i = 0; i < o1.size(); i++) {
                        result = result + o2.getDep().get(i).compareTo(o1.getDep().get(i));
                    }
                }
                return result;
            }
        });
        ArrayList<String> sorted = new ArrayList<>();
        for (Dep res : departaments) {
            for (String str : res.getDep()) {
                if (sorted.contains(str)) {
                    continue;
                } else {
                    sorted.add(str);
                }
            }
        }
        String[] result = sorted.toArray(new String[0]);
        return result;
    }
}





