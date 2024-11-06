package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {

    public HashMap<Integer, Task> getTasks();

    public HashMap<Integer, Epic> getEpics();

    public HashMap<Integer, Subtask> getSubtasks();

    public void printAllTasks();

    public void printAllEpics();

    public void printAllSubtasks();

    public void removeAllTasks();

    public void removeAllEpics();

    public void removeAllSubtasks();

    public Task getTaskById(int id);

    public Epic getEpicById(int id);

    public Subtask getSubtaskById(int id);

    public void createTask(Task task);

    public void createEpic(Epic epic);

    public void createSubtask(Subtask subtask);

    public void updateTask(Task newTask);

    public void updateEpic(Epic newEpic);

    public void updateSubtask(Subtask newSubtask);

    public void removeTaskById(int id);

    public void removeEpicById(int id);

    public void removeSubtaskById(int id);

    public ArrayList<Subtask> getEpicSubtasksById(int id);

    public void printEpicSubtasksById(int id);

    public void updateTaskStatusById(int id, Status newStatus);

    public void updateSubtaskStatusById(int id, Status newStatus);

    public HistoryManager getHistoryManager();



}
