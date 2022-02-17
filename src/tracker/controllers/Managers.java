package tracker.controllers;

public abstract class Managers {
    public abstract TaskManager getDefault();

    public static HistoryManager getDefaultHistory() {
        return null;
    }
}
