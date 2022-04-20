package main.java.tracker.history;

import main.java.tracker.controllers.InMemoryTaskManager;
import main.java.tracker.controllers.Managers;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static main.java.tracker.util.Status.NEW;
import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    private static HistoryManager<Task> historyManager;
    private static Task task;
    private static Subtask subtask;
    private static Epic epic;

    @BeforeAll
    public static void beforeAll(){
        historyManager = Managers.getDefaultHistory();
        task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        task.setIdTask(0);
        epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        epic.setIdTask(1);
        subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        subtask.setIdTask(2);


    }

    @Test
    void add() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");

        historyManager.add(subtask);
        final List<Task> history2 = historyManager.getHistory();
        assertNotNull(history2, "История не пустая.");
        assertEquals(2, history2.size(), "История не пустая после добавления 2 задачи.");

        historyManager.add(epic);
        final List<Task> history3 = historyManager.getHistory();
        assertNotNull(history3, "История не пустая.");
        assertEquals(3, history3.size(), "История не пустая после добавления 3 задачи.");
    }

    @Test
    void getHistory() {
    }

    @Test
    void remove() {
        historyManager.add(task);
        historyManager.add(subtask);
        historyManager.add(epic);

        historyManager.remove(0);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "История не пустая.");

        historyManager.remove(2);
        final List<Task> history2 = historyManager.getHistory();
        assertNotNull(history2, "История не пустая.");
        assertEquals(1, history2.size(), "История не пустая.");
    }

    @Test
    void contains() {
    }
}