package manager;

import model.Task;

import java.util.LinkedList;
import java.util.Queue;

public class InMemoryHistoryManager implements HistoryManager {

    private final Queue<Task> taskHistory = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (taskHistory.size() == 10) {
            taskHistory.remove();
        }

        taskHistory.add(task);
    }

    @Override
    public Queue<Task> getHistory() {
        return taskHistory;
    }
}
