package manager;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Queue;

class InMemoryHistoryManagerTest {

    private final Managers manager = new Managers();
    private InMemoryTaskManager taskManager;
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = (InMemoryTaskManager) manager.getDefault();
        historyManager = (InMemoryHistoryManager) Managers.getDefaultHistory();
    }

    @Test
    public void addTaskToHistory() {
        Task task = new Task("Задание 1", "Тест");
        taskManager.createTask(task);

        historyManager.add(task);

        final Queue<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    public void shouldPreserveTaskDataWhenAddedToHistoryManager() {
        Task task = new Task("Задание 1", "Тест");
        taskManager.createTask(task);

        Integer taskId = task.getId();
        Status taskStatus = task.getStatus();
        String taskDescription = task.getDescription();
        String taskName = task.getName();

        historyManager.add(task);
        final Queue<Task> history = historyManager.getHistory();

        Task savedTask = history.remove();

        assertEquals(taskId, savedTask.getId());
        assertEquals(taskStatus, savedTask.getStatus());
        assertEquals(taskDescription, savedTask.getDescription());
        assertEquals(taskName, savedTask.getName());
    }

}