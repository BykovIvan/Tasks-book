package tracker;

import tracker.controllers.InMemoryTaskManager;
import tracker.model.*;
import tracker.util.Status;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Купить0", "Что купить", Status.NEW);
        Task task2 = new Task("Купить1", "Что купить", Status.IN_PROGRESS);
        Task task3 = new Task("Купить2", "Что купить", Status.IN_PROGRESS);
        Task task4 = new Task("Купить3", "Что купить", Status.IN_PROGRESS);
        Task task5 = new Task("Купить4", "Что купить", Status.IN_PROGRESS);
        Task task6 = new Task("Купить5", "Что купить", Status.IN_PROGRESS);

        Epic epic1 = new Epic("Занятия6", "В зале");
        Epic epic2 = new Epic("Занятия7", "В зале2");

        Subtask subtask1 = new Subtask("Бегать8", "Бежать прямой1", Status.DONE);
        Subtask subtask2 = new Subtask("Бегать9", "Бежать прямой2", Status.DONE);
        Subtask subtask3 = new Subtask("Бегать10", "Бежать прямой3", Status.DONE);

        Subtask subtask4 = new Subtask("Бегать11", "Бежать прямой4", Status.DONE);
        Subtask subtask5 = new Subtask("Бегать12", "Бежать прямой5", Status.DONE);
        Subtask subtask6 = new Subtask("Бегать13", "Бежать прямой6", Status.DONE);
        Subtask subtask7 = new Subtask("Бегать14", "Бежать прямой7", Status.NEW);

//        TaskManager manager = Managers.getDefault();
        InMemoryTaskManager manager = new InMemoryTaskManager();

        manager.createNewTask(task1);
        manager.createNewTask(task2);
        manager.createNewTask(task3);
        manager.createNewTask(task4);
        manager.createNewTask(task5);
        manager.createNewTask(task6);

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

        manager.getTask(0);
        manager.getTask(1);
        manager.getTask(2);
        manager.getTask(3);
        manager.getTask(4);
        manager.getTask(5);

//        manager.getEpic(6);
//        manager.getEpic(7);
//
//        manager.getSubtask(8);
//        manager.getSubtask(9);
//        manager.getSubtask(10);
//
//        manager.getSubtask(11);
//        manager.getSubtask(12);
//        manager.getSubtask(13);
//        manager.getSubtask(14);

//        manager.deleteTask(5);
//        manager.deleteSubtask(14);
//        manager.deleteEpic(6);
        System.out.println("История:");
        System.out.println(manager.history());

    }
}