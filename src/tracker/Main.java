package tracker;

import tracker.controllers.InMemoryTaskManager;
import tracker.controllers.Managers;
import tracker.controllers.TaskManager;
import tracker.model.*;
import tracker.util.Status;

public class Main {
    public static void main(String[] args) {
        Task task = new Task("Купить", "Что купить", Status.NEW);
        Task task2 = new Task("Купить", "Что купить", Status.IN_PROGRESS);

        Epic epic1 = new Epic("Занятия", "В зале");
        Epic epic2 = new Epic("Занятия2", "В зале2");

        Subtask subtask1 = new Subtask("Бегать1", "Бежать прямой1", Status.NEW);
        Subtask subtask2 = new Subtask("Бегать2", "Бежать прямой2", Status.NEW);
        Subtask subtask3 = new Subtask("Бегать3", "Бежать прямой3", Status.NEW);

        Subtask subtask4 = new Subtask("Бегать4", "Бежать прямой4", Status.DONE);
        Subtask subtask5 = new Subtask("Бегать5", "Бежать прямой5", Status.DONE);
        Subtask subtask6 = new Subtask("Бегать6", "Бежать прямой6", Status.DONE);
        Subtask subtask7 = new Subtask("Бегать7", "Бежать прямой7", Status.DONE);

        TaskManager manager = new InMemoryTaskManager();

        manager.createNewTask(task);
        manager.createNewTask(task2);
        manager.createNewEpic(epic1);
        manager.createNewEpic(epic2);
        manager.createNewSubTask(subtask1, 2);
        manager.createNewSubTask(subtask2, 2);
        manager.createNewSubTask(subtask3, 2);
        manager.createNewSubTask(subtask4, 3);
        manager.createNewSubTask(subtask5, 3);
        manager.createNewSubTask(subtask6, 3);
        manager.createNewSubTask(subtask7, 3);
//        System.out.println(manager.getTasks());

        manager.getEpic(2);
        manager.getSubtask(6);
        manager.getSubtask(6);
        manager.getSubtask(6);
        manager.getSubtask(7);
        manager.getSubtask(7);
        manager.getSubtask(5);
        manager.getSubtask(7);
        manager.getSubtask(5);
        manager.getTask(0);
        manager.getTask(0);
        manager.getTask(1);
        manager.getTask(1);
        manager.getTask(1);
        manager.getTask(1);
        manager.getEpic(2);
        manager.getEpic(2);
        System.out.println("История:");
        System.out.println(manager.history());

    }
}