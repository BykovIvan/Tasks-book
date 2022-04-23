package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.util.Objects;

public class Subtask extends Task {
    private int idEpic = -1;

    public Subtask(String name, String discription, Status status) {
        super(name, discription, status);
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public TypeOfTasks getTypeOfTask() {
        return TypeOfTasks.SUBTASK;
    }

    @Override
    public String toString() {
        if (startTime.isPresent() && duration.isPresent()){
            return "Subtask {" +
                    "name='" + getName() + '\'' +
                    ", discription='" + getDiscription() + '\'' +
                    ", idTask=" + getIdTask() +
                    ", status=" + getStatus() +
                    ", startTime=" + getStartTime() +
                    ", duraction=" + getDuration().toMinutes() +
                    '}' + "\n";
    }
        return "Subtask {" +
                "name='" + getName() + '\'' +
            ", discription='" + getDiscription() + '\'' +
            ", idTask=" + getIdTask() +
            ", status=" + getStatus() +
            '}' + "\n ";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return idEpic == subtask.idEpic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idEpic);
    }
}
