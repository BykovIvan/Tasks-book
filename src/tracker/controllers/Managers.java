package tracker.controllers;

public class Managers{

    private static final TaskManager taskManager = new InMemoryTaskManager();
    private static final HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    public static TaskManager getDefault(){
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }
}
