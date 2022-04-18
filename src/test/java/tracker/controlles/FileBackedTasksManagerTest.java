package tracker.controlles;

import main.java.tracker.controllers.FileBackedTasksManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {

    @BeforeEach
    public void beforeEach(){
        manager = new FileBackedTasksManager("history.csv");
    }

    @Test
    @Override
    protected void testCreateNewTask() {
        super.testCreateNewTask();
    }

    @Test
    @Override
    protected void testUpdateTask() {
        super.testUpdateTask();
    }

    @Test
    @Override
    protected void testDeleteTask() {
        super.testDeleteTask();
    }

    @Test
    @Override
    protected void testGetTask() {
        super.testGetTask();
    }

    @Test
    @Override
    protected void testGetTasks() {
        super.testGetTasks();
    }
}

