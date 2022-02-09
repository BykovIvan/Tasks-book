public class Task {
    protected String name;
    protected String discription;
    protected int idTask;
    protected Status status;

    public Task(String name, String discription) {
        this.name = name;
        this.discription = discription;
        this.status = Status.NEW;
    }

    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Override
    public String toString() {
        return "Task \n{" +
                "name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", idTask=" + idTask +
                ", status=" + status +
                '}';
    }
}