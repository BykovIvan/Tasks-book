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

    @Test
    @Override
    protected void testUpdateTask() {
        super.testUpdateTask();
    }

    @Test
    @Override
    protected void testDeleteAllTask() {
        super.testDeleteAllTask();
    }

    @Test
    @Override
    protected void testDeleteTask() {
        super.testDeleteTask();
    }

    @Test
    @Override
    protected void testGetTasks() {
        super.testGetTasks();
    }

    @Test
    @Override
    protected void testGetTask() {
        super.testGetTask();
    }

    @Test
    @Override
    protected void testAddNewSubtask() {
        super.testAddNewSubtask();
    }

    @Test
    @Override
    protected void testUpdateSubtask() {
        super.testUpdateSubtask();
    }

    @Test
    @Override
    protected void testDeleteAllSubtask() {
        super.testDeleteAllSubtask();
    }

    @Test
    @Override
    protected void testDeleteSubtask() {
        super.testDeleteSubtask();
    }

    @Test
    @Override
    protected void testGetSubtasks() {
        super.testGetSubtasks();
    }

    @Test
    @Override
    protected void testGetSubtask() {
        super.testGetSubtask();
    }

    @Test
    @Override
    protected void testAddNewEpic() {
        super.testAddNewEpic();
    }

    @Test
    @Override
    protected void testUpdateEpic() {
        super.testUpdateEpic();
    }

    @Test
    @Override
    protected void testDeleteAllEpic() {
        super.testDeleteAllEpic();
    }

    @Test
    @Override
    protected void testDeleteEpic() {
        super.testDeleteEpic();
    }

    @Test
    @Override
    protected void testGetEpics() {
        super.testGetEpics();
    }

    @Test
    @Override
    protected void testGetEpic() {
        super.testGetEpic();
    }

    @Test
    @Override
    protected void testGetIdEpicFromSubtask() {
        super.testGetIdEpicFromSubtask();
    }

    @Test
    @Override
    protected void testStatusOfEpic() {
        super.testStatusOfEpic();
    }


}

