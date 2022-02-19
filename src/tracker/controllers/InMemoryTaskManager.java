package tracker.controllers;

import tracker.model.*;
import tracker.util.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    int id = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, Subtask> subtasks;
    HistoryManager historyList;


    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        historyList = Managers.getDefaultHistory();

    }

    @Override
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        updateStatusEpic();
    }

    @Override
    public Subtask getSubtask(int id) {
        if (subtasks.containsKey(id)) {
            historyList.add(subtasks.get(id));             // добавление задачи в лист истории
            return subtasks.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void createNewSubTask(Subtask subtask, int idEpic) {
        subtask.setIdTask(id);
        subtask.setIdEpic(idEpic);
        id++;
        subtasks.put(subtask.getIdTask(), subtask);
        if (epics.containsKey(idEpic)) {
            Epic epic = epics.get(idEpic);
            epic.getSubtasksOfEpic().add(subtask);
            updateStatusEpic();
        }

    }

    @Override
    public void updateSubtask(int id, Subtask subtask, int idEpic) {
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            subtasks.get(id).setIdEpic(idEpic);
            updateStatusEpic();
        }

    }

    @Override
    public void deleteSubtask(int id) {
        if (subtasks.containsKey(id)) {
            int idEpicTemp = subtasks.get(id).getIdEpic();              //Получили ид эпика в котором лежит это саб
            ArrayList<Subtask> tempSubtasksOfEpic = epics.get(idEpicTemp).getSubtasksOfEpic(); //Получаем список сабов в ID эпика
            for (int i = 0; i < tempSubtasksOfEpic.size(); i++) {       //Пробегаем по списку и находим
                if (tempSubtasksOfEpic.get(i).equals(subtasks.get(id))) {
                    tempSubtasksOfEpic.remove(i);                           //удаляем
                }
            }
            subtasks.remove(id);
            historyList.remove(subtasks.get(id));             // удаление задачи из листа истории
            updateStatusEpic();
        }
    }


    @Override
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public void deleteAllEpics() {
        for (Integer num : epics.keySet()) {
            epics.get(num).getSubtasksOfEpic().clear();
        }
        epics.clear();
        updateStatusEpic();
    }

    @Override
    public Epic getEpic(int id) {
        if (epics.containsKey(id)) {
            historyList.add(epics.get(id));          // добавление задачи в лист истории
            return epics.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void createNewEpic(Epic epic) {
        epic.setIdTask(id);
        id++;
        epics.put(epic.getIdTask(), epic);
        updateStatusEpic();
    }

    @Override
    public void updateEpic(int id, Epic epic, ArrayList<Subtask> subtasks) {
        if (epics.containsKey(id)) {
            epics.put(id, epic);
            epic.setSubtasksOfEpic(subtasks);
            updateStatusEpic();
        }

    }

    @Override
    public void deleteEpic(int id) {
        if (epics.containsKey(id)) {
            epics.get(id).getSubtasksOfEpic().clear();
            historyList.remove(epics.get(id));             // удаление задачи из листа истории
            epics.remove(id);
            updateStatusEpic();

        }

    }


    @Override
    public ArrayList<Subtask> getSubtasksByEpicId(int idEpic) {
        return epics.get(idEpic).getSubtasksOfEpic();
    }


    @Override
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            historyList.add(tasks.get(id));             // добавление задачи в лист истории
            return tasks.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void createNewTask(Task task) {
        task.setIdTask(id);
        id++;
        tasks.put(task.getIdTask(), task);
    }

    @Override
    public void updateTask(int id, Task task) {
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    @Override
    public void deleteTask(int id) {
        if (tasks.containsKey(id)) {
            historyList.remove(tasks.get(id));             // удаление задачи из листа истории
            tasks.remove(id);
        }
    }


    @Override
    public void updateStatusEpic() {
        int tempId = -1;                    //Переменная для хранения ID эпика
        int tempSizeList;              //Переменная для временного хранения размера Аррайлиста эписка с подзадачами
        int tempCount;                  //счетчика статуса DONE
        ArrayList<Subtask> list;
        for (Integer num : epics.keySet()) {
            tempSizeList = 0;
            tempCount = 0;
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

    public List<Task> history() {
        return historyList.getHistory();
    }
}
