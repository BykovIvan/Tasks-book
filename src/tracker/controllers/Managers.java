package tracker.controllers;

public class Managers{

    private static TaskManager taskManager;
    private static InMemoryHistoryManager inMemoryHistoryManager;

    public static TaskManager getDefault(){
        return taskManager;
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }
}
