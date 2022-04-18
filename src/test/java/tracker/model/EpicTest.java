package tracker.model;

import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.tracker.controllers.InMemoryTaskManager;
import main.java.tracker.util.Status;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private static InMemoryTaskManager manager;
    private static Epic epic;

    @BeforeEach
    public void beforeEach(){
        manager = new InMemoryTaskManager();
        ArrayList<Subtask> tasks = new ArrayList<>();
        Subtask subtask1 = new Subtask("Sub1", "Dis1", Status.NEW);
        Subtask subtask2 = new Subtask("Sub2", "Dis2", Status.NEW);
        Subtask subtask3 = new Subtask("Sub3", "Dis3", Status.NEW);
        Subtask subtask4 = new Subtask("Sub4", "Dis4", Status.NEW);
        manager.createNewSubTask(subtask1);
        manager.createNewSubTask(subtask2);
        manager.createNewSubTask(subtask3);
        manager.createNewSubTask(subtask4);
        tasks.add(subtask1);
        tasks.add(subtask2);
        tasks.add(subtask3);
        tasks.add(subtask4);
        epic = new Epic("Epic1","EpicDis", Status.NEW,  tasks);

    }

    //Пустой список дозадач
    @Test
    public void isEmptyListOfSubtask(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        list.clear();
        manager.createNewEpic(epic);
        assertTrue(list.isEmpty());
    }

    //Все подзадачи со статусом NEW
    @Test
    public void allStatusOfSubtaskIsNew(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            subtask.setStatus(Status.NEW);
        }
        manager.createNewEpic(epic);
        assertEquals(Status.NEW, epic.getStatus());
    }


    //Все подзадачи со статусом DONE
    @Test
    public void allStatusOfSubtaskIsDone(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            subtask.setStatus(Status.DONE);
        }
        manager.createNewEpic(epic);
        assertEquals(Status.DONE, epic.getStatus());
    }

    //Подзадачи со статусами NEW и DONE
    @Test
    public void allStatusOfSubtaskIsNewAndDone(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            if (subtask.getIdTask() % 2 == 0){
                subtask.setStatus(Status.NEW);
            }else{
                subtask.setStatus(Status.DONE);
            }

        }
        manager.createNewEpic(epic);
        assertEquals(Status.IN_PROGRESS, epic.getStatus());
    }

    //Подзадачи со статусом IN_PROGRESS
    @Test
    public void allStatusOfSubtaskIsInProgress(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            subtask.setStatus(Status.IN_PROGRESS);
        }
        manager.createNewEpic(epic);
        assertEquals(Status.IN_PROGRESS, epic.getStatus());
    }

}