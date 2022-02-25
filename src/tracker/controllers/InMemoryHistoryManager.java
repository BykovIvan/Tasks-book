package tracker.controllers;

import tracker.model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {


    private final HandMadeLinkedList tasksLinkidList ;


    public InMemoryHistoryManager() {
        tasksLinkidList = new HandMadeLinkedList();
    }



    @Override
    public void add(Task task) {
        int id = task.getIdTask();
        tasksLinkidList.linkLast(task, id);
//        linkLast(task, id);
    }

    @Override
    public List<Task> getHistory() {
        return tasksLinkidList.getTasks();
    }

    @Override
    public void remove(int idTask) {
//        removeNode(mapList.get(idTask));
    }

}


