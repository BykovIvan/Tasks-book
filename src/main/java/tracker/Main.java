package main.java.tracker;

import main.java.tracker.controllers.InMemoryTaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

import static main.java.tracker.util.Status.NEW;
import static main.java.tracker.util.Status.IN_PROGRESS;
import static main.java.tracker.util.Status.DONE;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task = new Task("Task1", "Dis1", NEW);
        int idTask = manager.createNewTask(task);
        manager.getTask(idTask).setStartTime("12.09.2022, 12:21");
        System.out.println(manager.getTask(idTask).getStartTime());
        manager.getTask(idTask).setDuration(34);
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
        manager.getSubtask(idSub2).setStartTime("13.09.2023, 04:26");
        manager.getSubtask(idSub2).setDuration(14);
        manager.getSubtask(idSub3).setStartTime("13.09.2023, 23:11");
        manager.getSubtask(idSub3).setDuration(7);
        System.out.println("Epic:");
        System.out.println(manager.getEpic(idEpic).getStartTime());
        System.out.println(manager.getEpic(idEpic).getEndTime());
        System.out.println(manager.getEpic(idEpic).getDuration().toHours() + " час " + manager.getEpic(idEpic).getDuration().toMinutesPart() + " мин");
        System.out.println(manager.getEpic(idEpic).getDuration().toMinutes());


//        InMemoryTaskManager manager = new InMemoryTaskManager();
//
//        manager.createNewTask(new Task(0, "Имя1", Status.NEW, "Что купить1"));
//        manager.createNewTask(new Task(1, "Имя2", Status.NEW, "Что купить2"));
//
//        manager.createNewEpic(new Epic(2, "Имя1", Status.NEW, "Где купить1"));
//
//        manager.createNewSubTask(new Subtask(3, "Саб1", Status.NEW, "Где взять", 2));
//        manager.createNewSubTask(new Subtask(4, "Саб2", Status.NEW, "Где взять", 2));
//
//        manager.createNewTask(new Task(5, "Имя3", Status.NEW, "Что купить3"));
//        manager.createNewTask(new Task(6, "Имя4", Status.NEW, "Что купить4"));
//        manager.createNewTask(new Task(7, "Имя5", Status.NEW, "Что купить5"));
//
//
//
////        manager.save();
////        System.out.println(manager.getTasks());
//
////        manager.getTask(0);
////        manager.getTask(1);
//        manager.getTask(5);
//        manager.getTask(6);
//        manager.getTask(7);
//        manager.getEpic(2);
//        manager.getSubtask(4);
//        manager.getSubtask(3);
//        manager.getTask(0);


//        System.out.println("История:");
//        System.out.println(manager.history());


//        System.out.println(manager.getEpic(0));


//        Task task2 = new Task("Купить1", "Что купить", Status.IN_PROGRESS);
//        Task task3 = new Task("Купить2", "Что купить", Status.IN_PROGRESS);
//        Task task4 = new Task("Купить3", "Что купить", Status.IN_PROGRESS);
//        Task task5 = new Task("Купить4", "Что купить", Status.IN_PROGRESS);
//        Task task6 = new Task("Купить5", "Что купить", Status.IN_PROGRESS);

//        Epic epic1 = new Epic("Занятия6", "В зале");
//        Epic epic2 = new Epic("Занятия7", "В зале2");
//
//        Subtask subtask1 = new Subtask("Бегать8", "Бежать прямой1", Status.DONE);
//        Subtask subtask2 = new Subtask("Бегать9", "Бежать прямой2", Status.DONE);
//        Subtask subtask3 = new Subtask("Бегать10", "Бежать прямой3", Status.NEW);
//
//        Subtask subtask4 = new Subtask("Бегать11", "Бежать прямой4", Status.DONE);
//        Subtask subtask5 = new Subtask("Бегать12", "Бежать прямой5", Status.DONE);
//        Subtask subtask6 = new Subtask("Бегать13", "Бежать прямой6", Status.DONE);
//        Subtask subtask7 = new Subtask("Бегать14", "Бежать прямой7", Status.NEW);

//        TaskManager manager = Managers.getDefault();
//        InMemoryTaskManager manager = new InMemoryTaskManager();

//        manager.createNewTask(task1);
//        manager.createNewTask(task2);
//        manager.createNewTask(task3);
//        manager.createNewTask(task4);
//        manager.createNewTask(task5);
//        manager.createNewTask(task6);
//
//        manager.createNewEpic(epic1);
//        manager.createNewEpic(epic2);
//
//        manager.createNewSubTask(subtask1, 6);
//        manager.createNewSubTask(subtask2, 6);
//        manager.createNewSubTask(subtask3, 6);
//
//        manager.createNewSubTask(subtask4, 7);
//        manager.createNewSubTask(subtask5, 7);
//        manager.createNewSubTask(subtask6, 7);
//        manager.createNewSubTask(subtask7, 7);

//        manager.getTask(1);
//        manager.getTask(2);
//        manager.getTask(5);
//        manager.getTask(4);
//        manager.getTask(3);


//        manager.getEpic(6);
//        manager.getEpic(7);

//        manager.getSubtask(8);
//        manager.getSubtask(9);
//        manager.getSubtask(10);

//        manager.getSubtask(11);
//        manager.getSubtask(12);
//        manager.getSubtask(13);
//        manager.getSubtask(14);

//        manager.deleteTask(5);
//        manager.deleteSubtaskWithOutHistory(14);
//        manager.deleteEpic(6);

//        manager.getEpic(7);
//        manager.getTask(3);


    }
}