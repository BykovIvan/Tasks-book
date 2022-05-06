package main.java.tracker.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.tracker.gson.EpicSerializer;
import main.java.tracker.gson.SubtaskSerializer;
import main.java.tracker.gson.TaskSerializer;
import main.java.tracker.httpClient.KVTaskClient;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

/**
 * Класс менеджера для работы c httpServers
 */
public class HTTPTaskManager extends FileBackedTasksManager {
    KVTaskClient kvClient;
    String key = "manager";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .registerTypeAdapter(Subtask.class, new SubtaskSerializer())
            .registerTypeAdapter(Epic.class, new EpicSerializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public HTTPTaskManager(String nameTestFile) {
        super(nameTestFile);
        kvClient = new KVTaskClient(nameTestFile);
    }

    @Override
    public void save() {
        StringBuilder builder = new StringBuilder();
        int fullSize = mapTasks.size() + mapEpics.size() + mapSubtasks.size();      //получение всех задач в порядке возрастания ID
        //для расчета начала если вдруг ид задачи начинается не с нуля
        int minIdOfAnyTask = Integer.MAX_VALUE;
        for (Task value : mapTasks.values()) {
            if (value.getIdTask() < minIdOfAnyTask){
                minIdOfAnyTask = value.getIdTask();
            }
        }
        for (Task value : mapEpics.values()) {
            if (value.getIdTask() < minIdOfAnyTask){
                minIdOfAnyTask = value.getIdTask();
            }
        }
        for (Task value : mapSubtasks.values()) {
            if (value.getIdTask() < minIdOfAnyTask){
                minIdOfAnyTask = value.getIdTask();
            }
        }
        for (int i = minIdOfAnyTask; i < (minIdOfAnyTask + fullSize); i++) {
            if (mapTasks.containsKey(i)) {
                builder.append(gson.toJson(mapTasks.get(i))).append(";");
            } else if (mapEpics.containsKey(i)) {
                builder.append(gson.toJson(mapEpics.get(i))).append(";");
            } else if (mapSubtasks.containsKey(i)) {
                builder.append(gson.toJson(mapSubtasks.get(i))).append(";");
            }
        }
//        builder.append("")
//        bw.write("\n");                           //пустая строка
//        bw.write(toStringHistory(historyList));                //получение истории

        kvClient.put(key, builder.toString());
        //TODO своя реализия
    }
}
