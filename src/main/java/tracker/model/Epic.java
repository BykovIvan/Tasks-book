package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksOfEpic;
    private Optional<LocalDateTime> endTime;

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
        if (startTime.isPresent()){
            return startTime.get().format(formatter);
        }
        return "null";
    }

    @Override
    public Duration getDuration() {
        countStartAndEndTime();
        if (startTime.isPresent() && endTime.isPresent()){
            return Duration.between(startTime.get(), endTime.get());
        }
        return Duration.ofMillis(0);
    }

    @Override
    public String getEndTime() {
        countStartAndEndTime();
        if (endTime.isPresent()){
            return endTime.get().format(formatter);
        }
        return "null";
    }

    /**
     * Расчет начала и конца времени Эпиков
     */
    private void countStartAndEndTime(){
        startTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter));
        endTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter));
        for (Subtask subtask : subtasksOfEpic) {
            LocalDateTime tempTime = LocalDateTime.parse(subtask.getStartTime(), formatter);
            if (tempTime.isBefore(startTime.get())){
                startTime = Optional.of(tempTime);
            }
            if (tempTime.isAfter(endTime.get())){
                endTime = Optional.of(tempTime);
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