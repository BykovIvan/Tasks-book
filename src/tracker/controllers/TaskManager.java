package tracker.controllers;

import tracker.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {

    HashMap<Integer, Subtask> getSubtasks();

    void deleteAllSubtasks();

    Subtask getSubtask(int id);

    void createNewSubTask(Subtask subtask, int idEpic);

    void updateSubtask(int id, Subtask subtask, int idEpic);

    void deleteSubtask(int id);


    HashMap<Integer, Epic> getEpics();

    void deleteAllEpics();

    Epic getEpic(int id);

    void createNewEpic(Epic epic);

    void updateEpic(int id, Epic epic, ArrayList<Subtask> subtasks);

    void deleteEpic(int id);

    void updateStatusEpic();

    ArrayList<Subtask> getSubtasksByEpicId(int idEpic);


    void deleteAllTasks();

    HashMap<Integer, Task> getTasks();

    Task getTask(int id);

    void createNewTask(Task task);

    void updateTask(int id, Task task);

    void deleteTask(int id);


}
