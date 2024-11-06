package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {

    private int taskCounter = 0;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager historyManager;

    InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public int getTaskCounter() {
        return taskCounter;
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }

        System.out.println("Список всех задач:");
        for (Task task : tasks.values()) {
            System.out.printf("  - %s\n", task);
        }
    }

    @Override
    public void printAllEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список эпиков пуст.");
            return;
        }

        System.out.println("Список всех эпиков:");
        for (Epic epic : epics.values()) {
            System.out.printf("  - %s\n", epic);
        }
    }

    @Override
    public void printAllSubtasks() {
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач пуст.");
            return;
        }

        System.out.println("Список всех подзадач:");
        for (Subtask subTask : subtasks.values()) {
            System.out.printf("  - %s\n", subTask);
        }
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
        System.out.println("Все задачи удалены.");
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeAllSubtasks() {

        Map<Integer, Subtask> subtasksCopy = new HashMap<Integer, Subtask>(this.subtasks);

        for (int id : subtasksCopy.keySet()) {
            removeSubtaskById(id);
        }
    }

    @Override
    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            historyManager.add(task);
            return task;
        } else {
            System.out.printf("Задания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    @Override
    public Epic getEpicById(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            historyManager.add(epic);
            return epic;
        } else {
            System.out.printf("Эпика с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    @Override
    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            historyManager.add(subtask);
            return subtask;
        } else {
            System.out.printf("Подзадания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    @Override
    public void createTask(Task task) {
        task.setId(++taskCounter);

        tasks.put(task.getId(), task);
        System.out.printf("Создана задача %s.\n", task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(++taskCounter);

        epics.put(epic.getId(), epic);
        System.out.printf("Создан эпик %s.\n", epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(++taskCounter);

        int subtaskEpicId = subtask.getEpicId();
        Epic subtaskEpic = epics.get(subtaskEpicId);
        subtaskEpic.getSubtasks().add(subtask);

        subtasks.put(subtask.getId(), subtask);
        System.out.printf("Создана подзадача %s.\n", subtask);
    }

    @Override
    public void updateTask(Task newTask) {
        int taskId = newTask.getId();

        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, newTask);
        } else {
            System.out.printf("Задание с идентификатором %d отсутствует.\n", taskId);
        }
    }

    @Override
    public void updateEpic(Epic newEpic) {
        int epicId = newEpic.getId();

        if (epics.containsKey(epicId)) {
            epics.put(epicId, newEpic);
            updateEpicStatusById(epicId);
        } else {
            System.out.printf("Эпик с идентификатором %d отсутствует.\n", epicId);
        }
    }

    @Override
    public void updateSubtask(Subtask newSubtask) {
        int subtaskId = newSubtask.getId();

        if (subtasks.containsKey(subtaskId)) {
            subtasks.put(subtaskId, newSubtask);
            int epicId = newSubtask.getEpicId();
            updateEpicStatusById(epicId);
        } else {
            System.out.printf("Подзадача с идентификатором %d отсутствует.\n", subtaskId);
        }
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
        System.out.printf("Удалена задача %d.\n", id);
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return;
        }

        ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

        if (!epicSubtasks.isEmpty()) {
            for (Subtask subtask : epicSubtasks) {
                removeSubtaskById(subtask.getId());
            }
        }
        epics.remove(id);
        System.out.printf("Удалён эпик %d.\n", id);
    }

    @Override
    public void removeSubtaskById(int id) {
        Subtask subtask = getSubtaskById(id);

        if (subtask == null) {
            return;
        }

        int subtaskEpicId = subtask.getEpicId();
        Epic subtaskEpic = epics.get(subtaskEpicId);
        subtaskEpic.getSubtasks().remove(subtask);

        subtasks.remove(id);
        System.out.printf("Удалёна подзадача %s.\n", subtask);

        updateEpicStatusById(subtaskEpicId);
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasksById(int id) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return null;
        }

        return epic.getSubtasks();
    }

    @Override
    public void printEpicSubtasksById(int id) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return;
        }

        ArrayList<Subtask> epicSubtasks = getEpicSubtasksById(id);

        if (epicSubtasks.isEmpty()) {
            System.out.printf("Список подзадач эпика %s пуст.\n", epic);
            return;
        }

        System.out.printf("Список всех подзадач эпика %d:\n", epic.getId());
        for (Subtask subTask : epicSubtasks) {
            System.out.printf("  - %s\n", subTask);
        }
    }

    @Override
    public void updateTaskStatusById(int id, Status newStatus) {
        Task task = getTaskById(id);

        if (task == null) {
            return;
        }

        Status currentStatus = task.getStatus();

        task.setStatus(newStatus);
        System.out.printf(
                "Статус задачи %d поменялся с %s на %s.\n",
                task.getId(), currentStatus, newStatus
        );
    }

    @Override
    public void updateSubtaskStatusById(int id, Status newStatus) {
        Subtask subtask = getSubtaskById(id);

        if (subtask == null) {
            return;
        }

        Status currentStatus = subtask.getStatus();

        subtask.setStatus(newStatus);

        System.out.printf(
                "Статус подзадачи %d поменялся с %s на %s.\n",
                subtask.getId(), currentStatus, newStatus
        );

        int epicId = subtask.getEpicId();
        updateEpicStatusById(epicId);
    }

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    private void updateEpicStatusById(int id) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return;
        }

        Status currentStatus = epic.getStatus();
        Status newStatus;
        ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

        if (epicSubtasks.isEmpty() || epic.countSubtasksByStatus(Status.NEW) == epicSubtasks.size()) {
            newStatus = Status.NEW;
        } else if (epic.countSubtasksByStatus(Status.DONE) == epicSubtasks.size()) {
            newStatus = Status.DONE;
        } else {
            newStatus = Status.IN_PROGRESS;
        }

        if (currentStatus != newStatus) {
            epic.setStatus(newStatus);
            System.out.printf("Статус эпика %d поменялся с %s на %s.\n", id, currentStatus, newStatus);
        }
    }
}