import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Subtask> subtasksOfEpic;

    public Epic(String name, String discription) {
        super(name, discription);
        subtasksOfEpic = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasksOfEpic() {
        return subtasksOfEpic;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "\n name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", idTask=" + idTask +
                ", status=" + status +
                ",\n subtask=" + subtasksOfEpic +
                '}' + "\n ";
    }
}