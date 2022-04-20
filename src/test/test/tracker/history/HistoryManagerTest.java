package tracker.history;

import main.java.tracker.history.HistoryManager;
import main.java.tracker.history.InMemoryHistoryManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static main.java.tracker.util.Status.NEW;
import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    private static HistoryManager<Task> historyManager;
    private static Task task;
    private static Subtask subtask;
    private static Epic epic;

    @BeforeEach
    public void beforeEach(){
        historyManager = new InMemoryHistoryManager<>();
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
        assertNotNull(history, "История пустая.");
        assertEquals(1, history.size(), "История не пустая.");

        historyManager.add(subtask);
        final List<Task> history2 = historyManager.getHistory();
        assertNotNull(history2, "История пустая.");
        assertEquals(2, history2.size(), "История состоит не из 2 задачи.");

        historyManager.add(epic);
        final List<Task> history3 = historyManager.getHistory();
        assertNotNull(history3, "История пустая.");
        assertEquals(3, history3.size(), "История состоит не из 3 задачи.");
        //Дублирование
        historyManager.add(epic);
        final List<Task> history4 = historyManager.getHistory();
        assertNotNull(history4, "История пустая.");
        assertEquals(3, history3.size(), "История состоит не из 3 задачи.");
    }

    @Test
    void getHistory() {
        //Пустой список подзадач
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(0, history.size(), "Список не пустой.");

        historyManager.add(task);
        historyManager.add(task);

        final List<Task> history2 = historyManager.getHistory();
        assertNotNull(history2, "История пустая.");
        assertEquals(1, history2.size(), "Ошибка при добавлении одной и той же задачи.");

        historyManager.add(subtask);
        historyManager.add(epic);
        final List<Task> history3 = historyManager.getHistory();
        assertNotNull(history3, "История пустая.");
        assertEquals(3, history3.size(), "Ошибка при добавлении задачи.");

    }

    @Test
    void remove() {
        historyManager.add(task);
        historyManager.add(subtask);
        historyManager.add(epic);

        historyManager.remove(subtask.getIdTask());
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(2, history.size(), "Что то не удалилось.");

        historyManager.add(subtask);

        historyManager.remove(task.getIdTask());
        final List<Task> history2 = historyManager.getHistory();
        assertNotNull(history2, "История пустая.");
        assertEquals(2, history2.size(), "Что то не удалилось.");

        historyManager.add(task);

        historyManager.remove(epic.getIdTask());
        final List<Task> history3 = historyManager.getHistory();
        assertNotNull(history3, "История пустая.");
        assertEquals(2, history3.size(), "Что то не удалилось.");

        //Дублирование удаления
        historyManager.remove(epic.getIdTask());
        final List<Task> history4 = historyManager.getHistory();
        assertNotNull(history4, "История пустая.");
        assertEquals(2, history4.size(), "Что то не удалилось.");
    }

    @Test
    void contains() {
        //содержит ли элемент в списке по id
        historyManager.add(task);
        historyManager.add(subtask);
        historyManager.add(epic);
        final int idTask = subtask.getIdTask();
        boolean containsTask = historyManager.contains(idTask);
        assertTrue(containsTask, "Ошибка при проверке содержания задачи при правильном id");

        final int idTask2 = 5;
        boolean containsTask2 = historyManager.contains(idTask2);
        assertFalse(containsTask2, "Ошибка при проверке содержания задачи при неправильном id");


    }
}