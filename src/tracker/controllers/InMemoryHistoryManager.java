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
        if (historyList.size() < 100) {
            historyList.add(task); // добавление задачи в лист истории
        } else {
            historyList.remove(0);
            historyList.add(99, task); // добавление задачи в лист истории
        }
    }

    @Override
    public List<Task> getHistory() {
        int sizeArray = historyList.size();
        if (sizeArray < 10) return historyList;

        List<Task> last10TaskList = new ArrayList<>();
        for (int i = historyList.size() - 10; i < historyList.size(); i++) {
            last10TaskList.add(historyList.get(i));
        }
        return last10TaskList;
    }

    @Override
    public void remove(Task task) {
        if (historyList.contains(task)){
            historyList.remove(task);
        }
    }
}
