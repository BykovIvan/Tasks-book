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
    private TypeOfTasks typeOfTask;     //Тип задачи (Создан для создания Spring из задачи и обратно

    protected LocalDateTime startTime;     //дата, когда предпологается приступить к выполнению задачи
    protected Duration duration;           //продолжительность задачи
    protected DateTimeFormatter formatter;

    public Task(String name, String discription, Status status) {
        this.name = name;
        this.discription = discription;
        this.status = status;
        typeOfTask = TypeOfTasks.TASK;
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
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
        return startTime.format(formatter);
    }

    public void setStartTime(String startTimeStr) {
        startTime = LocalDateTime.parse(startTimeStr, formatter);
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(int durationInMin) {
        duration = Duration.ofMinutes(durationInMin) ;
    }

    public String getEndTime(){
        return startTime.plusMinutes(duration.toMinutes()).format(formatter);
    }

    @Override
    public String toString() {
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
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discription, idTask, status);
    }

    @Override
    public int compareTo(Task anotherTask) {
        if (this.startTime.isAfter(anotherTask.startTime)){
            return 1;
        }else if (this.startTime.isBefore(anotherTask.startTime)){
            return -1;
        }else {
            return 0;
        }
    }
}