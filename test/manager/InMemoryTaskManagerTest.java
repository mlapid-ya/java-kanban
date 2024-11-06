package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InMemoryTaskManagerTest {

    private final Managers manager = new Managers();
    private TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = manager.getDefault();
    }

    @Test
    public void createNewTask() {
        Task task = new Task("Задание 1", "Тест");
        taskManager.createTask(task);

        final int taskId = task.getId();
        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    public void createNewEpic() {
        Epic epic = new Epic("Эпик 1", "Тест");
        taskManager.createEpic(epic);

        final int epicId = epic.getId();
        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic, savedEpic, "Эпики не совпадают.");

        final ArrayList<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество эпиков.");
        assertEquals(epic, epics.getFirst(), "Эпики не совпадают.");
    }

    @Test
    public void createNewSubtask() {
        Epic epic = new Epic("Эпик 1", "Тест");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Тест", epic.getId());
        taskManager.createSubtask(subtask);

        final int subtaskId = subtask.getId();
        final Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Подзадача не найдена.");
        assertEquals(subtask, savedSubtask, "Подзадачи не совпадают.");

        final ArrayList<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Подзадачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество подзадач.");
        assertEquals(subtask, subtasks.getFirst(), "Подзадачи не совпадают.");
    }

    @Test
    public void getTaskById() {
        Task task = new Task("Задание 1", "Тест");
        taskManager.createTask(task);

        int taskId = task.getId();
        Task savedTask = taskManager.getTaskById(taskId);
        assertEquals(task, savedTask);
    }

    @Test
    public void getEpicById() {
        Epic epic = new Epic("Эпик 1", "Тест");
        taskManager.createEpic(epic);

        int epicId = epic.getId();
        Epic savedEpic = taskManager.getEpicById(epicId);
        assertEquals(epic, savedEpic);
    }

    @Test
    public void getSubtaskById() {
        Epic epic = new Epic("Эпик 1", "Тест");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Тест", epic.getId());
        taskManager.createSubtask(subtask);

        int subtaskId = subtask.getId();
        Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);
        assertEquals(subtask, savedSubtask);
    }

    @Test
    public void shouldNotModifyTaskFieldsWhenAddedToManager() {
        Task task = new Task("Задание 1", "Тест");

        Status taskStatus = task.getStatus();
        String taskDescription = task.getDescription();
        String taskName = task.getName();

        taskManager.createTask(task);

        assertEquals(taskStatus, task.getStatus());
        assertEquals(taskDescription, task.getDescription());
        assertEquals(taskName, task.getName());
    }
}
