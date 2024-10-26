package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int taskCounter = 0;

    public final HashMap<Integer, Task> tasks = new HashMap<>();
    public final HashMap<Integer, Epic> epics = new HashMap<>();
    public final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int getTaskCounter() {
        return taskCounter;
    }

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

    public void removeAllTasks() {
        tasks.clear();
        System.out.println("Все задачи удалены.");
    }

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeAllSubtasks() {

        HashMap<Integer, Subtask> subtasksCopy = new HashMap<Integer, Subtask>(this.subtasks);

        for (int id : subtasksCopy.keySet()) {
            removeSubtaskById(id);
        }
    }

    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else {
            System.out.printf("Задания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public Epic getEpicById(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        } else {
            System.out.printf("Эпика с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        } else {
            System.out.printf("Подзадания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public void createTask(Task task) {
        task.setId(taskCounter + 1);
        taskCounter++;

        tasks.put(task.getId(), task);
        System.out.printf("Создана задача %s.\n", task);
    }

    public void createEpic(Epic epic) {
        epic.setId(taskCounter + 1);
        taskCounter++;

        epics.put(epic.getId(), epic);
        System.out.printf("Создан эпик %s.\n", epic);
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(taskCounter + 1);
        taskCounter++;

        int subtaskEpicId = subtask.getEpicId();
        Epic subtaskEpic = epics.get(subtaskEpicId);
        subtaskEpic.getSubtasks().add(subtask);

        subtasks.put(subtask.getId(), subtask);
        System.out.printf("Создана подзадача %s.\n", subtask);
    }

    public void updateTaskById(int id, Task newTask) {
        Task task = getTaskById(id);

        if (task == null) {
            return;
        }

        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setStatus(newTask.getStatus());
        task.setId(newTask.getId());
    }

    public void updateEpicById(int id, Epic newEpic) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return;
        }

        epic.setName(newEpic.getName());
        epic.setDescription(newEpic.getDescription());
        epic.setStatus(newEpic.getStatus());
        epic.setId(newEpic.getId());
        epic.setSubtasks(newEpic.getSubtasks());
    }

    public void updateSubtaskById(int id, Subtask newSubtask) {
        Subtask subtask = getSubtaskById(id);

        if (subtask == null) {
            return;
        }

        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);

        subtask.setName(newSubtask.getName());
        subtask.setDescription(newSubtask.getDescription());
        subtask.setStatus(newSubtask.getStatus());
        subtask.setId(newSubtask.getId());
        subtask.setEpicId(newSubtask.getEpicId());

        epic.getSubtasks().remove(subtask);
        updateEpicStatusById(epicId);
    }

    public void removeTaskById(int id) {
        Task task = getTaskById(id);

        if (task == null) {
            return;
        }

        tasks.remove(id);
        System.out.printf("Удалена задача %s.\n", task);
    }

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
        System.out.printf("Удалён эпик %s.\n", epic);
    }

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

    public void printEpicSubtasksById(int id) {
        Epic epic = getEpicById(id);

        if (epic == null) {
            return;
        }

        ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

        if (epicSubtasks.isEmpty()) {
            System.out.printf("Список подзадач эпика %s пуст.\n", epic);
            return;
        }

        System.out.printf("Список всех подзадач эпика %d:\n", epic.getId());
        for (Subtask subTask : epicSubtasks) {
            System.out.printf("  - %s\n", subTask);
        }
    }

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
