package tracker.model;

import tracker.util.Status;
import tracker.util.TypeOfTasks;
import java.util.Objects;

public class Subtask extends Task {
    private int idEpic;

    public Subtask(int idTask, String name, Status status, String discription, int idEpic) {
        super(idTask, name, status, discription);
        this.idEpic = idEpic;
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
