import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, SubTask> subTasks;

    public Epic(String name, String description) {
        super(name, description);
        this.subTasks = new HashMap<>();
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s}",
                this.getClass().getName(), super.getName(), super.getDescription(), super.getId(), super.getStatus());
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return this.subTasks;
    }

    public void setSubTasks(HashMap<Integer, SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTask subTask) {
        this.subTasks.put(subTask.getId(), subTask);
        System.out.printf("Подзадача с идентификатором %d добавлена в эпик %d.\n", subTask.getId(), this.getId());
        this.updateStatus();
    }

    public void removeSubTaskById(int id) {
        if (this.subTasks.containsKey(id)) {
            this.subTasks.remove(id);
            System.out.printf("Удалена подзадача %d.\n", id);
            this.updateStatus();
        } else {
            System.out.printf("Подзадания с идентификатором %s не существует.\n", id);
        }
    }

    public void printSubTasks() {
        if (this.subTasks.isEmpty()) {
            System.out.printf("Список подзадач эпика %d пуст.\n", super.getId());
            return;
        }

        System.out.printf("Список всех подзадач эпика %d:\n", super.getId());
        for (SubTask subTask : this.subTasks.values()) {
            System.out.printf("  - %s\n", subTask);
        }
    }

    protected void updateStatus() {

        Status currentStatus = super.getStatus();
        Status newStatus;

        if (this.subTasks.isEmpty() || this.countSubTasksByStatus(Status.NEW) == this.subTasks.size()) {
            newStatus = Status.NEW;
        } else if (this.countSubTasksByStatus(Status.DONE) == this.subTasks.size()) {
            newStatus = Status.DONE;
        } else {
            newStatus = Status.IN_PROGRESS;
        }

        if (currentStatus != newStatus) {
            this.setStatus(newStatus);
            System.out.printf("Статус эпика %d поменялся с %s на %s.\n", super.getId(), currentStatus, newStatus);
        }
    }

    private int countSubTasksByStatus(Status status) {
        int counter = 0;

        for (SubTask subTask : this.subTasks.values()) {
            if (subTask.getStatus() == status) {
                counter++;
            }
        }
        return counter;
    }

}
