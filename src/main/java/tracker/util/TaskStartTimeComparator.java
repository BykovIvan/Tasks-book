package main.java.tracker.util;

import main.java.tracker.model.Task;

import java.util.Comparator;

public class TaskStartTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (!(o1.getStartTime().equals("Null")) && !(o2.getStartTime().equals("Null"))){
            return o1.getStartTime().compareTo(o2.getStartTime());
        }else if (o1.getStartTime().equals("Null")){
            return 1;
        } else if (o2.getStartTime().equals("Null")){
            return -1;
        }
        return 1;
    }
}
