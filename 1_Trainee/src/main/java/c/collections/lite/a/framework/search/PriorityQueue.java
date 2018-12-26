package c.collections.lite.a.framework.search;

import java.util.LinkedList;
/**
 * Priority Queue.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class PriorityQueue {
    /**
     * Tasks.
     */
    private LinkedList<Task> tasks = new LinkedList<>();
    /**
     * The method inserts the task according to its priority.
     * With the same priority, the insertion occurs at the end of the group.
     * @param task - task.
     */
    public void put(Task task) {
        int temp = tasks.size();
        for (Task value : tasks) {
            if (value.getPriority() > task.getPriority()) {
                temp = tasks.indexOf(value);
                break;
            }
        }
        tasks.add(temp, task);
    }
    /**
     * Method return an array of tasks.
     * @return - array of tasks.
     */
    public String[] result() {
        String[] result = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            result[i] = tasks.get(i).getDesc();
        }
        return result;
    }
}