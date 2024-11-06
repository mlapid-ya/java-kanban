package model;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();

        if (!subtasks.isEmpty()) {
            hash = hash + subtasks.hashCode();
        }

        hash = hash * 31;

        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s}",
                this.getClass().getName(), super.getName(), super.getDescription(), super.getId(), super.getStatus());
    }

    public int countSubtasksByStatus(Status status) {
        int counter = 0;

        for (Subtask subTask : subtasks) {
            if (subTask.getStatus() == status) {
                counter++;
            }
        }
        return counter;
    }

}
