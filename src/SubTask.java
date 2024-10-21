public class SubTask extends Task {

    private Epic epic;

    public SubTask(String name, String description, Epic epic) {
        super(name, description);
        this.epic = epic;

        this.epic.addSubTask(this);
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s, epic=%s}",
                this.getClass().getName(), super.getName(), super.getDescription(), super.getId(), super.getStatus(),
                this.epic);
    }

    @Override
    public void setStatus(Status status) {
        Status currentStatus = super.getStatus();

        if (currentStatus != status) {
            super.setStatus(status);
            System.out.printf("Статус подзадачи %d поменялся с %s на %s.\n", super.getId(), currentStatus, status);
            epic.updateStatus();
        }
    }

    public Epic getEpic() {
        return this.epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

}
