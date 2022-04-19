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
    protected void testAddNewTask() {
        super.testAddNewTask();
    }
}

