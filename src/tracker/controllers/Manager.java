package tracker.controllers;

import tracker.model.*;
import tracker.util.Status;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    int id = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, Subtask> subtasks;

    public Manager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    //Получение списка всех задач SubTask
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    //Удаление всех задач
    public void deleteAllSubtasks() {
        subtasks.clear();
        updateStatusEpic();
    }

    //Получение по идентификатуру
    public Subtask getSubtask(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        } else {
            return null;
        }
    }

    //Создание задачи
    public void createNewSubTask(Subtask subtask, int idEpic) {
        subtask.setIdTask(id);
        subtask.setIdEpic(idEpic);
        id++;
        subtasks.put(subtask.getIdTask(), subtask);
        if (epics.containsKey(idEpic)) {
            Epic epic = epics.get(idEpic);
            epic.getSubtasksOfEpic().add(subtask);
        } else {
            System.out.println("No no no");
        }
        updateStatusEpic();
    }

    //Обновление задачи
    public void updateSubtask(int id, Subtask subtask, int idEpic) {
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            subtasks.get(id).setIdEpic(idEpic);
        } else {
            System.out.println("Нет такого!");
        }
        updateStatusEpic();
    }

    //Удаление по идентификатору
    public void deleteSubtask(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            updateStatusEpic();
        } else {
            System.out.println("Нет такого!");
        }
    }

    //обновление статуса эпиков по статусам сабов
    public void updateStatusEpic() {
        int tempId = -1;                    //Переменная для хранения ID эпика
        int tempSizeList = 0;              //Переменная для временного хранения размера Аррайлиста эписка с подзадачами
        int tempCount = 0;                  //счетчика статуса DONE
        ArrayList<Subtask> list;
        for (Integer num : epics.keySet()) {
            if (epics.get(num).getIdTask() != tempId) {
                tempId = epics.get(num).getIdTask();
                Epic epic = epics.get(tempId);
                list = epic.getSubtasksOfEpic();  //Записали АррайЛист от эпика NUM в переменную и с ней работаем
                tempSizeList = list.size();
                for (Subtask task : list) {
                    if (task.getStatus() == Status.IN_PROGRESS) {
                        epics.get(num).setStatus(Status.IN_PROGRESS);
                    } else if (task.getStatus() == Status.DONE) {
                        epics.get(num).setStatus(Status.IN_PROGRESS);
                        tempCount++;
                        if (tempCount == tempSizeList) {
                            epics.get(num).setStatus(Status.DONE);
                        }
                    }
                }
            }
        }
    }

    //Получение списка всех задач Epic
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    //Удаление всех задач
    public void deleteAllEpics() {
        for (Integer num : epics.keySet()) {
            epics.get(num).getSubtasksOfEpic().clear();
        }
        epics.clear();
        updateStatusEpic();
    }

    //Получение по идентификатуру
    public Epic getEpic(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        } else {
            return null;
        }
    }

    //Создание задачи
    public void createNewEpic(Epic epic) {
        epic.setIdTask(id);
        id++;
        epics.put(epic.getIdTask(), epic);
        updateStatusEpic();
    }

    //Обновление задачи
    public void updateEpic(int id, Epic epic, ArrayList<Subtask> subtasks) {
        if (epics.containsKey(id)) {
            epics.put(id, epic);
            epic.setSubtasksOfEpic(subtasks);
        } else {
            System.out.println("Нет такого!");
        }
        updateStatusEpic();
    }

    //Удаление по идентификатору
    public void deleteEpic(int id) {
        if (epics.containsKey(id)) {
            epics.get(id).getSubtasksOfEpic().clear();
            epics.remove(id);
        } else {
            System.out.println("Нет такого!");
        }
        updateStatusEpic();
    }

    //Получение задач по id эпика
    public ArrayList<Subtask> getSubtasksByEpicId(int idEpic) {
        return epics.get(idEpic).getSubtasksOfEpic();
    }

    //Получение списка всех задач Task
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    //Удаление всех задач
    public void deleteAllTasks() {
        tasks.clear();
    }

    //Получение по идентификатуру
    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else {
            return null;
        }
    }

    //Создание задачи
    public void createNewTask(Task task) {
        task.setIdTask(id);
        id++;
        tasks.put(task.getIdTask(), task);
    }

    //Обновление задачи
    public void updateTask(int id, Task task) {
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        } else {
            System.out.println("Нет такого!");
        }
    }

    //Удаление по идентификатору
    public void deleteTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Нет такого!");
        }
    }
}