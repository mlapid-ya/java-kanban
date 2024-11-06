package model;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();

        if (epicId != null) {
            hash = hash + epicId;
        }

        hash = hash * 31;

        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s, epicId=%d}",
                this.getClass().getName(), super.getName(), super.getDescription(), super.getId(), super.getStatus(),
                this.epicId);
    }

}
