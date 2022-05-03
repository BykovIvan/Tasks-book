package main.java.tracker.util;

import main.java.tracker.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class TaskStartTimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (!(o1.getStartTime() == null) && !(o2.getStartTime() == null)){
            return o1.getStartTime().compareTo(o2.getStartTime());

        }else if (o1.getStartTime() == null){
            return 1;
        } else if (o2.getStartTime() == null){
            return -1;
        }
        return 1;
    }
}
