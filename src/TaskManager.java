import java.util.HashMap;

public class TaskManager {

    private int taskCounter = 0;

    public final HashMap<Integer, Task> tasks;
    public final HashMap<Integer, Epic> epics;
    public final HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public int getTaskCounter() {
        return this.taskCounter;
    }

    public void printAllTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }

        System.out.println("Список всех задач:");
        for (Task task : this.tasks.values()) {
            System.out.printf("  - %s\n", task);
        }
    }

    public void printAllEpics() {
        if (this.epics.isEmpty()) {
            System.out.println("Список эпиков пуст.");
            return;
        }

        System.out.println("Список всех эпиков:");
        for (Epic epic : this.epics.values()) {
            System.out.printf("  - %s\n", epic);
        }
    }

    public void printAllSubTasks() {
        if (this.subTasks.isEmpty()) {
            System.out.println("Список подзадач пуст.");
            return;
        }

        System.out.println("Список всех подзадач:");
        for (SubTask subTask : this.subTasks.values()) {
            System.out.printf("  - %s\n", subTask);
        }
    }

    public void removeAllTasks() {
        this.tasks.clear();
        System.out.println("Все задачи удалены.");
    }

    public void removeAllEpics() {
        for (int id : this.epics.keySet()) {
            this.removeEpicById(id);
        }
    }

    public void removeAllSubTasks() {

        HashMap<Integer, SubTask> subTasksCopy = new HashMap<Integer, SubTask>(this.subTasks);

        for (int id : subTasksCopy.keySet()) {
            this.removeSubTaskById(id);
        }
    }

    public Task getTaskById(int id) {
        if (this.tasks.containsKey(id)) {
            return tasks.get(id);
        } else {
            System.out.printf("Задания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public Epic getEpicById(int id) {
        if (this.epics.containsKey(id)) {
            return this.epics.get(id);
        } else {
            System.out.printf("Эпика с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public SubTask getSubTaskById(int id) {
        if (this.subTasks.containsKey(id)) {
            return this.subTasks.get(id);
        } else {
            System.out.printf("Подзадания с идентификатором %s не существует.\n", id);
            return null;
        }
    }

    public void createTask(Task task) {
        this.tasks.put(task.getId(), task);
        this.taskCounter++;
        System.out.printf("Создана задача %s\n", task);
    }

    public void createEpic(Epic epic) {
        this.epics.put(epic.getId(), epic);
        this.taskCounter++;
        System.out.printf("Создан эпик %s\n", epic);
    }

    public void createSubTask(SubTask subTask) {
        this.subTasks.put(subTask.getId(), subTask);
        this.taskCounter++;
        System.out.printf("Создана подзадача %s\n", subTask);
    }

    public void updateTaskById(int id, Task newTask) {
        Task task = this.getTaskById(id);

        if (task == null) {
            return;
        }

        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setStatus(newTask.getStatus());
    }

    public void updateEpicById(int id, Epic newEpic) {
        Epic epic = this.getEpicById(id);

        if (epic == null) {
            return;
        }

        epic.setName(newEpic.getName());
        epic.setDescription(newEpic.getDescription());
        epic.setStatus(newEpic.getStatus());
        epic.setSubTasks(newEpic.getSubTasks());
    }

    public void updateSubTaskById(int id, SubTask newSubTask) {
        SubTask subTask = this.getSubTaskById(id);

        if (subTask == null) {
            return;
        }

        Epic epic = subTask.getEpic();

        subTask.setName(newSubTask.getName());
        subTask.setDescription(newSubTask.getDescription());
        subTask.setStatus(newSubTask.getStatus());
        subTask.setEpic(newSubTask.getEpic());
    }

    public void removeTaskById(int id) {
        Task task = this.getTaskById(id);

        if (task == null) {
            return;
        }

        this.tasks.remove(id);
        System.out.printf("Удалена задача %s\n", task);
    }

    public void removeEpicById(int id) {
        Epic epic = this.getEpicById(id);

        if (epic == null) {
            return;
        }

        if (epic.getSubTasks().isEmpty()) {
            this.epics.remove(id);
            System.out.printf("Удалён эпик %s\n", epic);
        } else {
            System.out.printf("Удаление эпика %d невозможно, в нём находятся подзадачи.\n", epic.getId());
            epic.printSubTasks();
        }
    }

    public void removeSubTaskById(int id) {
        SubTask subTask = this.getSubTaskById(id);

        if (subTask == null) {
            return;
        }
        this.subTasks.remove(id);
        Epic epic = subTask.getEpic();
        epic.removeSubTaskById(id);
    }

    public void printEpicSubTasksById(int id) {
        Epic epic = this.getEpicById(id);

        if (epic == null) {
            return;
        }

        epic.printSubTasks();
    }

    public void updateTaskStatusById(int id, Status newStatus) {
         Task task = this.getTaskById(id);

         if (task == null) {
            return;
         }

         task.setStatus(newStatus);
    }

    public void updateSubTaskStatusById(int id, Status newStatus) {
        SubTask subTask = this.getSubTaskById(id);

        if (subTask == null) {
            return;
        }

        subTask.setStatus(newStatus);
    }
}
