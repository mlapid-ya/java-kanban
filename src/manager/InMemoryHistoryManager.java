package manager;

import model.Task;

import java.util.LinkedList;
import java.util.Queue;

public class InMemoryHistoryManager implements HistoryManager {

    /*
    * В задании говорится: "Если размер списка исчерпан,
    * из него нужно удалить самый старый элемент — тот,
    * который находится в начале списка."
    * Queue работает по принципу FIFO, так что мы удаляем самый первый (старый) элемент.
    * Исходя из ТЗ, Dequeue посчитал чрезмерным.
    * */
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
        return new LinkedList<>(taskHistory);
    }
}
