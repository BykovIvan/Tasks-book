package main.java.tracker.util;

import main.java.tracker.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class TaskStartTimeComparator implements Comparator<Task> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
    @Override
    public int compare(Task o1, Task o2) {
        if (!(o1.getStartTime().equals("Null")) && !(o2.getStartTime().equals("Null"))){
            return LocalDateTime.parse(o1.getStartTime(), formatter).compareTo(LocalDateTime.parse(o2.getStartTime(), formatter));

        }else if (o1.getStartTime().equals("Null")){
            return 1;
        } else if (o2.getStartTime().equals("Null")){
            return -1;
        }
        return 1;
    }
}
