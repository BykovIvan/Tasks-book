package tracker.controlles;

import main.java.tracker.controllers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    public void beforeEach(){
        manager = new InMemoryTaskManager();
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