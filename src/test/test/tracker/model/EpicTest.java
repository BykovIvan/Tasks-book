package tracker.model;

import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.tracker.controllers.InMemoryTaskManager;

import static main.java.tracker.util.Status.NEW;
import static main.java.tracker.util.Status.IN_PROGRESS;
import static main.java.tracker.util.Status.DONE;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private static InMemoryTaskManager manager;
    private static Epic epic;

    @BeforeEach
    public void beforeEach(){
        manager = new InMemoryTaskManager();
        ArrayList<Subtask> tasks = new ArrayList<>();
        Subtask subtask1 = new Subtask("Sub1", "Dis1", NEW);
        Subtask subtask2 = new Subtask("Sub2", "Dis2", NEW);
        Subtask subtask3 = new Subtask("Sub3", "Dis3", NEW);
        Subtask subtask4 = new Subtask("Sub4", "Dis4", NEW);
        manager.createNewSubTask(subtask1);
        manager.createNewSubTask(subtask2);
        manager.createNewSubTask(subtask3);
        manager.createNewSubTask(subtask4);
        tasks.add(subtask1);
        tasks.add(subtask2);
        tasks.add(subtask3);
        tasks.add(subtask4);
        epic = new Epic("Epic1","EpicDis", NEW,  tasks);

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
            subtask.setStatus(NEW);
        }
        manager.createNewEpic(epic);
        assertEquals(NEW, epic.getStatus());
    }


    //Все подзадачи со статусом DONE
    @Test
    public void allStatusOfSubtaskIsDone(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            subtask.setStatus(DONE);
        }
        manager.createNewEpic(epic);
        assertEquals(DONE, epic.getStatus());
    }

    //Подзадачи со статусами NEW и DONE
    @Test
    public void allStatusOfSubtaskIsNewAndDone(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            if (subtask.getIdTask() % 2 == 0){
                subtask.setStatus(NEW);
            }else{
                subtask.setStatus(DONE);
            }

        }
        manager.createNewEpic(epic);
        assertEquals(IN_PROGRESS, epic.getStatus());
    }

    //Подзадачи со статусом IN_PROGRESS
    @Test
    public void allStatusOfSubtaskIsInProgress(){
        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
        for (Subtask subtask : list) {
            subtask.setStatus(IN_PROGRESS);
        }
        manager.createNewEpic(epic);
        assertEquals(IN_PROGRESS, epic.getStatus());
    }

}