package tracker.model;

import tracker.util.Status;
import tracker.util.TypeOfTasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksOfEpic;

    public Epic(int idTask, String name, Status status, String discription) {
        super(idTask, name, status, discription);
        subtasksOfEpic = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasksOfEpic() {
        return subtasksOfEpic;
    }

    public void setSubtasksOfEpic(ArrayList<Subtask> subtasksOfEpic) {
        this.subtasksOfEpic = subtasksOfEpic;
    }

    @Override
    public String toString() {
        return "\n" + getIdTask() +
                "," + TypeOfTasks.EPIC +
                "," + getName() +
                "," + getStatus() +
                "," + getDiscription();

//                "Epic {" +
//                "\n name='" + getName() + '\'' +
//                ", discription='" + getDiscription() + '\'' +
//                ", idTask=" + getIdTask() +
//                ", status=" + getStatus() +
//                ",\n subtask=" + "\n " + subtasksOfEpic +
//                '}' + "\n ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtasksOfEpic, epic.subtasksOfEpic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasksOfEpic);
    }
}