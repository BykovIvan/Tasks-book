package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static main.java.tracker.util.TypeOfTasks.EPIC;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksOfEpic;
    private LocalDateTime endTime;;

    public Epic(String name, String discription, Status status) {
        super(name, discription, status);
        subtasksOfEpic = new ArrayList<>();
    }

    public Epic() {
    }

    public ArrayList<Subtask> getSubtasksOfEpic() {
        return subtasksOfEpic;
    }

    public void setSubtasksOfEpic(ArrayList<Subtask> subtasksOfEpic) {
        this.subtasksOfEpic = subtasksOfEpic;
    }

    @Override
    public TypeOfTasks getTypeOfTask() {
        super.setTypeOfTask(EPIC);
        return EPIC;
    }

    @Override
    public LocalDateTime getStartTime() {
        countStartAndEndTime();
        return startTime;

    }

    @Override
    public Duration getDuration() {
        Duration duration = null;
        countStartAndEndTime();
        if (startTime != null && endTime != null){
            duration = Duration.between(startTime, endTime);
            super.setDuration(duration.toMinutes());
        }
        return duration;
    }


    public LocalDateTime getEndTime() {
        countStartAndEndTime();
        return endTime;

    }

    /**
     * Расчет начала и конца времени Эпиков
     */
    private void countStartAndEndTime(){
        startTime = subtasksOfEpic.get(0).getStartTime();
        endTime = subtasksOfEpic.get(0).getStartTime();
        for (Subtask subtask : subtasksOfEpic) {
            LocalDateTime tempTime = subtask.getStartTime();
            if (tempTime.isBefore(startTime)){
                startTime = tempTime;
                super.setStartTime(startTime);
            }
            if (tempTime.isAfter(endTime)){
                endTime = tempTime;
            }
        }
    }

    @Override
    public String toString() {
        return "Epic {" +
                "\n name='" + getName() + '\'' +
                ", discription='" + getDiscription() + '\'' +
                ", idTask=" + getIdTask() +
                ", status=" + getStatus() +
                ",\n subtask=" + "\n " + subtasksOfEpic +
                '}' + "\n ";
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