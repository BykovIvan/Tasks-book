package tracker.controllers;

import tracker.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    void deleteAllSubtasks();
    ArrayList<Subtask> getSubtasks();
    Subtask getSubtask(int id);
    void createNewSubTask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int id);

    void deleteAllEpics();
    ArrayList<Epic> getEpics();
    Epic getEpic(int id);
    void createNewEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpic(int id);

    void updateStatusEpic();
    ArrayList<Subtask> getSubtasksByEpicId(int idEpic);

    void deleteAllTasks();
    ArrayList<Task> getTasks();
    Task getTask(int id);
    void createNewTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);



}
