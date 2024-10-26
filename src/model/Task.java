package model;

public class Task {

    private String name;
    private String description;
    private Status status = Status.NEW;
    private int id;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return this.id == task.id;
    }

    @Override
    public int hashCode() {
        int hash = 17;

        if (name != null) {
            hash = hash + name.hashCode();
        }

        hash = hash * 31;

        if (description != null) {
            hash = hash + description.hashCode();
        }

        hash = hash * 31;

        if (status != null) {
            hash = hash + status.hashCode();
        }

        hash = hash * 31;

        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s}",
                this.getClass().getName(), name, description, id, status);
    }
}
