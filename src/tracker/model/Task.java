package tracker.model;

import tracker.util.Status;
import tracker.util.TypeOfTasks;

import java.util.Objects;

public class Task {
    private String name;
    private String discription;
    private int idTask;
    private Status status;
    private int idEpic;

//    public Task(String name, String discription) {
//        this.name = name;
//        this.discription = discription;
//        this.status = Status.NEW;
//        epic = this.epic;
//    }

    public Task(int idTask, String name, Status status, String discription) {
        this.name = name;
        this.discription = discription;
        this.status = status;
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "\n" + idTask +
                "," + TypeOfTasks.TASK +
                "," + name +
                "," + status +
                "," + discription;

//                "Task {" +
//                "name='" + name + '\'' +
//                ", discription='" + discription + '\'' +
//                ", idTask=" + idTask +
//                ", status=" + status +
//                '}' + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                Objects.equals(name, task.name) &&
                Objects.equals(discription, task.discription) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discription, idTask, status);
    }
}