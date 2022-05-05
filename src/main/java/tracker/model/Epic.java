package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static main.java.tracker.util.TypeOfTasks.EPIC;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksOfEpic;
    private Optional<LocalDateTime> endTime = Optional.empty();

    public Epic(String name, String discription, Status status) {
        super(name, discription, status);
        subtasksOfEpic = new ArrayList<>();
    }

    public Epic() {
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
        super.setTypeOfTask(EPIC);
        return EPIC;
    }

    @Override
    public String getStartTime() {
        countStartAndEndTime();
        if (startTime.isPresent()){
            return startTime.get().format(formatter);
        }else {
            return "null";
        }

    }

    @Override
    public Duration getDuration() {
        Duration duration = null;
        countStartAndEndTime();
        if (startTime.isPresent() && endTime.isPresent()){
            duration = Duration.between(startTime.get(), endTime.get());
            super.setDuration(duration.toMinutes());
            return duration;
        }
        return Duration.ofMinutes(0);
    }


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
        if (subtasksOfEpic.size() > 0){
            if (subtasksOfEpic.size() == 1){
                startTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter));
                super.setStartTime(startTime.get());
                endTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter).plusMinutes(subtasksOfEpic.get(0).getDuration().toMinutes()));
            }else {
                startTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter));
                endTime = Optional.of(LocalDateTime.parse(subtasksOfEpic.get(0).getStartTime(), formatter));
                for (Subtask subtask : subtasksOfEpic) {
                    LocalDateTime tempTimeStart = LocalDateTime.parse(subtask.getStartTime(), formatter);
                    LocalDateTime tempTimeEnd = LocalDateTime.parse(subtask.getStartTime(), formatter).plusMinutes(subtask.getDuration().toMinutes());
                    if (tempTimeStart.isBefore(startTime.get())){
                        startTime = Optional.of(tempTimeStart);
                        super.setStartTime(startTime.get());
                    }
                    if (tempTimeEnd.isAfter(endTime.get())){
                        endTime = Optional.of(tempTimeEnd);
                    }
                }
            }

        }
//        else{
//            startTime = null;
//            super.setStartTime(startTime.get());
//            endTime = null;
//        }

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