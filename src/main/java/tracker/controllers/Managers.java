package main.java.tracker.controllers;

import main.java.tracker.history.HistoryManager;
import main.java.tracker.history.InMemoryHistoryManager;
import main.java.tracker.model.Task;

/**
 * Утилитарный класс для метод создания объектов
 */

public class Managers {

    private static final TaskManager taskManager = new InMemoryTaskManager();

    private static final HistoryManager<Task> inMemoryHistoryManager = new InMemoryHistoryManager<>();

    public static TaskManager getDefault() {
        return taskManager;
    }

    public static HistoryManager<Task> getDefaultHistory() {
        return inMemoryHistoryManager;
    }
}
