package main.java.tracker.model;

import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static main.java.tracker.util.TypeOfTasks.TASK;

public class Task  {
    private String name;                //Имя задачи
    private String discription;         //Описание задачи
    private int idTask;                 //ID задачи (создается автоматически при методе создания
    private Status status;              //Статус задачи
    private TypeOfTasks typeOfTask = TASK;     //Тип задачи (Создан для создания Spring из задачи и обратно

    protected LocalDateTime startTime;     //дата, когда предпологается приступить к выполнению задачи
    protected Duration duration;          //продолжительность задачи


    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;

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

    protected void setTypeOfTask(TypeOfTasks typeOfTask) {
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



}