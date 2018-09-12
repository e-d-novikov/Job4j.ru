package departaments;

import java.util.ArrayList;
import java.util.Comparator;

public class Dep {

    private ArrayList<String> dep = new ArrayList<>();

    public void add(String departament) {
        if (dep.size() == 0) {
            dep.add(departament);
        } else {
            dep.add(dep.get(dep.size() - 1) + "\\" + departament);
        }
    }

    public ArrayList<String> getDep() {
        return this.dep;
    }

    public int size() {
        return dep.size();
    }
}
