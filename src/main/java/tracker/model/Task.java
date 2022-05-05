package main.java.tracker.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static main.java.tracker.util.TypeOfTasks.TASK;

public class Task {
    private String name;                //Имя задачи
    private String discription;         //Описание задачи
    private int idTask;                 //ID задачи (создается автоматически при методе создания
    private Status status;              //Статус задачи
    private TypeOfTasks typeOfTask = TASK;     //Тип задачи (Создан для создания Spring из задачи и обратно

    protected Optional<LocalDateTime> startTime;     //дата, когда предпологается приступить к выполнению задачи
    protected Optional<Duration> duration;          //продолжительность задачи

    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");


    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;
        startTime = Optional.empty();
        duration = Optional.empty();
    }

    public Task() {
        startTime = Optional.empty();
        duration = Optional.empty();

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

    public void setTypeOfTask(TypeOfTasks typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getStartTime() {
        if (startTime.isPresent()){
            return startTime.get().format(formatter);
        }else {
            return "null";
        }
    }

    public void setStartTime(LocalDateTime startTimeStr) {
        startTime = Optional.of(startTimeStr);
    }

    public Duration getDuration() {
        if (duration.isPresent()){
            return duration.get();
        }else {
            return Duration.ofMinutes(0);
        }
    }

    public void setDuration(long durationInMin) {
        duration = Optional.of(Duration.ofMinutes(durationInMin));
    }

    public String getEndTime(){
        if (startTime.isPresent() && duration.isPresent()){
            return startTime.get().plusMinutes(duration.get().toMinutes()).format(formatter);
        }
        else {
            return "null";
        }
    }

    @Override
    public String toString() {
        if (startTime.isPresent() && duration.isPresent()){
            return "Task {" +
                    "name='" + name + '\'' +
                    ", discription='" + discription + '\'' +
                    ", idTask=" + idTask +
                    ", status=" + status +
                    ", startTime=" + startTime.get() +
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

}