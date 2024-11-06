package manager;

import model.Task;

import java.util.Queue;

public interface HistoryManager {

    public void add(Task task);

    public Queue<Task> getHistory();
}
