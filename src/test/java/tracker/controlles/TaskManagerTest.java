package tracker.controlles;

import main.java.tracker.controllers.TaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static main.java.tracker.util.Status.NEW;
import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {

    protected T manager;

//Тест создания задачи
    @Test
    protected void testAddNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);

        final int taskId = manager.createNewTask(task);
        final Task savedTask = manager.getTask(taskId);
        //метод добавления
        assertNotNull(savedTask, "Задача не найдена после создания.");
        assertEquals(task, savedTask, "Задачи не совпадают (Task).");

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают после получния по id.");

        //метод Update
//        final int taskId2 = manager.createNewTask(task);
        manager.updateTask(task, task2);
        final Task savedTask2 = manager.getTask(taskId);
        final List<Task> tasksUpdate = manager.getTasks();

        assertEquals(task2, savedTask2, "Задачи не совпадают после одновления.");
        assertEquals(1, tasksUpdate.size(), "Неверное количество задач после обновления.");

        final Task savedTask3 = manager.getTask(5);

        assertNull(savedTask3, "Задача не удалена после указания неправильного id.");

        //Для метода удаления всех задач
        manager.createNewTask(task);
        manager.createNewTask(task2);

        manager.deleteAllTasks();
        final List<Task> tasks2 = manager.getTasks();
        final Task savedTask4 = manager.getTask(0);

        assertNull(savedTask4, "Задача не удалена после полного удаления.");
        assertEquals(0, tasks2.size(), "Список не пустой после удаления всех задач.");

        //Метод удаления по id
        final int taskId3 = manager.createNewTask(task);
        manager.createNewTask(task2);
        final List<Task> tasks3 = manager.getTasks();

        assertEquals(2, tasks3.size(), "Список пустой.");
        assertNotNull(tasks3, "Задачи на возвращаются.");

        manager.deleteTask(taskId3);
        final List<Task> tasks4 = manager.getTasks();

        assertNotNull(tasks4, "Задачи на возвращаются.");
        assertEquals(1, tasks4.size(), "Список не изменился.");

        manager.deleteTask(5);
        final List<Task> tasks5 = manager.getTasks();

        assertNotNull(tasks5, "Задачи на возвращаются.");
        assertEquals(1, tasks4.size(), "Список изменился.");
    }

    @Test
    protected void testAddNewSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW, epicId);
        Subtask subtask2 = new Subtask("Test2 addNewTask", "Test2 addNewTask description", NEW, epicId);

        final int subtaskId = manager.createNewSubTask(subtask);
        final Task savedSubtask = manager.getSubtask(subtaskId);
        //метод добавления
        assertNotNull(savedSubtask, "Подзадача не найдена после добавления.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        final List<Subtask> subtasks = manager.getSubtasks();

        assertNotNull(subtasks, "Подзадачи на возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество подзадач.");
        assertEquals(subtask, subtasks.get(0), "Подзадачи не совпадают.");

        //метод Update
//        final int subtaskId2 = manager.createNewSubTask(subtask);
        manager.updateSubtask(subtask, subtask2);
        final Subtask savedSubtask2 = manager.getSubtask(subtaskId);
        final List<Subtask> subtasksUpdate = manager.getSubtasks();

        assertEquals(subtask2, savedSubtask2, "Задачи не совпадают.");
        assertEquals(1, subtasksUpdate.size(), "Неверное количество задач.");
//
        final Subtask savedSubtask3 = manager.getSubtask(5);

        assertNull(savedSubtask3, "Задача не удалена.");

        //Для метода удаления всех задач
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        manager.deleteAllSubtasks();
        final List<Subtask> subtasks2 = manager.getSubtasks();
        final Subtask savedSubtask4 = manager.getSubtask(0);

        assertNull(savedSubtask4, "Задача не удалена.");
        assertEquals(0, subtasks2.size(), "Список не пустой.");

        //Метод удаления по id
        final int subtaskId3 = manager.createNewSubTask(subtask);

        final int subtaskId4 = manager.createNewSubTask(subtask2);

        final List<Subtask> subtasks3 = manager.getSubtasks();

        assertEquals(2, subtasks3.size(), "Список пустой.");
        assertNotNull(subtasks3, "Задачи на возвращаются.");

        manager.deleteSubtask(subtaskId3);

        final List<Subtask> subtasks4 = manager.getSubtasks();

        assertNotNull(subtasks4, "Задачи на возвращаются.");
        assertEquals(1, subtasks4.size(), "Список не изменился.");

        manager.deleteSubtask(8);

        final List<Subtask> subtasks5 = manager.getSubtasks();

        assertNotNull(subtasks5, "Задачи на возвращаются.");
        assertEquals(1, subtasks5.size(), "Список изменился.");
        //Наличие ид эпика
        int idEpicOfSub = subtasks5.get(subtaskId4).getIdEpic();

        assertEquals(epicId, idEpicOfSub, "Список изменился.");

    }
}