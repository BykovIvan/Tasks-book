package main.java.tracker.controllers;

import main.java.tracker.history.HistoryManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс менеджера для работы только с историей
 */

public class InMemoryTaskManager implements TaskManager {

    private int id = 0;
    protected HashMap<Integer, Task> mapTasks;
    protected HashMap<Integer, Epic> mapEpics;
    protected HashMap<Integer, Subtask> mapSubtasks;

    protected HistoryManager<Task> historyList;

    public InMemoryTaskManager() {
        mapTasks = new HashMap<>();
        mapEpics = new HashMap<>();
        mapSubtasks = new HashMap<>();
        historyList = Managers.getDefaultHistory();
    }

    @Override
    public void deleteAllSubtasks() {
        mapSubtasks.clear();
        updateStatusEpic();
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(mapSubtasks.values());
    }

    @Override
    public Subtask getSubtask(int id) {
        if (mapSubtasks.containsKey(id)) {
            historyList.add(mapSubtasks.get(id));             // добавление задачи в лист истории
            return mapSubtasks.get(id);
        } else {
            return null;
        }
    }

    @Override
    public int createNewSubTask(Subtask subtask) {
        subtask.setIdTask(id);
        id++;
        mapSubtasks.put(subtask.getIdTask(), subtask);
        int idEpicTemp = subtask.getIdEpic();
        if (mapEpics.containsKey(idEpicTemp)) {                  //добавдяем в список подзадач эпика свою задачу
            Epic epic = mapEpics.get(idEpicTemp);
            epic.getSubtasksOfEpic().add(subtask);
        }
        updateStatusEpic();
        return subtask.getIdTask();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        int idTemp = subtask.getIdTask();
        if (mapSubtasks.containsKey(idTemp)) {
            mapSubtasks.put(idTemp, subtask);
        }
        updateStatusEpic();
    }

    @Override
    public void deleteSubtask(int id) {
        if (mapSubtasks.containsKey(id)) {
            int idEpicTemp = mapSubtasks.get(id).getIdEpic();              //Получили ид эпика в котором лежит это саб
            ArrayList<Subtask> tempSubtasksOfEpic = mapEpics.get(idEpicTemp).getSubtasksOfEpic(); //Получаем список сабов в ID эпика
            tempSubtasksOfEpic.remove(mapSubtasks.get(id));                                       //удаляем из списка пр объекту
            updateStatusEpic();
            mapSubtasks.remove(id);
            historyList.remove(id);             // удаление задачи из листа истории
        }
    }

    public void deleteSubtaskWithOutHistory(int id) {
        if (mapSubtasks.containsKey(id)) {
            int idEpicTemp = mapSubtasks.get(id).getIdEpic();              //Получили ид эпика в котором лежит это саб
            ArrayList<Subtask> tempSubtasksOfEpic = mapEpics.get(idEpicTemp).getSubtasksOfEpic(); //Получаем список сабов в ID эпика
            tempSubtasksOfEpic.remove(mapSubtasks.get(id));                                       //удаляем из списка пр объекту
            updateStatusEpic();
        }
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(mapEpics.values());
    }

    @Override
    public void deleteAllEpics() {
        for (Integer num : mapEpics.keySet()) {
            mapEpics.get(num).getSubtasksOfEpic().clear();
        }
        mapEpics.clear();
        updateStatusEpic();
    }

    @Override
    public Epic getEpic(int id) {
        if (mapEpics.containsKey(id)) {
            historyList.add(mapEpics.get(id));          // добавление задачи в лист истории
            return mapEpics.get(id);
        } else {
            return null;
        }
    }

    @Override
    public int createNewEpic(Epic epic) {
        epic.setIdTask(id);
        ArrayList<Subtask> listSub = epic.getSubtasksOfEpic();
        for (Subtask subtask : listSub) {
            subtask.setIdEpic(id);
        }
        id++;
        mapEpics.put(epic.getIdTask(), epic);
        updateStatusEpic();

        return epic.getIdTask();
    }

    @Override
    public void updateEpic(Epic epic) {
        int idTemp = epic.getIdTask();
        mapEpics.put(idTemp, epic);
        updateStatusEpic();


    }

    @Override
    public void deleteEpic(int id) {
        if (mapEpics.containsKey(id)) {
            mapEpics.get(id).getSubtasksOfEpic().clear();
            historyList.remove(id);             // удаление задачи из листа истории
            mapEpics.remove(id);
            updateStatusEpic();
        }
    }

    /**
     * Получение списка подзадач у эпика по его ид
     *
     * @param idEpic
     * @return
     */
    @Override
    public ArrayList<Subtask> getSubtasksByEpicId(int idEpic) {
        return mapEpics.get(idEpic).getSubtasksOfEpic();
    }


    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(mapTasks.values());
    }

    @Override
    public void deleteAllTasks() {
        mapTasks.clear();
    }

    @Override
    public Task getTask(int id) {
        if (mapTasks.containsKey(id)) {
            historyList.add(mapTasks.get(id));             // добавление задачи в лист истории
            return mapTasks.get(id);
        } else {
            return null;
        }
    }

    @Override
    public int createNewTask(Task task) {
        task.setIdTask(id);
        id++;
        mapTasks.put(task.getIdTask(), task);
        return task.getIdTask();
    }

    @Override
    public void updateTask(Task task) {
        int idTemp = task.getIdTask();
        mapTasks.put(idTemp, task);
    }

    @Override
    public void deleteTask(int id) {
        if (mapTasks.containsKey(id)) {
            historyList.remove(id);             // удаление задачи из листа истории
            mapTasks.remove(id);
        }
    }


    @Override
    public void updateStatusEpic() {
        int tempId = -1;                    //Переменная для хранения ID эпика
        int tempSizeList;              //Переменная для временного хранения размера Аррайлиста эписка с подзадачами
        int tempCount;                  //счетчика статуса DONE
        ArrayList<Subtask> list;
        for (Integer num : mapEpics.keySet()) {
            tempSizeList = 0;
            tempCount = 0;
            if (mapEpics.get(num).getIdTask() != tempId) {
                mapEpics.get(num).setStatus(Status.NEW);
                tempId = mapEpics.get(num).getIdTask();
                Epic epic = mapEpics.get(tempId);
                list = epic.getSubtasksOfEpic();  //Записали АррайЛист от эпика NUM в переменную и с ней работаем
                tempSizeList = list.size();
                for (Subtask task : list) {
                    if (task.getStatus() == Status.IN_PROGRESS) {
                        mapEpics.get(num).setStatus(Status.IN_PROGRESS);
                    } else if (task.getStatus() == Status.DONE) {
                        mapEpics.get(num).setStatus(Status.IN_PROGRESS);
                        tempCount++;
                        if (tempCount == tempSizeList) {
                            mapEpics.get(num).setStatus(Status.DONE);
                        }
                    }
                }
            }
        }
    }

    public List<Task> history() {
        return historyList.getHistory();
    }
}