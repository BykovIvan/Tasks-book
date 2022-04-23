package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksOfEpic;
    private LocalDateTime endTime;

    public Epic(String name, String discription, Status status) {
        super(name, discription, status);
        subtasksOfEpic = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasksOfEpic() {
        return subtasksOfEpic;
    }

    public void setSubtasksOfEpic(ArrayList<Subtask> subtasksOfEpic) {
        this.subtasksOfEpic = subtasksOfEpic;
    }

    @Override
    public TypeOfTasks getTypeOfTask() {
        return TypeOfTasks.EPIC;
    }

    @Override
    public String getStartTime() {
        countStartAndEndTime();
        return startTime.format(formatter);
    }

    @Override
    public Duration getDuration() {
        countStartAndEndTime();
        return Duration.between(startTime, endTime);
    }

    @Override
    public String getEndTime() {
        countStartAndEndTime();
        return endTime.format(formatter);
    }

    /**
     * Расчет начала и конца времени Эпиков
     */
    private void countStartAndEndTime(){
        startTime = LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter);
        endTime = LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter);
        for (Subtask subtask : subtasksOfEpic) {
            LocalDateTime tempTime = LocalDateTime.parse(subtask.getStartTime(), formatter);
            if (tempTime.isBefore(startTime)){
                startTime = tempTime;
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