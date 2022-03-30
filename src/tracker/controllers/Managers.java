package tracker.controllers;

import tracker.history.HistoryManager;
import tracker.history.InMemoryHistoryManager;
import tracker.model.Task;

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
