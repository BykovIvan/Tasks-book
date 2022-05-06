package main.java.tracker.controllers;

import main.java.tracker.history.HistoryManager;
import main.java.tracker.history.InMemoryHistoryManager;
import main.java.tracker.model.Task;

/**
 * Утилитарный класс для метод создания объектов
 */

public class Managers {

    public static TaskManager getDefault() {
        return new HTTPTaskManager("http://localhost:8078");
    }

    public static HistoryManager<Task> getDefaultHistory() {
        return new InMemoryHistoryManager<>();
    }
}
