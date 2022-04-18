package tracker.controlles;

import main.java.tracker.controllers.TaskManager;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class TaskManagerTest<T extends TaskManager> {

    private T manager;

    @Test
    void createNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
//        final int taskId = manager.addNewTask(task);
        final int taskId = manager.createNewTask(task);

        final Task savedTask = manager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }
}