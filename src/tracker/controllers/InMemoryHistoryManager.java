package tracker.controllers;

import tracker.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> historyList;

    public InMemoryHistoryManager() {
        historyList = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        if (historyList.size() < 10) {
            historyList.add(task); // добавление задачи в лист истории
        } else {
            historyList.remove(0);
            historyList.add(9, task); // добавление задачи в лист истории
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
