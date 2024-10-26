import manager.TaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

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

        taskManager.printAllTasks();
        taskManager.printAllSubtasks();
        taskManager.printAllEpics();

        taskManager.updateSubtaskStatusById(subtask1.getId(), Status.DONE);
        taskManager.updateSubtaskStatusById(subtask2.getId(), Status.DONE);
        taskManager.printEpicSubtasksById(epic1.getId());

        taskManager.removeSubtaskById(subtask1.getId());

        taskManager.updateSubtaskById(subtask2.getId(), subtask3);
        taskManager.printEpicSubtasksById(epic1.getId());
        taskManager.printEpicSubtasksById(epic2.getId());

////        taskManager.printAllSubTasks();
////        taskManager.printAllEpics();
//        taskManager.printEpicSubtasksById(epic2.getId());
    }
}
