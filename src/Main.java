public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задание 1", "Тест");
        Epic epic1 = new Epic("Эпик 1", "Тест");
        Epic epic2 = new Epic("Эпик 2", "Тест");
        SubTask subTask1 = new SubTask("Подзадача 1", "Тест", epic1);
        SubTask subTask2 = new SubTask("Подзадача 2", "Тест", epic1);
        SubTask subTask3 = new SubTask("Подзадача 3", "Тест", epic2);

        taskManager.createTask(task1);
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);

        taskManager.updateSubTaskStatusById(subTask1.getId(), Status.DONE);
        taskManager.updateSubTaskStatusById(subTask2.getId(), Status.DONE);
        taskManager.updateSubTaskById(subTask1.getId(), subTask3);
        taskManager.printAllSubTasks();
        taskManager.printAllEpics();
        taskManager.printEpicSubTasksById(epic1.getId());
        taskManager.printEpicSubTasksById(epic2.getId());
    }
}
