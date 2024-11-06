import manager.HistoryManager;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

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

        printAllTasks(taskManager);

//        taskManager.getEpicById(epic1.getId());
//        taskManager.getEpicById(epic2.getId());
//        taskManager.getEpicById(epic2.getId());
//
//        taskManager.printHistory();

//        taskManager.printAllTasks();
//        taskManager.printAllSubtasks();
//        taskManager.printAllEpics();
//
//        taskManager.updateSubtaskStatusById(subtask1.getId(), Status.DONE);
//        taskManager.updateSubtaskStatusById(subtask2.getId(), Status.DONE);
//        taskManager.printEpicSubtasksById(epic1.getId());
//
//        taskManager.removeSubtaskById(subtask1.getId());
//
//        subtask2.setStatus(Status.NEW);
//        taskManager.updateSubtask(subtask2);
//
//        subtask3.setStatus(Status.DONE);
//        taskManager.updateSubtask(subtask3);
//        epic2.getSubtasks().clear();
//        taskManager.updateEpic(epic2);
//
//        taskManager.printEpicSubtasksById(epic1.getId());
//        taskManager.printEpicSubtasksById(epic2.getId());
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks().values()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics().values()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasksById(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks().values()) {
            System.out.println(subtask);
        }

        System.out.println("История задач:");
        HistoryManager historyManager = manager.getHistoryManager();
        for (Task task : historyManager.getHistory()) {
            System.out.printf("  - %s\n", task);
        }
    }
}
