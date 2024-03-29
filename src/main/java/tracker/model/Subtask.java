package main.java.tracker.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.lang.reflect.Type;
import java.util.Objects;

import static main.java.tracker.util.TypeOfTasks.SUBTASK;

public class Subtask extends Task {
    private int idEpic = -1;

    public Subtask(String name, String discription, Status status) {
        super(name, discription, status);
    }

    public Subtask() {
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public TypeOfTasks getTypeOfTask() {
        super.setTypeOfTask(SUBTASK);
        return SUBTASK;
    }

    @Override
    public String toString() {
        if (startTime.isPresent() && duration.isPresent()){
            return "Subtask {" +
                    "name='" + getName() + '\'' +
                    ", discription='" + getDiscription() + '\'' +
                    ", idTask=" + getIdTask() +
                    ", idEpic=" + idEpic +
                    ", status=" + getStatus() +
                    ", startTime=" + getStartTime() +
                    ", duraction=" + getDuration().toMinutes() +
                    '}' + "\n";
        }
        return "Subtask {" +
                "name='" + getName() + '\'' +
                ", discription='" + getDiscription() + '\'' +
                ", idTask=" + getIdTask() +
                ", idEpic=" + idEpic +
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
