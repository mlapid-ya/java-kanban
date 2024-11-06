import manager.*;
import model.Epic;
import model.Subtask;
import model.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = getTaskManager();

        printAllTasks(taskManager);
    }

    private static TaskManager getTaskManager() {
        Managers manager = new Managers();
        TaskManager taskManager = manager.getDefault();

        Task task1 = new Task("Задание 1", "Тест");
        taskManager.createTask(task1);

        Epic epic1 = new Epic("Эпик 1", "Тест");
        taskManager.createEpic(epic1);

        Epic epic2 = new Epic("Эпик 2", "Тест");
        taskManager.createEpic(epic2);

        Subtask subtask1 = new Subtask("Подзадача 1", "Тест", epic1.getId());
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача 2", "Тест", epic1.getId());
        taskManager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Подзадача 3", "Тест", epic2.getId());
        taskManager.createSubtask(subtask3);

        return taskManager;
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasksById(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История задач:");
        HistoryManager historyManager = manager.getHistoryManager();
        for (Task task : historyManager.getHistory()) {
            System.out.printf("  - %s\n", task);
        }
    }
}
