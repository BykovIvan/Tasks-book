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

import static main.java.tracker.util.TypeOfTasks.TASK;

public class Task {
    private String name;                //Имя задачи
    private String discription;         //Описание задачи
    private int idTask;                 //ID задачи (создается автоматически при методе создания
    private Status status;              //Статус задачи
    private TypeOfTasks typeOfTask = TASK;     //Тип задачи (Создан для создания Spring из задачи и обратно

    protected LocalDateTime startTime;     //дата, когда предпологается приступить к выполнению задачи
    protected Duration duration;          //продолжительность задачи
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");


    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;

    }

    public Task() {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTimeStr) {
        startTime = startTimeStr;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(long durationInMin) {
        duration = Duration.ofMinutes(durationInMin);
    }

    public LocalDateTime getEndTime(){
            return startTime.plusMinutes(duration.toMinutes());
    }

    @Override
    public String toString() {
        if (startTime != null && duration != null){
            return "Task {" +
                    "name='" + name + '\'' +
                    ", discription='" + discription + '\'' +
                    ", idTask=" + idTask +
                    ", status=" + status +
                    ", startTime=" + startTime +
                    ", duraction=" + duration.toMinutes() +
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
//
//    @Override
//    public JsonElement serialize(Task task, Type type, JsonSerializationContext jsonSerializationContext) {
//        JsonObject result = new JsonObject();
//
//        result.addProperty("name", task.getName());
//        result.addProperty("discription", task.getDiscription());
//        result.addProperty("idTask", task.getIdTask());
//        result.addProperty("status", String.valueOf(task.getStatus()));
//        result.addProperty("typeOfTask", String.valueOf(task.getTypeOfTask()));
//        result.addProperty("startTime", task.getStartTime().format(formatter) );
//        result.addProperty("duration", task.getDuration().toMinutes());
//        return result;
//    }
}