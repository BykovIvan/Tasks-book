//package main.java.tracker.model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import main.java.tracker.controllers.InMemoryTaskManager;
//import main.java.tracker.util.Status;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EpicTest {
//    private static InMemoryTaskManager manager;
//    private static Epic epic;
//
//    @BeforeEach
//    public void beforeEach(){
//        manager = new InMemoryTaskManager();
//        epic = new Epic(1, "Epic1", Status.NEW,"EpicDis",  new ArrayList<>(
//                Arrays.asList(new Subtask(1, "Sub1", Status.NEW, "Dis1"),
//                        new Subtask(2, "Sub2", Status.NEW, "Dis2"),
//                        new Subtask(3, "Sub3", Status.NEW, "Dis3"),
//                        new Subtask(4, "Sub4", Status.NEW, "Dis4"))
//        ));
//    }
//
//    //Пустой список дозадач
//    @Test
//    public void isEmptyListOfSubtask(){
//        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
//        list.clear();
//        manager.createNewEpic(epic);
//        assertTrue(list.isEmpty());
//    }
//
//    //Все подзадачи со статусом NEW
//    @Test
//    public void allStatusOfSubtaskIsNew(){
//        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
//        for (Subtask subtask : list) {
//            subtask.setStatus(Status.NEW);
//        }
//        manager.createNewEpic(epic);
//        assertEquals(Status.NEW, epic.getStatus());
//    }
//
//
//    //Все подзадачи со статусом DONE
//    @Test
//    public void allStatusOfSubtaskIsDone(){
//        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
//        for (Subtask subtask : list) {
//            subtask.setStatus(Status.DONE);
//        }
//        manager.createNewEpic(epic);
//        assertEquals(Status.DONE, epic.getStatus());
//    }
//
//    //Подзадачи со статусами NEW и DONE
//    @Test
//    public void allStatusOfSubtaskIsNewAndDone(){
//        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
//        for (Subtask subtask : list) {
//            if (subtask.getIdTask() % 2 == 0){
//                subtask.setStatus(Status.NEW);
//            }else{
//                subtask.setStatus(Status.DONE);
//            }
//
//        }
//        manager.createNewEpic(epic);
//        assertEquals(Status.IN_PROGRESS, epic.getStatus());
//    }
//
//    //Подзадачи со статусом IN_PROGRESS
//    @Test
//    public void allStatusOfSubtaskIsInProgress(){
//        ArrayList<Subtask> list = epic.getSubtasksOfEpic();
//        for (Subtask subtask : list) {
//            subtask.setStatus(Status.IN_PROGRESS);
//        }
//        manager.createNewEpic(epic);
//        assertEquals(Status.IN_PROGRESS, epic.getStatus());
//    }
//
//}