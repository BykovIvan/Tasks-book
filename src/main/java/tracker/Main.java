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

        int idTask = manager.createNewTask(task);
        int idTask2 = manager.createNewTask(task2);

        manager.getTask(idTask).setStartTime("15.10.2022, 12:21");
        manager.getTask(idTask2).setStartTime("14.08.2022, 12:21");
        System.out.println("Task:");
        System.out.println(manager.getTask(idTask).getStartTime());
        manager.getTask(idTask).setDuration(34);
        manager.getTask(idTask2).setDuration(43);

        System.out.println(manager.getTask(idTask).getEndTime());
        System.out.println(manager.getTask(idTask).getDuration().toHours() + " час " + manager.getTask(idTask).getDuration().toMinutesPart() + " мин");

        Epic epic = new Epic("Epic1", "Dis1", NEW);

        int idEpic = manager.createNewEpic(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Sub1", NEW);
        subtask1.setIdEpic(idEpic);

        int idSub1 = manager.createNewSubTask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2", "Sub2", NEW);
        subtask2.setIdEpic(idEpic);

        int idSub2 = manager.createNewSubTask(subtask2);

        Subtask subtask3 = new Subtask("Subtask3", "Sub3", NEW);
        subtask2.setIdEpic(idEpic);

        int idSub3 = manager.createNewSubTask(subtask3);

        manager.getSubtask(idSub1).setStartTime("14.09.2023, 10:21");
        manager.getSubtask(idSub1).setDuration(20);
        manager.getSubtask(idSub2).setStartTime("15.09.2023, 04:26");
        manager.getSubtask(idSub2).setDuration(14);
        manager.getSubtask(idSub3).setStartTime("13.09.2023, 23:11");
        manager.getSubtask(idSub3).setDuration(7);
        System.out.println("Epic:");
        System.out.println(manager.getEpic(idEpic).getStartTime());
        System.out.println(manager.getEpic(idEpic).getEndTime());
        System.out.println(manager.getEpic(idEpic).getDuration().toHours() + " час " + manager.getEpic(idEpic).getDuration().toMinutesPart() + " мин");
        System.out.println(manager.getEpic(idEpic).getDuration().toMinutes());

        TreeSet<Task> setList = manager.getPrioritizedTasks();
        for (Task task1 : setList) {
            System.out.println(task1);
        }



    }
}