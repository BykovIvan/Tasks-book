package main.java.tracker;

import main.java.tracker.controllers.InMemoryTaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

import java.util.TreeSet;

import static main.java.tracker.util.Status.NEW;
import static main.java.tracker.util.Status.IN_PROGRESS;
import static main.java.tracker.util.Status.DONE;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task = new Task("Task1", "Dis1", NEW);
        Task task2 = new Task("Task2", "Dis2", NEW);
        Task task3 = new Task("Task3", "Dis3", NEW);
        Task task4 = new Task("Task4", "Dis4", NEW);

        task.setStartTime("16.12.2022-12:21");
        task.setDuration(15);

        task2.setStartTime("13.08.2021-12:29");
        task2.setDuration(15);

        task4.setStartTime("15.10.2022-13:21");
        task4.setDuration(15);

        int idTask = manager.createNewTask(task);
        int idTask2 = manager.createNewTask(task2);
        int idTask3 = manager.createNewTask(task3);
        int idTask4 = manager.createNewTask(task4);

        System.out.println("Task:");
        System.out.println(manager.getTask(idTask).getStartTime());
        System.out.println(manager.getTask(idTask3).getStartTime());

        System.out.println(manager.getTask(idTask).getDuration().toHours() + " час " + manager.getTask(idTask).getDuration().toMinutesPart() + " мин");

        Epic epic = new Epic("Epic1", "Dis1", NEW);

        int idEpic = manager.createNewEpic(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Sub1", NEW);
        subtask1.setIdEpic(idEpic);
        subtask1.setStartTime("14.12.2022-11:21");
        subtask1.setDuration(20);

        int idSub1 = manager.createNewSubTask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2", "Sub2", NEW);
        subtask2.setIdEpic(idEpic);
        subtask2.setStartTime("14.10.2021-15:21");
        subtask2.setDuration(20);
        int idSub2 = manager.createNewSubTask(subtask2);

        Subtask subtask3 = new Subtask("Subtask3", "Sub3", NEW);
        subtask3.setIdEpic(idEpic);
        subtask3.setStartTime("14.09.2021-13:21");
        subtask3.setDuration(20);
        int idSub3 = manager.createNewSubTask(subtask3);


        TreeSet<Task> setList = manager.getPrioritizedTasks();
        for (Task task1 : setList) {
            System.out.print(task1);
        }

    }
}