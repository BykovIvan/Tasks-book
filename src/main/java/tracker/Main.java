package main.java.tracker;

import main.java.tracker.controllers.HTTPTaskManager;
import main.java.tracker.controllers.InMemoryTaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

import static main.java.tracker.util.Status.NEW;
import static main.java.tracker.util.Status.IN_PROGRESS;
import static main.java.tracker.util.Status.DONE;

public class Main {
    public static void main(String[] args) {
        HTTPTaskManager manager = new HTTPTaskManager("http://localhost:8078");
        manager.restoreHttpTaskManager();
        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
        System.out.println("History:");
        System.out.println(manager.history());



    }
}