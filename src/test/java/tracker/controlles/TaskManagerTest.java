package tracker.controlles;

import main.java.tracker.controllers.TaskManager;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(0, manager.getTasks().size(), "Список не пустой.");

//        //пустой или не действующий идентификатор
//        Task task4 = new Task("Test4 addNewTask", "Test4 addNewTask description", Status.NEW);
//        Task task5 = new Task("Test5 addNewTask", "Test5 addNewTask description", Status.NEW);
//        manager.createNewTask(task4);
//        manager.createNewTask(task5);

    }
    @Test
    protected void testDeleteTask(){
        Task task1 = new Task("Test1 addNewTask", "Test1 addNewTask description", Status.NEW);

    }

    @Test
    protected void testGetTask(){

    }

    @Test
    protected void testGetTasks(){

    }

}