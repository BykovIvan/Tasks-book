package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class Task implements Comparable<Task>  {
    private String name;                //Имя задачи
    private String discription;         //Описание задачи
    private int idTask;                 //ID задачи (создается автоматически при методе создания
    private Status status;              //Статус задачи
    private int idEpic;                 //ID эпика задачи (Если требуется)
    private final TypeOfTasks typeOfTask;     //Тип задачи (Создан для создания Spring из задачи и обратно

    protected Optional<LocalDateTime> startTime;     //дата, когда предпологается приступить к выполнению задачи
    protected Optional<Duration> duration;           //продолжительность задачи

    protected DateTimeFormatter formatter;

    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;
        typeOfTask = TypeOfTasks.TASK;
        startTime = Optional.empty();
        duration = Optional.empty();
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
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

    public TypeOfTasks getTypeOfTask() {
        return typeOfTask;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public String getStartTime() {
        if (startTime.isPresent()){
            return startTime.get().format(formatter);
        }
        return "Null";
    }

    public void setStartTime(String startTimeStr) {
        startTime = Optional.of(LocalDateTime.parse(startTimeStr, formatter));
    }

    public Duration getDuration() {
        if (duration.isPresent()){
            return duration.get();
        }
        return Duration.ofMillis(0);

    }

    public void setDuration(int durationInMin) {
        duration = Optional.of(Duration.ofMinutes(durationInMin));
    }

    public String getEndTime(){
        if (startTime.isPresent() & duration.isPresent()){
            return startTime.get().plusMinutes(duration.get().toMinutes()).format(formatter);
        }
        return "null";
    }

    @Override
    public String toString() {
        if (startTime.isPresent() && duration.isPresent()){
            return "Task {" +
                    "name='" + name + '\'' +
                    ", discription='" + discription + '\'' +
                    ", idTask=" + idTask +
                    ", status=" + status +
                    ", startTime=" + startTime.get().format(formatter) +
                    ", duraction=" + duration.get().toMinutes() +
                    '}' + "\n";
        }
        return "Task {" +
                "name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", idTask=" + idTask +
                ", status=" + status +
                '}' + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                Objects.equals(name, task.name) &&
                Objects.equals(discription, task.discription) &&
                Objects.equals(duration, task.duration) &&
                Objects.equals(startTime, task.startTime) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discription, idTask, status, discription, startTime);
    }

    @Override
    public int compareTo(Task anotherTask) {
        if (startTime.isPresent() && anotherTask.startTime.isPresent()){
            if (this.startTime.get().isAfter(anotherTask.startTime.get())){
                return 1;
            }else if (this.startTime.get().isBefore(anotherTask.startTime.get())){
                return -1;
            }else {
                return 0;
            }
        }else if (startTime.isPresent()){
            return -1;
        } else if (anotherTask.startTime.isPresent()){
            return 1;
        }
        return 1;

    }
}