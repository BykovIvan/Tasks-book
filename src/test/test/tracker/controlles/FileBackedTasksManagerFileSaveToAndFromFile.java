package tracker.controlles;

import main.java.tracker.controllers.FileBackedTasksManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static main.java.tracker.util.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileBackedTasksManagerFileSaveToAndFromFile {

    private static FileBackedTasksManager manager;
    private static Task task;
    private static Task task2;
    private static Task task3;
    private static Epic epic;
    private static Subtask subtask;
    private static Subtask subtask2;

    @BeforeAll
    public static void beforeAll(){
        manager = new FileBackedTasksManager("history2.csv");
    }

    @BeforeEach
    public void beforeEach(){
        task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task3 = new Task("Test3 addNewTask", "Test3 addNewTask description", NEW);
        epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
    }

    @Test
    protected void testSaveToAndFromFiles() {
        final int taskId = manager.createNewTask(task);
        final int taskId2 = manager.createNewTask(task2);
        final int taskId3 = manager.createNewTask(task3);
        int epicId = manager.createNewEpic(epic);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        final int subtaskId1 = manager.createNewSubTask(subtask);
        final int subtaskId2 = manager.createNewSubTask(subtask2);

        manager.getTask(taskId3);
        manager.getTask(taskId);
        manager.getTask(taskId2);
        manager.getEpic(epicId);
        manager.getSubtask(subtaskId2);
        manager.getSubtask(subtaskId1);
        manager.getTask(taskId3);

        File file = new File("history2.csv");
        final FileBackedTasksManager fileManager = FileBackedTasksManager.loadFromFile(file);

        assertNotNull(fileManager, "Новый объект не сохранился");
        assertEquals(manager.getTasks(), fileManager.getTasks(), "Задачи не сохранились");
        assertEquals(manager.getEpics(), fileManager.getEpics(), "Эпики не сохранились");
        assertEquals(manager.getSubtasks(), fileManager.getSubtasks(), "Подзададчи не сохранились");
//        assertEquals(manager.getPrioritizedTasks(), fileManager.getPrioritizedTasks(), "Лист приорита не совпадает");
        assertEquals(manager.history(), fileManager.history(), "История не сохранилась");
    }

    @Test
    protected void testSaveToAndFromFileswithEmptyList() {
        //Пустыйе списки задач
        manager.deleteAllTasks();
        manager.deleteAllEpics();
        manager.deleteAllSubtasks();
        File file = new File("history2.csv");
        final FileBackedTasksManager fileManager = FileBackedTasksManager.loadFromFile(file);

        assertNotNull(fileManager, "Новый объект не сохранился2");
        assertEquals(manager.getTasks(), fileManager.getTasks(), "Задачи не сохранились2");
        assertEquals(manager.getEpics(), fileManager.getEpics(), "Эпики не сохранились2");
        assertEquals(manager.getSubtasks(), fileManager.getSubtasks(), "Подзададчи не сохранились2");
//        assertEquals(manager.getPrioritizedTasks(), fileManager.getPrioritizedTasks(), "Лист приорита не совпадает");
        assertEquals(manager.history(), fileManager.history(), "История не сохранилась2");
    }

    @Test
    public void testSaveToAndFromFileswithEmptyHistoryList(){
        //Пустой список истории
        manager.createNewTask(task);
        manager.createNewTask(task2);
        manager.createNewTask(task3);
        final int epicId2 = manager.createNewEpic(epic);
        subtask.setIdEpic(epicId2);
        subtask2.setIdEpic(epicId2);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        File file = new File("history2.csv");
        final FileBackedTasksManager fileManager = FileBackedTasksManager.loadFromFile(file);

        assertNotNull(fileManager, "Новый объект не сохранился3");
        assertEquals(manager.getTasks(), fileManager.getTasks(), "Задачи не сохранились3");
        assertEquals(manager.getEpics(), fileManager.getEpics(), "Эпики не сохранились3");
        assertEquals(manager.getSubtasks(), fileManager.getSubtasks(), "Подзададчи не сохранились3");
//        assertEquals(manager.getPrioritizedTasks(), fileManager.getPrioritizedTasks(), "Лист приорита не совпадает");
        assertEquals(manager.history(), fileManager.history(), "История не сохранилась3");
    }
}
