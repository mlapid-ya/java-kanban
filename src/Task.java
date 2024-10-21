public class Task {

    private String name;
    private String description;
    private Status status;
    private final int id;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
        this.id = this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        int hash = 17;

        if (this.name != null) {
            hash = hash + this.name.hashCode();
        }

        hash = hash * 31;

        if (this.description != null) {
            hash = hash + this.description.hashCode();
        }

        hash = hash * 31;

        if (this.status != null) {
            hash = hash + this.status.hashCode();
        }

        hash = hash + this.getClass().getName().hashCode();

        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', description='%s', id=%d, status=%s}",
                this.getClass().getName(), name, description, id, status);
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
}
