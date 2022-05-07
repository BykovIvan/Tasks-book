package tracker.controlles;

import main.java.tracker.controllers.TaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static main.java.tracker.util.Status.*;
import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
    protected T manager;

    //Тест создания задачи
    @Test
    protected void testAddNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        final int taskId = manager.createNewTask(task);
        final Task savedTask = manager.getTask(taskId);
        assertNotNull(savedTask, "Задача не найдена после создания.");
        assertEquals(task, savedTask, "Задачи не совпадают (Task).");

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают после получния по id.");
    }

    //Метод тестирование метода Update
    @Test
    protected void testUpdateTask() {

        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        task2.setIdTask(0);
        task2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        task2.setDuration(334);
        final int taskId = manager.createNewTask(task);
        manager.updateTask(task2);

        final Task savedTask2 = manager.getTask(taskId);
        final List<Task> tasksUpdate = manager.getTasks();

        assertEquals(task2, savedTask2, "Задачи не совпадают после одновления.");
        assertEquals(1, tasksUpdate.size(), "Неверное количество задач после обновления.");

        final Task savedTask3 = manager.getTask(5);

        assertNull(savedTask3, "Задача не удалена после указания неправильного id.");

    }

    //Для метода удаления всех задач
    @Test
    protected void testDeleteAllTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        task2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        task2.setDuration(34);
        manager.createNewTask(task);
        manager.createNewTask(task2);
        manager.deleteAllTasks();
        final List<Task> tasks2 = manager.getTasks();
        final Task savedTask4 = manager.getTask(0);

        assertNull(savedTask4, "Задача не удалена после полного удаления.");
        assertEquals(0, tasks2.size(), "Список не пустой после удаления всех задач.");

    }

    //Метод удаления по id
    @Test
    protected void testDeleteTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        task2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        task2.setDuration(34);
        final int taskId1 = manager.createNewTask(task);
        final int taskId2 = manager.createNewTask(task2);
        final List<Task> tasks1 = manager.getTasks();

        assertEquals(2, tasks1.size(), "Список пустой.");
        assertNotNull(tasks1, "Задачи не возвращаются.");

        manager.deleteTask(taskId1);
        final List<Task> tasks2 = manager.getTasks();

        assertNotNull(tasks2, "Задачи не возвращаются.");
        assertEquals(1, tasks2.size(), "Список не изменился.");

        manager.deleteTask(5);
        final List<Task> tasks3 = manager.getTasks();

        assertNotNull(tasks3, "Задачи не возвращаются.");
        assertEquals(1, tasks3.size(), "Список изменился.");

        manager.deleteAllTasks();
        manager.deleteTask(taskId2);
        final List<Task> tasks4 = manager.getTasks();

        assertEquals(0, tasks4.size(), "Список изменился.");

    }

    @Test
    protected void testGetTasks() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        task2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        task2.setDuration(34);
        final int taskId1 = manager.createNewTask(task);
        final int taskId2 = manager.createNewTask(task2);

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(taskId1), "Задачи не совпадают.");
        assertEquals(task2, tasks.get(taskId2), "Задачи не совпадают.");

        manager.deleteAllTasks();

        final List<Task> tasks2 = manager.getTasks();

        assertEquals(0, tasks2.size(), "Неверное количество задач.");

    }

    @Test
    protected void testGetTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", NEW);
        task.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task.setDuration(34);
        task2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        task2.setDuration(34);
        final int taskId1 = manager.createNewTask(task);
        final int taskId2 = manager.createNewTask(task2);

        Task getTask = manager.getTask(taskId1);
        assertEquals(task, getTask, "Задачи не совпадают.");

        Task getTast2 = manager.getTask(565);
        assertNull(getTast2, "Ошибка получения какой то задачи из списка");

        manager.deleteAllTasks();
        Task getTask3 = manager.getTask(taskId2);
        assertNull(getTask3, "Список не пустой");

    }


    //Subtask

    @Test
    protected void testAddNewSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        subtask.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        final int subtaskId = manager.createNewSubTask(subtask);

        final Subtask savedSubtask = manager.getSubtask(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена после создания.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают (Subtask).");

        final List<Subtask> subtasks = manager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.get(0), "Задачи не совпадают после получния по id.");
    }

    //Метод тестирование метода Update
    @Test
    protected void testUpdateSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask2.setIdTask(1);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:33", formatter));
        subtask2.setDuration(4);
        final int subtaskId = manager.createNewSubTask(subtask);

        manager.updateSubtask(subtask2);

        final Subtask savedSubtask2 = manager.getSubtask(subtaskId);
        final List<Subtask> subtasksUpdate = manager.getSubtasks();

        assertEquals(subtask2, savedSubtask2, "Задачи не совпадают после одновления.");
        assertEquals(1, subtasksUpdate.size(), "Неверное количество задач после обновления.");

        final Subtask savedSubtask3 = manager.getSubtask(5);

        assertNull(savedSubtask3, "Задача не удалена после указания неправильного id.");

    }

    //Для метода удаления всех задач
    @Test
    protected void testDeleteAllSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        manager.deleteAllSubtasks();

        final List<Subtask> subtasks2 = manager.getSubtasks();
        final Subtask savedSubtask4 = manager.getSubtask(0);

        assertNull(savedSubtask4, "Задача не удалена после полного удаления.");
        assertEquals(0, subtasks2.size(), "Список не пустой после удаления всех задач.");

    }

    //Метод удаления по id
    @Test
    protected void testDeleteSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        final int subtaskId1 = manager.createNewSubTask(subtask);
        final int subtaskId2 = manager.createNewSubTask(subtask2);

        final List<Subtask> subtasks1 = manager.getSubtasks();

        assertEquals(2, subtasks1.size(), "Список пустой.");
        assertNotNull(subtasks1, "Задачи не возвращаются.");

        manager.deleteSubtask(subtaskId1);
        final List<Subtask> subtasks2 = manager.getSubtasks();

        assertNotNull(subtasks2, "Задачи не возвращаются.");
        assertEquals(1, subtasks2.size(), "Список не изменился.");

        manager.deleteSubtask(5);
        final List<Subtask> subtasks3 = manager.getSubtasks();

        assertNotNull(subtasks3, "Задачи не возвращаются.");
        assertEquals(1, subtasks3.size(), "Список изменился.");

        manager.deleteAllSubtasks();

        manager.deleteSubtask(subtaskId2);
        final List<Subtask> subtasks4 = manager.getSubtasks();

        assertEquals(0, subtasks4.size(), "Список изменился.");

    }

    @Test
    protected void testGetSubtasks() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        final List<Subtask> subtasks = manager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(2, subtasks.size(), "Неверное количество задач.");

        manager.deleteAllSubtasks();

        final List<Subtask> subtasks2 = manager.getSubtasks();

        assertEquals(0, subtasks2.size(), "Неверное количество задач.");

    }

    @Test
    protected void testGetSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        final int subtaskId1 = manager.createNewSubTask(subtask);
        final int subtaskId2 = manager.createNewSubTask(subtask2);

        final Subtask savedSubtask = manager.getSubtask(subtaskId1);
        final Subtask savedSubtask2 = manager.getSubtask(subtaskId2);
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");
        assertEquals(subtask2, savedSubtask2, "Задачи не совпадают.");

        final Subtask getSubtask2 = manager.getSubtask(565);
        assertNull(getSubtask2, "Ошибка получения какой то задачи из списка");

        manager.deleteAllSubtasks();
        final Subtask getSubtask3 = manager.getSubtask(subtaskId2);
        assertNull(getSubtask3, "Список не пустой");

    }

    @Test
    protected void testGetIdEpicFromSubtask(){
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        final int subtaskId1 = manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        final int idEpicOfSubtask = manager.getSubtask(subtaskId1).getIdEpic();
        assertEquals(epicId, idEpicOfSubtask, "ID эпика не совпадает.");

    }

    //Epic

    @Test
    protected void testAddNewEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        subtask.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        manager.createNewSubTask(subtask);

        final Epic savedEpic = manager.getEpic(epicId);

        assertNotNull(savedEpic, "Задача не найдена после создания.");
        assertEquals(epic, savedEpic, "Задачи не совпадают (Subtask).");

        final List<Epic> epics = manager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают после получния по id.");
    }

    //Метод тестирование метода Update
    @Test
    protected void testUpdateEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);
        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description", NEW);
        epic2.setIdTask(0);

        manager.updateEpic(epic2);

        final Epic savedEpic = manager.getEpic(epicId);
        final List<Epic> epicsUpdate = manager.getEpics();

        assertEquals(epic2, savedEpic, "Задачи не совпадают после одновления.");
        assertEquals(1, epicsUpdate.size(), "Неверное количество задач после обновления.");

        final Epic savedEpic2 = manager.getEpic(5);

        assertNull(savedEpic2, "Задача не удалена после указания неправильного id.");

    }

    //Для метода удаления всех задач
    @Test
    protected void testDeleteAllEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        Epic epic2 = new Epic("Test addNewEpic3", "Test addNewEpic3 description", NEW);
        manager.createNewEpic(epic);
        Epic epic3 = new Epic("Test addNewEpic3", "Test addNewEpic3 description", NEW);
        manager.createNewEpic(epic);

        manager.deleteAllEpics();

        final List<Epic> epics = manager.getEpics();
        final Epic savedEpic1 = manager.getEpic(0);

        assertNull(savedEpic1, "Задача не удалена после полного удаления.");
        assertEquals(0, epics.size(), "Список не пустой после удаления всех задач.");

    }

    //Метод удаления по id
    @Test
    protected void testDeleteEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description", NEW);
        int epicId2 = manager.createNewEpic(epic2);
        Epic epic3 = new Epic("Test addNewEpic3", "Test addNewEpic3 description", NEW);
        manager.createNewEpic(epic3);



        final List<Epic> epics1 = manager.getEpics();

        assertEquals(3, epics1.size(), "Список пустой.");
        assertNotNull(epics1, "Задачи не возвращаются.");

        manager.deleteEpic(epicId);
        final List<Epic> epics2 = manager.getEpics();

        assertNotNull(epics2, "Задачи не возвращаются.");
        assertEquals(2, epics2.size(), "Список не изменился.");

        manager.deleteEpic(5);
        final List<Epic> epics3 = manager.getEpics();

        assertNotNull(epics3, "Задачи не возвращаются.");
        assertEquals(2, epics3.size(), "Список изменился.");

        manager.deleteAllEpics();

        manager.deleteEpic(epicId2);
        final List<Epic> epics4 = manager.getEpics();

        assertEquals(0, epics4.size(), "Список изменился.");

    }

    @Test
    protected void testGetEpics() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        Epic epic2 = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        manager.createNewEpic(epic2);

        final List<Subtask> subtasks = manager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(2, subtasks.size(), "Неверное количество задач.");

        manager.deleteAllSubtasks();

        final List<Subtask> subtasks2 = manager.getSubtasks();

        assertEquals(0, subtasks2.size(), "Неверное количество задач.");

    }

    @Test
    protected void testGetEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        manager.createNewSubTask(subtask);
        manager.createNewSubTask(subtask2);

        Epic epic2 = new Epic("Test addNewEpic2", "Test addNewEpic2 description", NEW);
        int epicId2 = manager.createNewEpic(epic2);
        Epic epic3 = new Epic("Test addNewEpic3", "Test addNewEpic3 description", NEW);
        int epicId3 = manager.createNewEpic(epic3);

        final Epic savedEpic = manager.getEpic(epicId);
        final Epic savedEpic2 = manager.getEpic(epicId2);

        assertEquals(epic, savedEpic, "Задачи не совпадают.");
        assertEquals(epic2, savedEpic2, "Задачи не совпадают2.");

        final Epic savedEpic3 = manager.getEpic(565);
        assertNull(savedEpic3, "Ошибка получения какой то задачи из списка");

        manager.deleteAllEpics();
        final Epic savedEpic4 = manager.getEpic(epicId3);

        assertNull(savedEpic4, "Список не пустой");

    }

    @Test
    protected void testStatusOfEpic(){
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        int epicId = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Test addNewSubtask", "Test addNewSubtask description", NEW);
        Subtask subtask2 = new Subtask("Test addNewSubtask2", "Test addNewSubtask2 description", NEW);
        subtask.setIdEpic(epicId);
        subtask2.setIdEpic(epicId);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        subtask2.setStartTime(LocalDateTime.parse("16.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        final int subtaskId1 = manager.createNewSubTask(subtask);
        final int subtaskId2 = manager.createNewSubTask(subtask2);

        manager.updateStatusEpic();
        assertEquals(NEW, epic.getStatus(), "Неверный статус когда все подзадачи в NEW");

        manager.getSubtask(subtaskId1).setStatus(IN_PROGRESS);

        manager.updateStatusEpic();
        assertEquals(IN_PROGRESS, epic.getStatus(), "Неверный статус когда одна- NEW, вторая - IN_PROGRESS");

        manager.getSubtask(subtaskId2).setStatus(IN_PROGRESS);

        manager.updateStatusEpic();
        assertEquals(IN_PROGRESS, epic.getStatus(), "Неверный статус когда все подзадачи в IN_PROGRESS");

        manager.getSubtask(subtaskId1).setStatus(DONE);

        manager.updateStatusEpic();
        assertEquals(IN_PROGRESS, epic.getStatus(), "Неверный статус когда одна- DONE, вторая - IN_PROGRESS");

        manager.getSubtask(subtaskId2).setStatus(DONE);

        manager.updateStatusEpic();
        assertEquals(DONE, epic.getStatus(), "Неверный статус когда все подзадачи в DONE");

        manager.getSubtask(subtaskId2).setStatus(NEW);

        manager.updateStatusEpic();
        assertEquals(IN_PROGRESS, epic.getStatus(), "Неверный статус когда одна- DONE, вторая - NEW");

    }


}


