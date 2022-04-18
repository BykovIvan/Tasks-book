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
    protected void createNewTask() {
        super.createNewTask();
    }
}