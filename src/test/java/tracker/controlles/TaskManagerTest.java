package tracker.controlles;

import main.java.tracker.controllers.Managers;
import main.java.tracker.controllers.TaskManager;
import main.java.tracker.history.HistoryManager;
import main.java.tracker.history.InMemoryHistoryManager;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {

    protected T manager;

//Тест создания задачи
    @Test
    protected void testCreateNewTask() {
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

        //Для метода Update
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", Status.NEW);
        final int taskId2 = manager.createNewTask(task);
        manager.updateTask(task, task2);
        final Task savedTask2 = manager.getTask(taskId2);

        assertEquals(task2, savedTask2, "Задачи не совпадают.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");

        final Task savedTask3 = manager.getTask(5);

        manager.deleteAllTasks();
        final Task savedTask4 = manager.getTask(taskId2);
        assertNull(savedTask4, "Задача возвращается.");
        assertEquals(0, tasks.size(), "Неверное количество задач.");


    }

    //Тест обновления задачи
    @Test
    protected void testUpdateTask(){
        Task task1 = new Task("Test1 addNewTask", "Test1 addNewTask description", Status.NEW);
        Task task2 = new Task("Test2 addNewTask", "Test2 addNewTask description", Status.NEW);
        //Стандартное поведение задачи
        int idTask1 = manager.createNewTask(task1);
        manager.createNewTask(task2);
        manager.updateTask(task1, task2);

        final Task savedTask = manager.getTask(idTask1);
        assertNotNull(savedTask, "Задача на возвращаются.");
        assertEquals(task2, savedTask, "Задачи не совпадают.");

        //С пустым списком задач
        Task task3 = new Task("Test3 addNewTask", "Test3 addNewTask description", Status.NEW);
        manager.createNewTask(task3);
        manager.deleteAllTasks();
        manager.updateTask(task2, task3);
        assertTrue(manager.getTasks().isEmpty(), "Список не пустой.");

//        //пустой или не действующий идентификатор
//        Task task4 = new Task("Test4 addNewTask", "Test4 addNewTask description", Status.NEW);
//        Task task5 = new Task("Test5 addNewTask", "Test5 addNewTask description", Status.NEW);
//        manager.createNewTask(task4);
//        manager.createNewTask(task5);

    }
    @Test
    protected void testDeleteTask(){
        Task task1 = new Task("Test1 addNewTask", "Test1 addNewTask description", Status.NEW);
        //Стандартное поведение
        int id = manager.createNewTask(task1);

        manager.deleteTask(id);
        assertTrue(manager.getTasks().isEmpty(), "Список не пустой.");
        //С пустым списком задач
        manager.deleteAllTasks();
        manager.deleteTask(id);
        assertTrue(manager.getTasks().isEmpty(), "Список не пустой.");

        //С неверным идентификатором
        manager.createNewTask(task1);
        manager.deleteTask(5);
        assertEquals(1, manager.getTasks().size(), "Попытка удаления с неправильным идентификатором");



    }

    @Test
    protected void testGetTask(){
        Task task1 = new Task("Test1 addNewTask", "Test1 addNewTask description", Status.NEW);
        //Стандартное поведение
        final int taskId = manager.createNewTask(task1);
        final Task savedTask = manager.getTask(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");
        //С пустым списком
        manager.deleteAllTasks();
        final Task savedTask2 = manager.getTask(taskId);
        assertNull(savedTask2, "Задача пресутствует в списке");
        //С неверным идентификатором
        manager.createNewTask(task1);
        final Task savedTask3 = manager.getTask(3);
        assertNull(savedTask3, "Задача присутствует в списке");


    }

    @Test
    protected void testGetTasks(){

    }

}